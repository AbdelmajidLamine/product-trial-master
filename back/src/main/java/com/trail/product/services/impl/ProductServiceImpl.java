package com.trail.product.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.trail.product.dtos.ProductResponseDto;
import com.trail.product.models.Product;
import com.trail.product.repositories.ProductRepository;
import com.trail.product.services.ProductService;
import com.trail.product.transformators.ProductTransformator;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final ProductTransformator productTransformator;

	public ProductServiceImpl(ProductRepository productRepository, ProductTransformator productTransformator) {this.productRepository = productRepository;
		this.productTransformator = productTransformator;
	}

	@Override
	public List<ProductResponseDto> getAllProducts() {
		return productTransformator.ProductsToProductsDtos(productRepository.findAll());
	}
	@Override
	public ProductResponseDto getProductById(final Long id) {
		return productTransformator.ProductToProductDto(productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found")));
	}
	@Override
	public ProductResponseDto createProduct(final Product product) {
		return productTransformator.ProductToProductDto(productRepository.save(product));
	}
	@Override
	public ProductResponseDto updateProduct(final Long id, final Product productDetails) {
		Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
		product.setName(productDetails.getName());
		product.setDescription(productDetails.getDescription());
		product.setPrice(productDetails.getPrice());
		product.setQuantity(productDetails.getQuantity());
		product.setUpdatedAt(LocalDateTime.now());
		return productTransformator.ProductToProductDto(productRepository.save(product));
	}
	@Override
	public void deleteProduct(final Long id) {
		productRepository.deleteById(id);
	}
}