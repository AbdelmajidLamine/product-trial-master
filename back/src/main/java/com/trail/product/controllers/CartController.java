package com.trail.product.controllers;

import java.util.List;

import com.trail.product.models.Cart;
import com.trail.product.services.CartService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
	private final CartService cartService;

	public CartController(CartService cartService) {this.cartService = cartService;}

	@PostMapping("/add")
	public Cart addToCart(@RequestParam Long userId, @RequestParam Long productId) {
		return cartService.addToCart(userId, productId);
	}

	@GetMapping("/user/{userId}")
	public List<Cart> getCartByUserId(@PathVariable Long userId) {
		return cartService.getCartByUserId(userId);
	}

	@DeleteMapping("/remove/{cartId}")
	public void removeFromCart(@PathVariable Long cartId) {
		cartService.removeFromCart(cartId);
	}
}