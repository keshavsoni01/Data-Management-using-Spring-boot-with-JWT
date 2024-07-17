package com.DataManagement.controller.userController;

import com.DataManagement.dto.userDTO.UserDTO;
import com.DataManagement.entity.user.User;
import com.DataManagement.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/v1/all-users")
    public ResponseEntity<List<UserDTO>> fetchUser() {
        System.out.println(userService);
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> fetchUser(@PathVariable int id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @GetMapping("/user/{username}")
    public ResponseEntity<UserDTO> fetchUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @DeleteMapping("/v1/{username}")
    public ResponseEntity<UserDTO> deleteUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.deleteUserByUsername(username));
    }
    @DeleteMapping("/v1/{id}")
    public ResponseEntity<UserDTO> deleteUserById(@PathVariable int id) {
        return ResponseEntity.ok(userService.deleteUserById(id));
    }

    @PostMapping("/v1/create-user")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }
    @PutMapping("/user/update-user/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @RequestBody UserDTO updatedUserDTO) {
        return ResponseEntity.ok(userService.updateUser(id,updatedUserDTO));
    }
}


