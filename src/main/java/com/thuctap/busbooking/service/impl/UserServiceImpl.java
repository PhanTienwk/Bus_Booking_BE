package com.thuctap.busbooking.service.impl;

import com.thuctap.busbooking.dto.request.UserRequest;
import com.thuctap.busbooking.entity.Account;
import com.thuctap.busbooking.entity.User;
import com.thuctap.busbooking.repository.AccountRepository;
import com.thuctap.busbooking.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thuctap.busbooking.service.auth.UserService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    AccountRepository accountRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(int id, UserRequest request) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        if (request.getName() != null) {
            existingUser.setName(request.getName());
        }
        if (request.getPhone() != null) {
            existingUser.setPhone(request.getPhone());
        }
        if (request.getGender() != 0) {
            existingUser.setGender(request.getGender());
        }
        if (request.getBirthDate() != null) {
            existingUser.setBirthDate(request.getBirthDate());
        }

        return userRepository.save(existingUser);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        Account account = user.getAccount();
        if (account == null) {
            throw new RuntimeException("Người dùng không có tài khoản liên kết");
        }

        account.setStatus(0);
        accountRepository.save(account);
    }
}
