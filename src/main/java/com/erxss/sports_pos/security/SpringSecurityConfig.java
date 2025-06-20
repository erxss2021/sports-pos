package com.erxss.sports_pos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.erxss.sports_pos.security.filter.JwtAuthenticationFilter;
import com.erxss.sports_pos.security.filter.JwtValidationFilter;


@Configuration
public class SpringSecurityConfig {
	
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration; 

	@Bean
	AuthenticationManager authenticationManager() throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		
		return http.authorizeHttpRequests(
				auth -> auth
					.requestMatchers(
							"/api/auth/login",
							 "/v3/api-docs/**",
				             "/swagger-ui/**",
				             "/swagger-ui.html",
				             "/swagger-resources/**",
				             "/webjars/**",
				             "/v3/api-docs",
				             "/v3/api-docs.yaml",
				             "/api-docs/swagger-config",
				             "/api-docs",                   
				             "/api-docs/swagger-config"
							
					).permitAll()
					.anyRequest().authenticated())
				.addFilter(new JwtAuthenticationFilter(authenticationManager()))
				.addFilter(new JwtValidationFilter(authenticationManager()))
				.csrf(config -> config.disable())
				.sessionManagement(
						management -> management
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.build();
	}
}
