package com.github.chen0040.magento.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.chen0040.magento.MagentoClient;
import com.github.chen0040.magento.models.StockItem;
import com.github.mgiorda.oauth.OAuthConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xschen on 12/6/2017.
 */
public class MagentoInventoryStockManager extends MagentoHttpComponent {
	private static final Logger logger = LoggerFactory.getLogger(MagentoInventoryStockManager.class);
	private static final String relativePath = "rest/V1/stockItems";
	private MagentoClient client;

	public MagentoInventoryStockManager(MagentoClient client) {
		super(client.getHttpComponent());
		this.client = client;
	}

	@Override
	public String token() {
		return client.token();
	}

	@Override
	public String baseUri() {
		return client.baseUri();
	}

	public StockItem getStockItems(String productSku) {
		String uri = baseUri() + "/" + relativePath + "/" + productSku;
		String json = getSecure(uri, logger);
		
		if (!validateJSON(json)) {
			return null;
		}
		
		StockItem result = JSON.parseObject(json, StockItem.class);
		
		return result;
	}

	public String saveStockItems(String productSku, StockItem si) {
		String uri = baseUri() + "/rest/V1/products/" + escape(productSku) + "/stockItems/" + si.getItem_id();
		Map<String, Object> req = new HashMap<>();
		Map<String, Object> obj = new HashMap<>();
		
		obj.put("qty", si.getQty());

		obj.put("item_id", si.getItem_id());
		obj.put("product_id", si.getProduct_id());
		obj.put("stock_id", si.getStock_id());

		obj.put("is_in_stock", si.is_in_stock());
		obj.put("is_qty_decimal", si.is_qty_decimal());

		obj.put("show_default_notification_message", si.isShow_default_notification_message());
		obj.put("use_config_min_qty", si.isUse_config_min_qty());
		obj.put("min_qty", si.getMin_qty());
		obj.put("use_config_min_sale_qty", si.getUse_config_min_sale_qty());
		obj.put("min_sale_qty", si.getMin_sale_qty());
		obj.put("use_config_max_sale_qty", si.isUse_config_max_sale_qty());
		obj.put("max_sale_qty", si.getMax_sale_qty());
		obj.put("use_config_backorders", si.isUse_config_backorders());
		obj.put("backorders", si.getBackorders());
		obj.put("use_config_notify_stock_qty", si.isUse_config_notify_stock_qty());
		obj.put("notify_stock_qty", si.getNotify_stock_qty());
		obj.put("use_config_qty_increments", si.isUse_config_qty_increments());
		obj.put("qty_increments", si.getQty_increments());
		obj.put("use_config_enable_qty_inc", si.isUse_config_enable_qty_inc());
		obj.put("enable_qty_increments", si.isEnable_qty_increments());
		obj.put("use_config_manage_stock", si.isUse_config_manage_stock());
		obj.put("manage_stock", si.isManage_stock());
		obj.put("low_stock_date", si.getLow_stock_date());
		obj.put("is_decimal_divided", si.is_decimal_divided());
		obj.put("stock_status_changed_auto", si.getStock_status_changed_auto());
		obj.put("extension_attributes", new ArrayList<String>());

		req.put("stockItem", obj);
		
		String body = JSON.toJSONString(req, SerializerFeature.BrowserCompatible);
		String stockId = putSecure(uri, body, logger);

		if (!validateJSON(stockId)) {
			return null;
		}

		return stockId;
	}

	@Override
	public boolean oauthEnabled() {
		return client.oauthEnabled();
	}

	@Override
	public OAuthConfig oAuth() {
		return client.oAuth();
	}
}
