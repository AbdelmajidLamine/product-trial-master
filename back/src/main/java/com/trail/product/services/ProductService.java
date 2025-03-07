package com.trail.product.services;

import java.util.List;

import com.trail.product.dtos.ProductResponseDto;
import com.trail.product.models.Product;

public interface ProductService {
	List<ProductResponseDto> getAllProducts();
	ProductResponseDto getProductById(final Long id);
	ProductResponseDto createProduct(final Product product);
	ProductResponseDto updateProduct(final Long id, final Product productDetails);
	void deleteProduct(final Long id);
}
