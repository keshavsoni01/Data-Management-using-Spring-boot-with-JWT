package com.DataManagement.service.userService;

import com.DataManagement.entity.user.User;
import com.DataManagement.repository.userRepo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Roles roles = new Roles();
        User user = userRepo.findByUsernameAndIsDeletedFalse(username).orElseThrow(()->new UsernameNotFoundException("User not found with username :"+username));
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(String.valueOf(user.getRoles().getRoleName()))
                .build();
    }
}
