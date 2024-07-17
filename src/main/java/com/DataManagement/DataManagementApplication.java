package com.DataManagement;

import com.DataManagement.entity.user.RoleName;
import com.DataManagement.entity.user.Roles;
import com.DataManagement.entity.user.User;
import com.DataManagement.repository.userRepo.UserRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class DataManagementApplication {
//	@Autowired
//	private UserRepo userRepo;
//	@Bean
//	public PasswordEncoder passwordEncoder(){
//		return new BCryptPasswordEncoder();
//	}
//
//	@PostConstruct
//	public void saveUser(){
//		User user = new User();
//		user.setFirstName("keshav");
//		user.setLastName("soni");
//		user.setUsername("keshavsoni01");
//		user.setPassword(passwordEncoder().encode("uhhuh@72122"));
//		user.setAccCreatedAt(new Date());
//		user.setLastUpdatedBy(new Date());
//		user.setDeleted(false);
//		user.setContactNo("+91 8269787561");
//		user.setVerificationCode("0110");
//
//		Roles  roles = new Roles();
//		roles.setRoleName(RoleName.valueOf("ADMIN"));
//		roles.setRoleDescription("Ceo of DARK INC.");
//
//		user.setRoles(roles);
//		userRepo.save(user);
//	}

	public static void main(String[] args) {
		SpringApplication.run(DataManagementApplication.class, args);
	}

}
