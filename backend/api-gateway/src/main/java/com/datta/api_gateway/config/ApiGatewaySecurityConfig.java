//package com.datta.api_gateway.config;
//import java.util.Arrays;
//import java.util.List;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.reactive.CorsWebFilter;
//import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
//
//import com.datta.api_gateway.filter.JWTWebFilter;
//
//@Configuration
//@EnableWebFluxSecurity
//public class ApiGatewaySecurityConfig {
//
//	@Bean
//    public SecurityWebFilterChain filterChain(ServerHttpSecurity httpSecurity) throws Exception {
//        
//		httpSecurity.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());
//        httpSecurity.cors( c -> c.configurationSource(corsConfigurationSource()));
//		httpSecurity.authorizeExchange( exchange->
//        	exchange.pathMatchers("/api/v1/**","/api/v2/**","/api/v3/**","/api/v4/**").permitAll()
//        	.anyExchange().authenticated());
//        
//		httpSecurity.addFilterAfter(new JWTWebFilter(), SecurityWebFiltersOrder.FIRST);
//
//        return httpSecurity.build();
//    }
//	
//	/*
//    CORS Config
//    */
//   @Bean
//   public CorsWebFilter corsWebFilter() {
//       return new CorsWebFilter(corsConfigurationSource());
//   }
//	
//   private UrlBasedCorsConfigurationSource corsConfigurationSource() {
//	        
//		 	CorsConfiguration configuration = new CorsConfiguration();
//	        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
//	        configuration.setAllowedMethods(Arrays.asList("*"));
//	        configuration.setAllowedHeaders(Arrays.asList("*"));
//	        configuration.setAllowCredentials(true);
//	        
//	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	        source.registerCorsConfiguration("/**", configuration);
//	        return source;
//	    } 
//}
