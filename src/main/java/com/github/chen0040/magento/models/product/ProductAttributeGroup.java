package com.github.chen0040.magento.models.product;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.github.chen0040.magento.models.MagentoAttribute;
import com.github.chen0040.magento.models.serialization.AttributeValueDeserializer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductAttributeGroup {
	 private String attribute_group_id;
	 private String attribute_group_name;
	 private long attribute_set_id;
	 
	@JSONField(deserializeUsing = AttributeValueDeserializer.class)
	private List<MagentoAttribute> extension_attributes;
}
