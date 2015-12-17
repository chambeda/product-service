package com.tgt.product;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProductsTest {

	
	@Test
	public void jsonConversionActive() throws JsonGenerationException, JsonMappingException, IOException
	{
		Product p = new Product("123", "target", "website", 0.0);
		
		List<Product> products = new ArrayList<Product>();
		products.add(p);
		
		Products productstoSend = new Products(products,null);
		
		ObjectMapper m = new ObjectMapper();
		m.writeValue(System.out, productstoSend);
		String expected = "{\"active\":[{\"id\":\"123\",\"title\":\"target\",\"description\":\"website\",\"price\":0.0}],\"inactive\":null}";
		
		assertEquals(expected, m.writeValueAsString(productstoSend));
	}
	
	@Test
	public void jsonConversionInActive() throws JsonGenerationException, JsonMappingException, IOException
	{
		Product p = new Product("123", "target", "website", 0.0);
		
		List<Product> products = new ArrayList<Product>();
		products.add(p);
		
		Products productstoSend = new Products(null,products);
		
		String expected = "{\"active\":null,\"inactive\":[{\"id\":\"123\",\"title\":\"target\",\"description\":\"website\",\"price\":0.0}]}";
		
		ObjectMapper m = new ObjectMapper();
		
		assertEquals(expected, m.writeValueAsString(productstoSend));
		
	}
}
