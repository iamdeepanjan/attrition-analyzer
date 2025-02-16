package com.datta.authentication_service.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datta.authentication_service.exceptions.IncorrectCredentialsException;
import com.datta.authentication_service.exceptions.UserNotFoundException;
import com.datta.authentication_service.models.UserCredentialsDTO;
import com.datta.authentication_service.services.UserAuthService;

@RestController
@RequestMapping("/api/v4")
@CrossOrigin(origins = "http://localhost:4200")
public class UserAuthController {
	
	@Autowired
    private UserAuthService userAuthService;
	
	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserCredentialsDTO userCredentialDTO) {
        try {
            Map<String, Object> response = userAuthService.validateUser(userCredentialDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (UserNotFoundException | IncorrectCredentialsException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

}
