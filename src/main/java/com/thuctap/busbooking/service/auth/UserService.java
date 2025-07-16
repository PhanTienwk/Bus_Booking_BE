package com.thuctap.busbooking.service.auth;

import com.thuctap.busbooking.dto.request.UserRequest;
import com.thuctap.busbooking.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(int id);
    User createUser(User user);
    User updateUser(int id, UserRequest request);
    void deleteUser(int id);
    void restoreUser(int id);
    public List<User> filterUsers(String name, Integer gender, LocalDateTime birthday, String phone, String email, Integer status, Integer role);
}
