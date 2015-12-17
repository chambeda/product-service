package com.tgt.product;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * This is the "starting point" for a product service.  There are lots of missing pieces that would need to be
 * decided by a business partner.
 *
 * It would also be good to implement swagger for consumers of our API
 *
 */

@RestController
public class ProductController {

	@Autowired
	protected DataLoader dl;

	@Autowired
	DAO dao;

	@CrossOrigin(origins = "http://127.0.0.1:8080")
	@RequestMapping("products/{id}")
	public @ResponseBody Product getProducts(@PathVariable String id)
			throws ExecutionException, InterruptedException, JsonParseException, JsonMappingException, IOException {

		//TODO: what should we return if we don't have the product anymore?
		
		Product product = dao.getActiveProduct(id);
		return product;

	}
	
	@RequestMapping("products/all")
	public Products getAllProducts() throws ExecutionException, InterruptedException
	{
		//TODO: Assume we want all active products.
		//TODO:  Lots of things are not implemented for this endpoint.  Need to implement pagination
		Products products = new Products(dao.getAllActiveProducts(), null);
		
		return products;
	}

	//TODO:  Should be in its own Controller
	@RequestMapping("load")
	public void loadData() throws JsonProcessingException, ExecutionException, InterruptedException {
		dl.loadMockData();
	}

}