package controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import models.User;
import services.UserService;

@RestController
@RequestMapping("/home")
public class HomeController {

	private final UserService userService;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	public HomeController(UserService userService) {
		this.userService = userService;
	}

	// localhost:8080/home/user
	@GetMapping("/user")
	public List<User> getUser() {
		logger.info("Fetching all users.");
		return userService.getUsers();
	}

	@GetMapping("/current-user")
	public ResponseEntity<?> getLoggedInUser(Principal principal) {
		if (principal == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated");
		}
		return ResponseEntity.ok(principal.getName());
	}
}
