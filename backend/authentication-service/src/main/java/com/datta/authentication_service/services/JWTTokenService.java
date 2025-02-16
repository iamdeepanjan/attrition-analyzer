package com.datta.authentication_service.services;

import com.datta.authentication_service.models.UserCredentialsDTO;

public interface JWTTokenService {

	String generateToken(UserCredentialsDTO userCredentialDTO);
}
