package com.datta.user_profile_service.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datta.user_profile_service.exceptions.UserAlreadyExistsException;
import com.datta.user_profile_service.models.UserAuthDetails;
import com.datta.user_profile_service.models.UserDTO;
import com.datta.user_profile_service.services.UserService;

@RestController
@RequestMapping("/api/v3")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	 public ResponseEntity<?> createUser(@RequestBody UserDTO user) {
        try {
            UserDTO createdUser = userService.createUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (UserAlreadyExistsException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }
    }

	@GetMapping("/users")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

	@GetMapping("/users/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

	@PutMapping("/users/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTO user) {
        try {
            UserDTO updatedUser = userService.updateUser(id, user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (UserAlreadyExistsException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }
    }

	@DeleteMapping("/users/{id}")
	public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long id) {
		Map<String, String> response = userService.deleteUser(id);
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/auth/{email}")
	public ResponseEntity<UserAuthDetails> getUserAuthDetails(@PathVariable String email) {
	    UserAuthDetails userAuthDetails = userService.getUserAuthDetailsByEmail(email);
	    return new ResponseEntity<>(userAuthDetails, HttpStatus.OK);
	}

}
