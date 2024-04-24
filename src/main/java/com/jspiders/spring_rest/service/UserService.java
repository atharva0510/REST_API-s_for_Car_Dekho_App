package com.jspiders.spring_rest.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jspiders.spring_rest.pojo.User;
import com.jspiders.spring_rest.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public User addUser(User user) {
		boolean flag = true;
		List<User> users = userRepository.findAllUser();
		if (users != null && users.size() > 0) {
			for (User u : users) {
				if (u.getUserName().equals(user.getUserName()) || u.getEmail().equals(user.getEmail())
						|| u.getMobile() == user.getMobile()) {
					flag = false;
					break;
				}
			}
		}
		if (flag) {
			return userRepository.addUser(user);
		} else {
			return null;
		}

	}

	public User validateUser(String userName, String password) {
		try {
			User user = userRepository.validateUser(userName, password);
			return user;
		} catch (NoResultException e) {
			return null;
		}
	}

	public User updateUser(User user) {
		return userRepository.updateUser(user);
	}

	public User deleteUser(int id) {
		return userRepository.deleteUser(id);
	}

	public User updateCarListForUser(int userId, int carId) {
		return userRepository.updateCarListForUser(userId, carId);
	}

}
