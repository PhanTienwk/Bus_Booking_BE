package com.thuctap.busbooking.service.impl;

import com.thuctap.busbooking.SpecificationQuery.FilterUser;
import com.thuctap.busbooking.dto.request.*;
import com.thuctap.busbooking.entity.Account;
import com.thuctap.busbooking.entity.User;
import com.thuctap.busbooking.exception.AppException;
import com.thuctap.busbooking.exception.ErrorCode;
import com.thuctap.busbooking.mapper.UserMapper;
import com.thuctap.busbooking.repository.AccountRepository;
import com.thuctap.busbooking.repository.UserRepository;
import com.thuctap.busbooking.service.auth.AccountService;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.thuctap.busbooking.service.auth.UserService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    AccountRepository accountRepository;
    AccountService accountService;
    UserMapper userMapper;
    CloudinaryService cloudinaryService;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAllUsers();
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

    @Override
    @Transactional
    public void restoreUser(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        Account account = user.getAccount();
        if (account == null) {
            throw new RuntimeException("Người dùng không có tài khoản liên kết");
        }

        account.setStatus(1);
        accountRepository.save(account);
    }

    @Override
    @Transactional
    public User createUserLogin(UserCreationRequest request) {
        AccountCreationRequest request1 = new AccountCreationRequest(request.getEmail(), request.getPassword());
        Account account = accountService.createAccountUser(request1);

        if(userRepository.existsByCccd(request.getCccd()))
            throw new AppException(ErrorCode.CCCD_EXIST);
        if(userRepository.existsByPhone(request.getPhone()))
            throw new AppException(ErrorCode.PHONE_EXIST);
        User user = userMapper.toUserCreation(request);
        user.setAccount(account);
        user.setAvatar("https://res.cloudinary.com/dxxswaeor/image/upload/v1753078869/file_afjup6.jpg");
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User createDriver(DriverCreationRequest request) {
        AccountCreationRequest request1 = new AccountCreationRequest(request.getEmail(), request.getPassword());
        Account account = accountService.createAccountDriver(request1);
        String imgurl = "https://res.cloudinary.com/dxxswaeor/image/upload/v1753078869/file_afjup6.jpg";
        if(request.getFile() != null && !request.getFile().isEmpty()){
            try {
                log.info(String.valueOf(request.getFile()));
                imgurl = cloudinaryService.uploadFile(request.getFile());
            } catch (IOException e) {
                throw new AppException(ErrorCode.PHOTO_UPLOAD_FAILED);
            }
        }
        if(userRepository.existsByCccd(request.getCccd()))
            throw new AppException(ErrorCode.CCCD_EXIST);
        if(userRepository.existsByPhone(request.getPhone()))
            throw new AppException(ErrorCode.PHONE_EXIST);
        log.info(imgurl);
        User user = userMapper.toDriverCreation(request);
        user.setAccount(account);
        user.setAvatar(imgurl);
        return userRepository.save(user);
    }

    public User getMyInfo(){
        var context = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountRepository.findByEmail(String.valueOf(context)).orElseThrow(()->new AppException(ErrorCode.USER_NOT_EXISTED));
        User user = userRepository.findByAccount(account);
        return user;
    }

    public User updateUserInfo(UserUpdateInfoRequest request){
        var context = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountRepository.findByEmail(String.valueOf(context)).orElseThrow(()->new AppException(ErrorCode.USER_NOT_EXISTED));
        User user = userRepository.findByAccount(account);

        if(request.getFile() != null && !request.getFile().isEmpty()){
            try {
                log.info(String.valueOf(request.getFile()));
                String imgurl = cloudinaryService.uploadFile(request.getFile());
                user.setAvatar(imgurl);
            } catch (IOException e) {
                throw new AppException(ErrorCode.PHOTO_UPLOAD_FAILED);
            }
        }

        user.setName(request.getName());
        user.setBirthDate(request.getBirthDate());
        user.setCccd(request.getCccd());
        user.setPhone(request.getPhone());
        user.setGender(request.getGender());
        return userRepository.save(user);
    }

    public List<User> filterUsers(String name, Integer gender, LocalDateTime birthday, String phone, String email, Integer status, Integer roleId) {
        Specification<User> spec = FilterUser.filterUsers(name, gender, birthday, phone, email, status, roleId);
        return userRepository.findAll(spec);
    }

}
