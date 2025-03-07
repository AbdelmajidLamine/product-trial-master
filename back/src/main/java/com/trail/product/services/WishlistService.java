package com.trail.product.services;

import java.util.List;

import com.trail.product.models.Wishlist;

public interface WishlistService {
	Wishlist addToWishlist(final Long userId, final Long productId);
	List<Wishlist> getWishlistByUserId(final Long userId);
	void removeFromWishlist(final Long wishlistId);
}
