package com.datta.user_profile_service.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datta.user_profile_service.exceptions.ResourceNotFoundException;
import com.datta.user_profile_service.exceptions.UserAlreadyExistsException;
import com.datta.user_profile_service.models.User;
import com.datta.user_profile_service.models.UserAuthDetails;
import com.datta.user_profile_service.models.UserDTO;
import com.datta.user_profile_service.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDTO createUser(UserDTO user) throws UserAlreadyExistsException {
		if (userRepository.existsByEmail(user.getEmail())) {
			throw new UserAlreadyExistsException("User with email " + user.getEmail() + " already exists.");
		}
		User uObj = this.modelMapper.map(user, User.class);
		User savedUser = userRepository.save(uObj);
		return this.modelMapper.map(savedUser, UserDTO.class);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users.stream()
                .map(user -> this.modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
	}

	@Override
	public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id: " + id));
        return this.modelMapper.map(user, UserDTO.class);
    }

	@Override
	 public UserDTO updateUser(Long id, UserDTO user) throws UserAlreadyExistsException {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id: " + id));

     // Check if the updated email exists and does not belong to the current user
        if (!existingUser.getEmail().equals(user.getEmail()) && userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("Email " + user.getEmail() + " is already in use by another user.");
        }
        
        User updatedData = this.modelMapper.map(user, User.class);

        existingUser.setName(updatedData.getName());
        existingUser.setEmail(updatedData.getEmail());
        existingUser.setMobile(updatedData.getMobile());
        existingUser.setPassword(updatedData.getPassword());
        existingUser.setAge(updatedData.getAge());
        existingUser.setGender(updatedData.getGender());
        existingUser.setAddress(updatedData.getAddress());
        existingUser.setDob(updatedData.getDob());

        User updatedUser = userRepository.save(existingUser);

        return this.modelMapper.map(updatedUser, UserDTO.class);
    }

	@Override
	public Map<String, String> deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id: " + id));

        userRepository.delete(user);

        Map<String, String> response = new HashMap<>();
        response.put("message", "User deleted successfully");
        response.put("userId", id.toString());
        return response;
    }

	@Override
	public UserAuthDetails getUserAuthDetailsByEmail(String email) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this email: " + email));
		return new UserAuthDetails(user.getId(), user.getEmail(), user.getPassword(), user.getName());
	}

}
