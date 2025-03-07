package com.trail.product.controllers;

import java.util.List;

import com.trail.product.models.Wishlist;
import com.trail.product.services.WishlistService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/wishlist")
public class WishlistController {
	private final WishlistService wishlistService;

	public WishlistController(WishlistService wishlistService) {this.wishlistService = wishlistService;}

	@PostMapping("/add")
	public Wishlist addToWishlist(@RequestParam Long userId, @RequestParam Long productId) {
		return wishlistService.addToWishlist(userId, productId);
	}

	@GetMapping("/user/{userId}")
	public List<Wishlist> getWishlistByUserId(@PathVariable Long userId) {
		return wishlistService.getWishlistByUserId(userId);
	}

	@DeleteMapping("/remove/{wishlistId}")
	public void removeFromWishlist(@PathVariable Long wishlistId) {
		wishlistService.removeFromWishlist(wishlistId);
	}
}
