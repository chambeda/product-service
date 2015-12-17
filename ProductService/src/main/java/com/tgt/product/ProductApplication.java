package com.tgt.product;

import java.net.UnknownHostException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.basho.riak.client.api.RiakClient;

@SpringBootApplication
public class ProductApplication {
	
	
	@Bean
	public RiakClient getRiakClient() 
	{
		RiakClient client = null;
		try {
			client = RiakClient.newClient();
		} catch (UnknownHostException e) {
			
			throw new RuntimeException(e);
		}
		
		return client;
	}
	
	@Bean
	public DataLoader getDataloader()
	{
		return new DataLoader();
	}
	
	@Bean
	public DAO getDAO()
	{
		return new DAO();
	}
	
	public static void main(String[] args)
	{
		SpringApplication.run(ProductApplication.class, args);
	}

}
