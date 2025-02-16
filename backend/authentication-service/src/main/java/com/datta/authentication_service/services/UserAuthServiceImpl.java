package com.datta.authentication_service.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datta.authentication_service.exceptions.IncorrectCredentialsException;
import com.datta.authentication_service.exceptions.UserNotFoundException;
import com.datta.authentication_service.feign.UserProfileFeignClient;
import com.datta.authentication_service.models.UserAuth;
import com.datta.authentication_service.models.UserAuthDetails;
import com.datta.authentication_service.models.UserCredentialsDTO;
import com.datta.authentication_service.models.UserDetailsDTO;
import com.datta.authentication_service.repositories.UserAuthRepository;

import feign.FeignException;

@Service
public class UserAuthServiceImpl implements UserAuthService {

	@Autowired
	private UserAuthRepository userAuthRepo;

	@Autowired
	private UserProfileFeignClient userProfile;

	@Autowired
	private JWTTokenService jwt;

	@Override
	public Map<String, Object> validateUser(UserCredentialsDTO userCredentialDTO)
			throws UserNotFoundException, IncorrectCredentialsException {

		// Step 1: Check if the user exists in the local UserAuth repository
		Optional<UserAuth> userAuthOptional = userAuthRepo.findById(userCredentialDTO.getUsername());

		if (userAuthOptional.isPresent()) {
			UserAuth userAuth = userAuthOptional.get();

			// Step 2: Validate password if user is found in the local database
			if (!userAuth.getPassword().equals(userCredentialDTO.getPassword())) {
				throw new IncorrectCredentialsException("Incorrect password.");
			}
//			if (!passwordEncoder.matches(userCredentialDTO.getPassword(), userAuth.getPassword())) {
//				throw new IncorrectCredentialsException("Incorrect password.");
//			}

			// Step 3: Prepare response with user data from local database and generate JWT token
			return prepareUserResponse(userAuth);
		}

		UserAuthDetails userResponse;
		try {
			userResponse = userProfile.getUserAuthDetails(userCredentialDTO.getUsername()).getBody();
			if (userResponse == null) {
				throw new UserNotFoundException("User not found for email: " + userCredentialDTO.getUsername());
			}
		} catch (FeignException.NotFound e) {
			throw new UserNotFoundException("User not found for email: " + userCredentialDTO.getUsername());
		}

		// Step 5: If user is found in UserProfile Service, create a new user record in the local DB
		UserAuth newUserAuth = new UserAuth(userResponse.getUsername(), userResponse.getPassword(),
				userResponse.getName(), userResponse.getUserId());
		userAuthRepo.save(newUserAuth);

		// Step 6: Prepare response with user data and generate JWT token
		return prepareUserResponse(newUserAuth);
	}

	private Map<String, Object> prepareUserResponse(UserAuth userAuth) {
		Map<String, Object> response = new HashMap<>();
		UserDetailsDTO userDetails = new UserDetailsDTO();
		userDetails.setUserId(userAuth.getUserId());
		userDetails.setName(userAuth.getName());
		userDetails.setUsername(userAuth.getUsername());
		response.put("userId", userAuth.getUserId());
		response.put("user", userDetails);

		UserCredentialsDTO credentialsDTO = new UserCredentialsDTO();
		credentialsDTO.setUsername(userAuth.getUsername());
		credentialsDTO.setPassword(userAuth.getPassword()); // Include if needed, otherwise set as null

		String token = jwt.generateToken(credentialsDTO);
		response.put("token", token);

		return response;
	}

}
