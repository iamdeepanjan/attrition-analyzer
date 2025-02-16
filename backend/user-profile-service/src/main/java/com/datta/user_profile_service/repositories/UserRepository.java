package com.datta.user_profile_service.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datta.user_profile_service.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public boolean existsByEmail(String email);
	public Optional<User> findByEmail(String email);
}
