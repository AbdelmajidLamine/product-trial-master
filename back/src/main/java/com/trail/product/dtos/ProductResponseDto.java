package com.trail.product.dtos;

import com.trail.product.enumeration.InventoryStatus;
import lombok.Data;

@Data
public class ProductResponseDto {
	private String code;
	private String name;
	private String description;
	private String image;
	private String category;
	private double price;
	private int quantity;
	private String internalReference;
	private int shellId;
	private InventoryStatus inventoryStatus;
	private int rating;
}
