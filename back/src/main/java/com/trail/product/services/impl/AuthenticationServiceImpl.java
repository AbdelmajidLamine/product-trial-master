package com.trail.product.services.impl;

import com.trail.product.config.JwtUtil;
import com.trail.product.dtos.AuthRequest;
import com.trail.product.dtos.AuthResponse;
import com.trail.product.dtos.LoginRequest;
import com.trail.product.models.User;
import com.trail.product.repositories.UserRepository;
import com.trail.product.services.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	private final UserRepository userRepository;
	private final JwtUtil jwtUtil;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;

	public AuthenticationServiceImpl(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
		this.userRepository = userRepository;
		this.jwtUtil = jwtUtil;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
	}
	@Override
	public String registerUser(final AuthRequest authRequest) {
		User user = new User();
		user.setUsername(authRequest.getUsername());
		user.setFirstname(authRequest.getFirstname());
		user.setEmail(authRequest.getEmail());
		user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
		userRepository.save(user);
		return "User registered successfully!";
	}
	@Override
	public AuthResponse generateToken(final LoginRequest loginRequest) {
		User user = userRepository.findByEmail(loginRequest.getEmail())
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		// Vérifier le mot de passe
		if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
			throw new BadCredentialsException("Invalid password");
		}
		String token = jwtUtil.generateToken(user.getEmail());
		return new AuthResponse(token);
	}
}
