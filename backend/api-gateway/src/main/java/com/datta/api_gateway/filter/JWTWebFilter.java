//package com.datta.api_gateway.filter;
////import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpRequest;
////import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
////import org.springframework.security.core.context.ReactiveSecurityContextHolder;
////import org.springframework.security.core.userdetails.User;
////import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebFilter;
//import org.springframework.web.server.WebFilterChain;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureException;
//import reactor.core.publisher.Mono;
//
//@Component
//public class JWTWebFilter implements WebFilter{
//	 
//	@Value("${jwt.secret.key}")
//    private String jwtSecretKey;
//	 
//	    private static final List<String> EXCLUDED_PATHS = Arrays.asList(
//	            "/api/v1/allEmployees", 
//	            "/api/v4/login",
//	            "/api/v3/register",
//	            "/actuator/"
//	    );
//	 
//	    @Override
//	    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
//	        ServerHttpRequest request = exchange.getRequest();
//	        String path = request.getURI().getPath();
//	 
//	        // Bypass JWT validation for excluded paths
//	        for (String excludedPath : EXCLUDED_PATHS) {
//	            if (path.startsWith(excludedPath.replace("**", ""))) {
//	                return chain.filter(exchange);
//	            }
//	        }
//	 
//	        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
//	 
//	        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//	            String token = authHeader.substring(7);
//	            try {
//	                Claims claims = Jwts.parser()
//	                        .setSigningKey(jwtSecretKey)
//	                        .parseClaimsJws(token)
//	                        .getBody();
//	                
//					/*
//					 * request.mutate().header("email", claims.getSubject()).build(); UserDetails
//					 * userDetails =
//					 * User.withUsername(claims.getSubject()).password("").authorities(new
//					 * ArrayList<>()).build(); UsernamePasswordAuthenticationToken
//					 * authenticationToken = new UsernamePasswordAuthenticationToken( userDetails,
//					 * null, userDetails.getAuthorities() );
//					 * authenticationToken.setDetails(authenticationToken);
//					 
//	 
//	                exchange = exchange.mutate()
//	                        .request(exchange.getRequest().mutate()
//	                                .header("X-Authenticated-User", claims.getSubject())
//	                                .header("Authorization", "Bearer " + token)
//	                                .build())
//	                        .build();
//	                        */
//	 
//	                return chain.filter(exchange);
//	                        //.contextWrite(ReactiveSecurityContextHolder.withAuthentication(authenticationToken));
//	            } catch (SignatureException e) {
//	            	System.out.println("In API GW EXC"+ authHeader + " token "+ token);
//	                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//	                return exchange.getResponse().setComplete();
//	            }
//	        }
//	 
//	        return chain.filter(exchange);
//	    }
//	}