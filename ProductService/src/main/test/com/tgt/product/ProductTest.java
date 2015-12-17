package com.tgt.product;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProductTest {
	
	Product p;
	
	@Before
	public void createProduct()
	{
		 p = new Product("1243","tgt","Let's do this!", 10.0);
		
	}
	
	@Test
	public void productIdNotNull() throws JsonGenerationException, JsonMappingException, IOException
	{
		ObjectMapper m = new ObjectMapper();
		
		String result = "{\"id\":\"1243\",\"title\":\"tgt\",\"description\":\"Let's do this!\",\"price\":10.0}";
		assertTrue(result.equalsIgnoreCase(m.writeValueAsString(p)));
	}
	
	@Test
	public void toJSON() throws JsonGenerationException, JsonMappingException, IOException
	{
		ObjectMapper m = new ObjectMapper();
		String result = "{\"id\":\"1243\",\"title\":\"tgt\",\"description\":\"Let's do this!\",\"price\":10.0}";
		
		assertTrue(result.equalsIgnoreCase(m.writeValueAsString(p)));
	}
	
	

}
