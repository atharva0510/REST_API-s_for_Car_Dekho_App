package com.jspiders.spring_rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jspiders.spring_rest.pojo.User;
import com.jspiders.spring_rest.response.ResponseStructure;
import com.jspiders.spring_rest.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping(path = "add_user")
	public ResponseEntity<ResponseStructure<User>> addUser(@RequestBody User user) {
		User addUser = userService.addUser(user);
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		if (addUser != null) {
			responseStructure.setMessage("User Added Successfully..!!");
			responseStructure.setData(addUser);
			responseStructure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.OK);
		} else {
			responseStructure.setMessage("User Already Exists..!!");
			responseStructure.setData(addUser);
			responseStructure.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
			return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.NOT_ACCEPTABLE);
		}

	}

	@GetMapping(path = "sign_in")
	public ResponseEntity<ResponseStructure<User>> validateUser(@RequestParam(name = "userName") String userName,
			@RequestParam(name = "password") String password) {
		User user = userService.validateUser(userName, password);
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		if (user != null) {
			responseStructure.setMessage("User Found..!!");
			responseStructure.setData(user);
			responseStructure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.FOUND);
		} else {
			responseStructure.setMessage("Invalid UserId & Password");
			responseStructure.setData(user);
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(path = "update_user")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user) {
		User userToBeUpdated = userService.updateUser(user);
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		if (userToBeUpdated != null) {
			responseStructure.setMessage("User Updated Successfully..!!");
			responseStructure.setData(userToBeUpdated);
			responseStructure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.OK);
		} else {
			responseStructure.setMessage("User Not Found");
			responseStructure.setData(userToBeUpdated);
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(path = "delete_user")
	public ResponseEntity<ResponseStructure<User>> deleteUser(@RequestParam(name = "id") int id) {
		User userToBeDeleted = userService.deleteUser(id);
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		if (userToBeDeleted != null) {
			responseStructure.setMessage("User Deleted Successfully..!!");
			responseStructure.setData(userToBeDeleted);
			responseStructure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.OK);
		} else {
			responseStructure.setMessage("User Not Found");
			responseStructure.setData(userToBeDeleted);
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.NOT_FOUND);
		}
	}

	@PatchMapping(path = "add_user_car")
	public ResponseEntity<ResponseStructure<User>> updateCarListForUser(@RequestParam(name = "userId") int userId,
			@RequestParam(name = "carId") int carId) {
		User updateCarListForUser = userService.updateCarListForUser(userId, carId);
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		if (updateCarListForUser != null) {
			responseStructure.setMessage("Car list for the user is updated");
			responseStructure.setData(updateCarListForUser);
			responseStructure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.OK);
		} else {
			responseStructure.setMessage("Invalid User Id & Car Id");
			responseStructure.setData(updateCarListForUser);
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.NOT_FOUND);
		}
	}

}
