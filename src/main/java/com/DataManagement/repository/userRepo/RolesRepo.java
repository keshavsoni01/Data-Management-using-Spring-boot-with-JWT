package com.DataManagement.repository.userRepo;

import com.DataManagement.entity.user.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepo extends JpaRepository<Roles,Integer> {
}
