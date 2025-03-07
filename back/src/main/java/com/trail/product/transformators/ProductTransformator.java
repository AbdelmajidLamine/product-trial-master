package com.trail.product.transformators;

import java.util.List;
import java.util.stream.Collectors;

import com.trail.product.dtos.ProductResponseDto;
import com.trail.product.models.Product;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class ProductTransformator {
	private static ModelMapper modelMapper = null;
	public static ModelMapper modelMapper() {
		if (null == modelMapper) {
			modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			modelMapper.getConfiguration().setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
		}
		return modelMapper;
	}

	@Transactional
	public ProductResponseDto ProductToProductDto(Product product) {
		if (null == product) {
			return null;
		} else {
			return modelMapper().map(product, ProductResponseDto.class);
		}
	}

	@Transactional
	public List<ProductResponseDto> ProductsToProductsDtos(List<Product> products) {
		return products.stream().map(this::ProductToProductDto).collect(Collectors.toList());
	}
}
