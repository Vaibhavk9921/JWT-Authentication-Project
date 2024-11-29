package controller;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import models.User;
import services.UserService;

@RestController
@RequestMapping("/home")
public class HomeController {
	private UserService userService;

	// localhost:8080/home/user
	@RequestMapping("/user")
	public List<User> getUser() {
		System.out.println("Getting Users ");
		return userService.getUsers();
	}
	 @GetMapping("/current-user")
	    public String getLoggedInUser(Principal principal){
	        return principal.getName();
	    }
}
