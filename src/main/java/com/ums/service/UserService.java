package com.ums.service;

import com.ums.dto.UserRegistrationDto;
import com.ums.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();

    User saveUser(User user);

    User getUserById(Long id);
    User updateUser(User user);
    User registerUser(UserRegistrationDto userRegistrationDto);

    void deleteUserById(Long id);

  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;


}
