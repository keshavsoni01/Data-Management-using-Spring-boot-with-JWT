package com.DataManagement;

import com.DataManagement.entity.user.Roles;
import com.DataManagement.entity.user.User;
import com.DataManagement.repository.userRepo.UserRepo;
import com.DataManagement.service.userService.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class DataManagementApplicationTests {
	@Autowired
	private UserService userService;
	@MockBean
	private UserRepo userRepo;

	// test case for get all users method
	@Test
	public void getUsersTest() {
		when(userRepo.findAllByIsDeletedFalse()).thenReturn(Stream.of(new User(
				1,
				null,
				"keshav",
				"soni",
				"keshavsoni01",
				"keshav@dark.com",
				"8269787561",
				"uhhuh@123",
				null, // resetPassword is not provided
				true,
				"0110",
				false,
				new Date(),
				new Date(),
				new Roles()
		)).collect(Collectors.toList()));
		assertEquals(1,
				userService.getUsers().size());
	}

}
