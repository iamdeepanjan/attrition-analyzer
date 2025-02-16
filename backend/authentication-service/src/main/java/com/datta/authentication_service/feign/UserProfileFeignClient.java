package com.datta.authentication_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.datta.authentication_service.models.UserAuthDetails;

@FeignClient("USER-PROFILE-SERVICE")
public interface UserProfileFeignClient {

	@GetMapping("/api/v3/auth/{email}")
	public ResponseEntity<UserAuthDetails> getUserAuthDetails(@PathVariable("email") String email);
}
