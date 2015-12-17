package com.tgt.product;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {

	@NotNull(message = "product Id is required")
	@JsonProperty
	private String id;
	
	@JsonProperty
	private String title;
	
	@JsonProperty
	private String description;
	
	@JsonProperty
	private Double price;
	
	public Product(String id, String title, String description, Double price)
	{
		this.id = id;
		this.description = description;
		this.title = title;
		this.price = price;
	}
	
	public Product()
	{
		new Product(null,null,null,0.0);
	}

	
	public String getId()
	{
		return id;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public Double getPrice()
	{
		return price;
	}
}
