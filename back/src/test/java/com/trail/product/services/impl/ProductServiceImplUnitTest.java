package com.trail.product.services.impl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.trail.product.dtos.ProductResponseDto;
import com.trail.product.models.Product;
import com.trail.product.repositories.ProductRepository;
import com.trail.product.transformators.ProductTransformator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class ProductServiceImplUnitTest {
	@Mock
	private ProductRepository productRepository;

	@Mock
	private ProductTransformator productTransformator;

	@InjectMocks
	private ProductServiceImpl productService;

	private Product product;
	private ProductResponseDto productResponseDto;

	@BeforeEach
	void setUp() {
		product = new Product();
		product.setId(1L);
		product.setName("pc");
		product.setDescription("pc");
		product.setPrice(555);
		product.setQuantity(5);
		product.setCreatedAt(LocalDateTime.now());
		product.setUpdatedAt(LocalDateTime.now());

		productResponseDto = new ProductResponseDto();
		productResponseDto.setName("pc");
		productResponseDto.setDescription("pc");
		productResponseDto.setPrice(555);
	}

	@Test
	void testGetAllProducts() {
		when(productRepository.findAll()).thenReturn(Collections.singletonList(product));
		when(productTransformator.ProductsToProductsDtos(Collections.singletonList(product)))
				.thenReturn(Collections.singletonList(productResponseDto));

		List<ProductResponseDto> result = productService.getAllProducts();

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals("pc", result.get(0).getName());
		assertEquals("pc", result.get(0).getDescription());
		assertEquals(555, result.get(0).getPrice());

		verify(productRepository, times(1)).findAll();
		verify(productTransformator, times(1)).ProductsToProductsDtos(Collections.singletonList(product));
	}

	@Test
	void testGetProductById() {
		when(productRepository.findById(1L)).thenReturn(Optional.of(product));
		when(productTransformator.ProductToProductDto(product)).thenReturn(productResponseDto);

		ProductResponseDto result = productService.getProductById(1L);

		assertNotNull(result);
		assertEquals("pc", result.getName());
		assertEquals("pc", result.getDescription());
		assertEquals(555, result.getPrice());

		verify(productRepository, times(1)).findById(1L);
		verify(productTransformator, times(1)).ProductToProductDto(product);
	}


	@Test
	void testCreateProduct() {
		when(productRepository.save(any(Product.class))).thenReturn(product);
		when(productTransformator.ProductToProductDto(product)).thenReturn(productResponseDto);

		ProductResponseDto result = productService.createProduct(product);

		assertNotNull(result);
		assertEquals("pc", result.getName());
		assertEquals("pc", result.getDescription());
		assertEquals(555, result.getPrice());

		verify(productRepository, times(1)).save(product);
		verify(productTransformator, times(1)).ProductToProductDto(product);
	}

	@Test
	void testUpdateProduct() {
		Product updatedProduct = new Product();
		updatedProduct.setName("pc");
		updatedProduct.setDescription("pc");
		updatedProduct.setPrice(5555);
		updatedProduct.setQuantity(5);

		when(productRepository.findById(1L)).thenReturn(Optional.of(product));
		when(productRepository.save(any(Product.class))).thenReturn(product);
		when(productTransformator.ProductToProductDto(product)).thenReturn(productResponseDto);

		ProductResponseDto result = productService.updateProduct(1L, updatedProduct);

		assertNotNull(result);
		assertEquals("pc", result.getName());
		assertEquals("pc", result.getDescription());
		assertEquals(555, result.getPrice());

		verify(productRepository, times(1)).findById(1L);
		verify(productRepository, times(1)).save(product);
		verify(productTransformator, times(1)).ProductToProductDto(product);
	}

	@Test
	void testDeleteProduct() {
		doNothing().when(productRepository).deleteById(1L);

		productService.deleteProduct(1L);

		verify(productRepository, times(1)).deleteById(1L);
	}
}
