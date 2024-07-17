package com.DataManagement.repository.userRepo;

import com.DataManagement.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    Optional<User> findByUsernameAndIsDeletedFalse(String username);
    List<User> findAllByIsDeletedFalse();
    Optional<User> findByIdAndIsDeletedFalse(int id);
}
