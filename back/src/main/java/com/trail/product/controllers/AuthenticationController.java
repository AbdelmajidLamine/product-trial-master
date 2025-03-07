package com.trail.product.controllers;

import com.trail.product.dtos.AuthRequest;
import com.trail.product.dtos.AuthResponse;
import com.trail.product.dtos.LoginRequest;
import com.trail.product.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {
	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping("/register")
	public String registerUser(@RequestBody AuthRequest authRequest) {
		return authenticationService.registerUser(authRequest);
	}

	@PostMapping("/login")
	public AuthResponse generateToken(@RequestBody LoginRequest loginRequest) {
		return authenticationService.generateToken(loginRequest);
	}
}
