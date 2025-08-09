package com.thuctap.busbooking.service.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.thuctap.busbooking.dto.request.GoogleLoginRequest;
import com.thuctap.busbooking.dto.request.LoginRequest;
import com.thuctap.busbooking.dto.response.ApiResponse;
import com.thuctap.busbooking.dto.response.GoogleLoginResponse;
import com.thuctap.busbooking.dto.response.JwtResponse;
import com.thuctap.busbooking.entity.Account;
import com.thuctap.busbooking.entity.Role;
import com.thuctap.busbooking.entity.User;
import com.thuctap.busbooking.exception.AppException;
import com.thuctap.busbooking.exception.ErrorCode;
import com.thuctap.busbooking.repository.AccountRepository;
import com.thuctap.busbooking.repository.RoleRepository;
import com.thuctap.busbooking.repository.UserRepository;
import com.thuctap.busbooking.security.jwt.JwtUtil;
import com.thuctap.busbooking.service.auth.AuthService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthServiceImpl implements AuthService {
    AuthenticationManager authenticationManager;
    JwtUtil jwtUtil;
    AccountRepository accountRepository;
    PasswordEncoder passwordEncoder;
    UserRepository userRepository;
    RoleRepository roleRepository;
    @NonFinal
    @Value("${google.client-id}")
    private String googleClientId;

    public JwtResponse login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            Account account = accountRepository.findByEmail(loginRequest.getEmail())
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
            String token = jwtUtil.generateToken(account.getEmail(), account.getRole().getName());
            return new JwtResponse(token);
        } catch (DisabledException e) {
            throw new RuntimeException("Account is disabled. Please contact support.");
        }
    }

    @Transactional
    public GoogleLoginResponse loginWithGoogle(GoogleLoginRequest request) throws Exception {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singletonList(googleClientId))
                .build();

        GoogleIdToken idToken = verifier.verify(request.getToken());
        if (idToken == null) {
            throw new IllegalArgumentException("Invalid Google token");
        }

        GoogleIdToken.Payload payload = idToken.getPayload();
        String email = payload.getEmail();
        String name = (String) payload.get("name");
        String pictureUrl = (String) payload.get("picture");

        Optional<Account> accountOpt = accountRepository.findByEmail(email);

        boolean isNewUser = false;
        Account account;

        if (accountOpt.isPresent()) {
            account = accountOpt.get();
        } else {
            Role userRole = roleRepository.findByName("USER");
            int randomInt = ThreadLocalRandom.current().nextInt(100000, 1000000);
            account = Account.builder()
                    .email(email)
                    .password(passwordEncoder.encode(String.valueOf(randomInt)))
                    .role(userRole)
                    .status(1)
                    .build();
            accountRepository.save(account);

            User user = User.builder()
                    .name(name)
                    .avatar(pictureUrl)
                    .account(account)
                    .build();
            userRepository.save(user);

            isNewUser = true;
        }

        String jwtToken = jwtUtil.generateToken(account.getEmail(), account.getRole().getName());

        return GoogleLoginResponse.builder()
                .isNewUser(isNewUser)
                .email(account.getEmail())
                .name(name)
                .role(account.getRole().getName())
                .token(jwtToken)
                .build();
    }

}
