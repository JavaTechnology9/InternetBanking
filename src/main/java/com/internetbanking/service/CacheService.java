package com.internetbanking.service;

public interface CacheService {
	public void saveObject(String key, Object value);
	public Object getObject(String key);

}
