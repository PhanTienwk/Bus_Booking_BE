package com.thuctap.busbooking.service.impl;

import com.thuctap.busbooking.dto.request.AccountCreationRequest;
import com.thuctap.busbooking.dto.request.PasswordResetRequest;
import com.thuctap.busbooking.entity.Account;
import com.thuctap.busbooking.entity.Otp;
import com.thuctap.busbooking.entity.Role;
import com.thuctap.busbooking.exception.AppException;
import com.thuctap.busbooking.exception.ErrorCode;
import com.thuctap.busbooking.mapper.AccountMapper;
import com.thuctap.busbooking.repository.AccountRepository;
import com.thuctap.busbooking.repository.OtpRepository;
import com.thuctap.busbooking.repository.RoleRepository;
import com.thuctap.busbooking.service.auth.AccountService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountServiceImpl implements AccountService {
    AccountRepository accountRepository;
    AccountMapper accountMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;
    JavaMailSender mailSender;
    OtpRepository otpRepository;

    private static final long OTP_TTL = 10;

    private Map<String, String> verificationCodes = new HashMap<>();

    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }

    public Account createAccountUser(AccountCreationRequest request){
        if(accountRepository.existsByEmail(request.getEmail()))
            throw new AppException(ErrorCode.ACCOUNT_EXIST);
        Account account =accountMapper.toAccount(request);
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        account.setStatus(1);
        Role role = roleRepository.findByName("USER");
        account.setRole(role);
        return accountRepository.save(account);
    }

    public boolean resetPass(PasswordResetRequest request){
        var context = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountRepository.findByEmail(String.valueOf(context)).orElseThrow(()->new AppException(ErrorCode.USER_NOT_EXISTED));
        System.out.println(account.getPassword().equals(request.getCurrentPassword()));
        if(passwordEncoder.matches(request.getCurrentPassword(), account.getPassword())){
            account.setPassword(passwordEncoder.encode(request.getNewPassword()));
            accountRepository.save(account);
            return true;
        }
        return false;
    }

    public Account createAccountDriver(AccountCreationRequest request){
        if(accountRepository.existsByEmail(request.getEmail()))
            throw new AppException(ErrorCode.ACCOUNT_EXIST);
        Account account =accountMapper.toAccount(request);
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        account.setStatus(1);
        Role role = roleRepository.findByName("DRIVER");
        account.setRole(role);
        return accountRepository.save(account);
    }

    public String sendVerificationEmail(String email) {
        if (accountRepository.existsByEmail(email))
            throw new AppException(ErrorCode.EMAIL_EXIST);
        if(otpRepository.existsByEmail(email))
            throw new AppException(ErrorCode.EMAIL_EXIST);
        String code = generateVerificationCode();
        Otp otp = Otp.builder()
                .email(email)
                .otp(code)
                .expiresAt(LocalDateTime.now().plusMinutes(OTP_TTL))
                .build();
        otpRepository.save(otp);
        verificationCodes.put(email, code);
        sendEmail(email, code);
        return "Verification code sent to " + email;
    }

    private String generateVerificationCode() {
        return String.valueOf((int) (Math.random() * 900000) + 100000);
    }

    private void sendEmail(String email, String code) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(email);
            helper.setSubject("Xác Thực Email - BusBooking");

            // Nội dung HTML cho email
            String htmlContent = "<!DOCTYPE html>"
                    + "<html lang=\"vi\">"
                    + "<head>"
                    + "<meta charset=\"UTF-8\">"
                    + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
                    + "<style>"
                    + "body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }"
                    + ".container { max-width: 600px; margin: 50px auto; background-color: #ffffff; border-radius: 10px; overflow: hidden; box-shadow: 0 0 10px rgba(0,0,0,0.1); }"
                    + ".header { background-color: #1a73e8; color: white; text-align: center; padding: 20px; }"
                    + ".header img { max-width: 150px; }"
                    + ".content { padding: 20px; text-align: center; }"
                    + ".code { background-color: #e0f7fa; color: #00796b; font-size: 24px; font-weight: bold; padding: 10px 20px; border-radius: 5px; display: inline-block; margin: 20px 0; }"
                    + ".footer { text-align: center; padding: 10px; color: #777; font-size: 12px; background-color: #f9f9f9; }"
                    + "@media only screen and (max-width: 600px) { .container { margin: 10px; } }"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<div class=\"container\">"
                    + "<div class=\"header\">"
//                    + "<img src='https://via.placeholder.com/150' alt='BusBooking Logo' style='display:block;'>"
                    + "<h2>Xác Thực Email Của Bạn</h2>"
                    + "</div>"
                    + "<div class=\"content\">"
                    + "<p>Xin chào,</p>"
                    + "<p>Cảm ơn bạn đã đăng ký với BusBooking! Vui lòng sử dụng mã xác thực bên dưới để hoàn tất quá trình đăng ký.</p>"
                    + "<div class=\"code\">" + code + "</div>"
                    + "<p>Mã này có hiệu lực trong 10 phút. Nếu bạn không yêu cầu mã này, vui lòng bỏ qua email này.</p>"
                    + "<p>Trân trọng,<br>Đội ngũ BusBooking</p>"
                    + "</div>"
                    + "<div class=\"footer\">"
                    + "<p>&copy; " + java.time.Year.now().getValue() + " BusBooking. All rights reserved.</p>"
                    + "</div>"
                    + "</div>"
                    + "</body>"
                    + "</html>";

            helper.setText(htmlContent, true); // true để bật chế độ HTML
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public boolean verifyEmail(String email, String code) {
        log.info(email+code);
        return otpRepository.findByEmail(email)
                .filter(otp -> otp.getExpiresAt().isAfter(LocalDateTime.now()))
                .map(otp -> {
                    if (otp.getOtp().equals(code)) {
                        otpRepository.delete(otp);
                        return true;
                    }
                    return false;
                })
                .orElse(false);
    }
}
