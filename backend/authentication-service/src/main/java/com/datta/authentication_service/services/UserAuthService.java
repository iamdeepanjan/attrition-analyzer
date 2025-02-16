package com.datta.authentication_service.services;

import java.util.Map;

import com.datta.authentication_service.exceptions.IncorrectCredentialsException;
import com.datta.authentication_service.exceptions.UserNotFoundException;
import com.datta.authentication_service.models.UserCredentialsDTO;

public interface UserAuthService {
	public Map<String, Object> validateUser(UserCredentialsDTO userCredentialDTO)
			throws UserNotFoundException, IncorrectCredentialsException;
}
