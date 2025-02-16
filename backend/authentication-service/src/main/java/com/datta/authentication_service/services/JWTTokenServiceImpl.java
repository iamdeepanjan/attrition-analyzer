package com.datta.authentication_service.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.datta.authentication_service.models.UserCredentialsDTO;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTTokenServiceImpl implements JWTTokenService {

	@Value("${jwt.secret.key}")
	private String secretkey;

	@Override
	public String generateToken(UserCredentialsDTO userCredentialDTO) {
		String token = Jwts.builder()
				.signWith(SignatureAlgorithm.HS256, secretkey)
				.setSubject(userCredentialDTO.getUsername())
				.setIssuer("AuthenticationAPI")
				.setIssuedAt(new Date())
//				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
				.compact();

		return token;
	}

}
