package com.tgt.product;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Products {
	
	@JsonProperty(value="active")
	private List<Product> activeProducts;
	
	@JsonProperty(value="inactive", defaultValue="")
	private List<Product> inactiveProducts;
	
	public Products(List<Product> active, List<Product> inactive )
	{
		
		this.activeProducts = active;
		this.inactiveProducts = inactive;
	}
	
	public Products()
	{
		new Products(null,null);
	}
	
	public List<Product> getActiveProducts()
	{
		return this.activeProducts;
	}
	
	public List<Product> getInactiveProducts()
	{
		return this.inactiveProducts;
	}
	
	
}
