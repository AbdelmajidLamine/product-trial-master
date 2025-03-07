package com.trail.product.controllers;

import java.util.List;

import com.trail.product.dtos.ProductResponseDto;
import com.trail.product.models.Product;
import com.trail.product.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService productService;

	@GetMapping
	public List<ProductResponseDto> getAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/{id}")
	public ProductResponseDto getProductById(@PathVariable Long id) {
		return productService.getProductById(id);
	}

	@PostMapping
	public ProductResponseDto createProduct(@RequestBody Product product, @AuthenticationPrincipal User authenticatedUser) {
		extractUser(authenticatedUser);
		return productService.createProduct(product);
	}

	@PatchMapping("/{id}")
	public ProductResponseDto updateProduct(@PathVariable Long id, @RequestBody Product product, @AuthenticationPrincipal User authenticatedUser) {
		extractUser(authenticatedUser);
		return productService.updateProduct(id, product);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id, @AuthenticationPrincipal User authenticatedUser) {
		extractUser(authenticatedUser);
		productService.deleteProduct(id);
		return ResponseEntity.ok().build();
	}

	public void extractUser(User authenticatedUser) {
		if(!"admin@admin.com".equals(authenticatedUser.getUsername())){
			throw new AccessDeniedException("User is not authenticated");
		}
	}
}