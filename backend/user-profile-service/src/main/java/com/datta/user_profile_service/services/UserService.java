package com.datta.user_profile_service.services;

import java.util.List;
import java.util.Map;

import com.datta.user_profile_service.models.UserAuthDetails;
import com.datta.user_profile_service.models.UserDTO;
import com.datta.user_profile_service.exceptions.UserAlreadyExistsException;

public interface UserService {

	public UserDTO createUser(UserDTO user) throws UserAlreadyExistsException;

	public List<UserDTO> getAllUsers();

	public UserDTO getUserById(Long id);

	public UserDTO updateUser(Long id, UserDTO user) throws UserAlreadyExistsException;

	public Map<String, String> deleteUser(Long id);

	public UserAuthDetails getUserAuthDetailsByEmail(String email);
}
