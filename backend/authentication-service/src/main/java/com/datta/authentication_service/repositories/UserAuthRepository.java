package com.datta.authentication_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datta.authentication_service.models.UserAuth;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, String> {

}
