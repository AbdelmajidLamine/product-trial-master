package com.trail.product.services.impl;

import java.util.List;

import com.trail.product.models.Cart;
import com.trail.product.models.Product;
import com.trail.product.models.User;
import com.trail.product.repositories.CartRepository;
import com.trail.product.repositories.ProductRepository;
import com.trail.product.repositories.UserRepository;
import com.trail.product.services.CartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
	private final CartRepository cartRepository;
	private final UserRepository userRepository;
	private final ProductRepository productRepository;

	public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository) {
		this.cartRepository = cartRepository;
		this.userRepository = userRepository;
		this.productRepository = productRepository;
	}

	public Cart addToCart(final Long userId, final Long productId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
		Cart cart = new Cart();
		cart.setUser(user);
		cart.setProduct(product);
		return cartRepository.save(cart);
	}

	public List<Cart> getCartByUserId(final Long userId) {
		return cartRepository.findByUserId(userId);
	}

	public void removeFromCart(final Long cartId) {
		cartRepository.deleteById(cartId);
	}
}
