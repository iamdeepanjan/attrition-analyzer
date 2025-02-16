package com.datta.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes().route("employee_service", r -> r.path("/api/v1/**").uri("http://localhost:8081"))
				.route("notification_service", r -> r.path("/api/v2/**").uri("http://localhost:8082"))
//				.route("user_profile", r -> r.path("/api/v3/**").uri("http://localhost:8083"))
//				.route("authentication", r -> r.path("/api/v4/**").uri("http://localhost:8084"))
				.build();
	}


}
