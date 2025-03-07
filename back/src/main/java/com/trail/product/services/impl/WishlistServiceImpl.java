package com.trail.product.services.impl;

import java.util.List;

import com.trail.product.models.User;
import com.trail.product.models.Wishlist;
import com.trail.product.models.Product;
import com.trail.product.repositories.ProductRepository;
import com.trail.product.repositories.UserRepository;
import com.trail.product.repositories.WishlistRepository;
import com.trail.product.services.WishlistService;
import org.springframework.stereotype.Service;

@Service
public class WishlistServiceImpl implements WishlistService {

	private final WishlistRepository wishlistRepository;
	private final UserRepository userRepository;
	private final ProductRepository productRepository;

	public WishlistServiceImpl(WishlistRepository wishlistRepository, UserRepository userRepository, ProductRepository productRepository) {
		this.wishlistRepository = wishlistRepository;
		this.userRepository = userRepository;
		this.productRepository = productRepository;
	}
	@Override
	public Wishlist addToWishlist(final Long userId, final Long productId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
		Wishlist wishlist = new Wishlist();
		wishlist.setUser(user);
		wishlist.setProduct(product);
		return wishlistRepository.save(wishlist);
	}
	@Override
	public List<Wishlist> getWishlistByUserId(final Long userId) {
		return wishlistRepository.findByUserId(userId);
	}
	@Override
	public void removeFromWishlist(final Long wishlistId) {
		wishlistRepository.deleteById(wishlistId);
	}
}
