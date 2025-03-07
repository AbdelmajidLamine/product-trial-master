package com.trail.product.services;

import java.util.List;

import com.trail.product.models.Cart;

public interface CartService {
	Cart addToCart(final Long userId, final Long productId);
	List<Cart> getCartByUserId(final Long userId);
	void removeFromCart(final Long cartId);
}
