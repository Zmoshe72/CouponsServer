package com.yan.coupons.logic;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public  class CacheController {
		
	private Map<String, Object> cacheController;

	public  CacheController() {
		super();
		this.cacheController = new HashMap<>();
	}
	
	public Object  get(String token) {
		return cacheController.get(token);
	}

	public  void putCacheController(String key, Object value) {
		cacheController.put(key, value);
	}
	
	
}
