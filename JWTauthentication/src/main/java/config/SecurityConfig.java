package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import security.JWTAuthenticationEntryPoint;
import security.JwtAuthenticationFilter;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
public class SecurityConfig {

	@Autowired
	private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// Updated configuration to use authorizeHttpRequests instead of the deprecated
		// method
		http.csrf(csrf -> csrf.disable()) // Disable CSRF protection for stateless authentication
				.cors(cors -> cors.disable()) // Disable CORS (ensure you handle this as needed in production)
				.authorizeHttpRequests(auth -> auth.requestMatchers("/home/**").authenticated() // Authenticate /home/**
																								// requests
						.requestMatchers("/auth/login").permitAll() // Allow public access to login endpoint
						.anyRequest().authenticated()) // Authenticate any other requests
				.exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint)) // Set the custom
																									// authentication
																									// entry point
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Ensure
																												// stateless
																												// session
																												// policy

		// Add the JwtAuthenticationFilter before Spring's
		// UsernamePasswordAuthenticationFilter
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build(); // Return the HttpSecurity configuration
	}
}
