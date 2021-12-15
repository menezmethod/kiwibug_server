package com.kb.kiwibugback.auth;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('LEAD') or hasRole('MANAGER') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/lead")
	@PreAuthorize("hasRole('LEAD') or hasRole('ADMIN') or hasRole('MANAGER')  ")
	public String leadAccess() {
		return "Lead Board.";
	}

	@GetMapping("/manager")
	@PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
	public String managerAccess() {
		return "Manager Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
}
