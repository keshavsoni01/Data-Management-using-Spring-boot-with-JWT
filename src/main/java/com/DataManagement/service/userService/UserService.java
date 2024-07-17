package com.DataManagement.service.userService;

import com.DataManagement.dto.userDTO.UserDTO;
import com.DataManagement.entity.user.User;
import com.DataManagement.exception.BadRequestException;
import com.DataManagement.repository.userRepo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService{

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Get all users
    public List<UserDTO> getUsers(){
        List<User> users = userRepo.findAllByIsDeletedFalse();
        return users.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // Get User by Id --------------------------
    public UserDTO getUserById(int id) throws BadRequestException{
        User user = userRepo.findByIdAndIsDeletedFalse(id).orElseThrow(() ->new BadRequestException(401,"User not found with id: "+id));

        return this.mapToDto(user);
    }

//     Get User by Username ----------------------
    public UserDTO getUserByUsername(String username) throws BadRequestException{
        User user = userRepo.findByUsernameAndIsDeletedFalse(username).orElseThrow(()->new BadRequestException(401,"User with username"+username+"not found"));
        return this.mapToDto(user);
    }

//     Delete a User by Username
    public UserDTO deleteUserByUsername(String username) throws BadRequestException{
        User user = userRepo.findByUsernameAndIsDeletedFalse(username).orElseThrow(()->new BadRequestException(401,"User with username "+username+"not found"));
        user.setDeleted();
        userRepo.save(user);
        return this.mapToDto(user);
    }

    // Delete a User by Id
    public UserDTO deleteUserById(int id) throws BadRequestException{
      User user = userRepo.findByIdAndIsDeletedFalse(id).orElseThrow(()->new BadRequestException(401,"User not found with id: "+id));
      user.setDeleted();
      userRepo.save(user);
      return this.mapToDto(user);
    }

    // Create new user
    public UserDTO createUser(UserDTO userDTO) {
        User user = this.mapToEntity(userDTO);
        user.setPassword(passwordEncoder().encode(userDTO.getPassword()));
        user.setAccCreatedAt(new Date());
        user.setLastUpdatedBy(new Date());
        userRepo.save(user);
        return mapToDto(user);
    }

//     Update existing user by id
public UserDTO updateUser(int id, UserDTO updatedUserDTO) throws BadRequestException {
    User user = userRepo.findByIdAndIsDeletedFalse(id)
            .orElseThrow(() -> new BadRequestException(401, "User not found with id: " + id));

    // Update user fields
    user.setFirstName(updatedUserDTO.getFirstName());
    user.setLastName(updatedUserDTO.getLastName());
    user.setUsername(updatedUserDTO.getUsername());
    user.setPassword(passwordEncoder().encode(updatedUserDTO.getPassword()));
    user.setLastUpdatedBy(new Date());
    // Assuming you have a method to update role in UserDTO, you can use it here
    // user.setRoles(updatedUserDTO.getRoles());`

    User updatedUser = userRepo.save(user);
    return this.mapToDto(updatedUser);
}


    public User mapToEntity(UserDTO userDTO){
        User user = this.modelMapper.map(userDTO, User.class);
        return user;
    }
    public UserDTO mapToDto(User user){
        UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
        return userDTO;
    }
}
