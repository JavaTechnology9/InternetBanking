package com.internetbanking.service;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class CacheServiceImpl implements CacheService{
	private LoadingCache<String, Object> cache=null;
	 public CacheServiceImpl() {
		 super();
		 cache=CacheBuilder.newBuilder().build(new CacheLoader<String, Object>() {
				@Override
				public Object load(String key) throws Exception {
					// TODO Auto-generated method stub
					return key;
				}
			}) ;
	}

	@Override
	public void saveObject(String key, Object value) {
		cache.put(key, value);
		//cache.getUnchecked("hello");   this computes and loads the value into the cache if it doesn't already exist.
		
	}

	@Override
	public Object getObject(String key) {
		try {
			return cache.get(key);
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}

}
