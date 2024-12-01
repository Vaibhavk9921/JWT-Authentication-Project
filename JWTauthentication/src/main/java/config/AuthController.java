package config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import models.JwtRequest;
import models.JwtResponse;
import security.JwtHelper;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // Allow CORS from frontend (update with your frontend URL)
public class AuthController {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private JwtHelper helper;

	private Logger logger = LoggerFactory.getLogger(AuthController.class);

	@PostMapping("/auth/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
		// Authenticate the user
		this.doAuthenticate(request.getUsername(), request.getPassword());

		// Get user details and generate JWT token
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.helper.generateToken(userDetails);

		// Return the JWT token in response
		JwtResponse response = JwtResponse.builder().jwtToken(token).username(userDetails.getUsername()).build();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	private void doAuthenticate(String username, String password) {
		// Create authentication token
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username,
				password);

		try {
			// Attempt authentication
			manager.authenticate(authentication);
		} catch (BadCredentialsException e) {
			// Log and throw custom error if credentials are invalid
			logger.error("Invalid Username or Password: {}", e.getMessage());
			throw new BadCredentialsException("Invalid Username or Password!!");
		}
	}

	// Handle authentication errors globally for BadCredentialsException
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<String> exceptionHandler() {
		return new ResponseEntity<>("Credentials Invalid!!", HttpStatus.UNAUTHORIZED);
	}
}
