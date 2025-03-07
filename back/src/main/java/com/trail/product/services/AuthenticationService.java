package com.trail.product.services;

import com.trail.product.dtos.AuthRequest;
import com.trail.product.dtos.AuthResponse;
import com.trail.product.dtos.LoginRequest;

public interface AuthenticationService {
	String registerUser(final AuthRequest authRequest);

	AuthResponse generateToken(final LoginRequest loginRequest);
}
