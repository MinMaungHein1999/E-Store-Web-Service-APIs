package com.test.demo.service;

import java.util.List;
import com.test.demo.models.User;

public interface UserService {
	List<User> getAllUsers();

	User saveUser(User customer);

	User getUserById(Long id);

	User updateUser(User customer);

	void deleteUserById(Long id);
}
