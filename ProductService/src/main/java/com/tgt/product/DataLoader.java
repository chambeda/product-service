package com.tgt.product;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;

public class DataLoader {
	
	@Autowired
	protected DAO dao;
	
	
	public void loadMockData() throws JsonProcessingException, ExecutionException, InterruptedException
	{
		Product activeProduct1 = new Product("123", "doc 1", "this is the product", 10.0);
		Product activeProduct2 = new Product("111", "product 2", "This is product 2", 100.0);
		Product activeProduct3 = new Product("333", "product 3", "This is product 3", 100.0);
		Product activeProduct4 = new Product("444", "product 4", "This is product 4", 100.0);
		Product activeProduct5 = new Product("555", "product 5", "This is product 5", 100.0);
		
		dao.addActiveProducts(activeProduct1);
		dao.addActiveProducts(activeProduct2);
		dao.addActiveProducts(activeProduct3);
		dao.addActiveProducts(activeProduct4);
		dao.addActiveProducts(activeProduct5);
		
		Product inactiveProduct1 = new Product(null, null, null, null);
		Product inactiveProduct2 = new Product(null, null, null, null);
		Product inactiveProduct3 = new Product(null, null, null, null);
		Product inactiveProduct4 = new Product(null, null, null, null);
		Product inactiveProduct5 = new Product(null, null, null, null);
		
	}

}
