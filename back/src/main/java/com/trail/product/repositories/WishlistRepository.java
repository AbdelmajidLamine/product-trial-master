package com.trail.product.repositories;

import java.util.List;

import com.trail.product.models.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
	List<Wishlist> findByUserId(Long userId);
}