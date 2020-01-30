package com.github.chen0040.magento.models.product;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductAttributeOption {
	private String label;
	private String value;
	private Integer sort_order;
	private Boolean is_default;
	List<ProductAttributeOptionStoreLabel> store_labels;
}