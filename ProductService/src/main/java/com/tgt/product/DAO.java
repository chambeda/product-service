package com.tgt.product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;

import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.cap.Quorum;
import com.basho.riak.client.api.commands.kv.FetchValue;
import com.basho.riak.client.api.commands.kv.ListKeys;
import com.basho.riak.client.api.commands.kv.ListKeys.Builder;
import com.basho.riak.client.api.commands.kv.StoreValue;
import com.basho.riak.client.api.commands.kv.StoreValue.Option;
import com.basho.riak.client.core.query.Location;
import com.basho.riak.client.core.query.Namespace;
import com.basho.riak.client.core.query.RiakObject;
import com.basho.riak.client.core.util.BinaryValue;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DAO {

	@Autowired
	protected RiakClient client;
	
	public Product getActiveProduct(String id) throws ExecutionException, InterruptedException, JsonParseException, JsonMappingException, IOException
	{
		Namespace ns = new Namespace ("default","activeProducts");
		
		Location location = new Location(ns, id);
		FetchValue fv = new FetchValue.Builder(location).build();
		FetchValue.Response response = client.execute(fv);
		Product returnProduct;
		
		if(response.hasValues())
		{
			RiakObject riakProduct = response.getValue(RiakObject.class);
			BinaryValue productJSON = riakProduct.getValue();
			String data = productJSON.toString();
			ObjectMapper m = new ObjectMapper();
			returnProduct = m.readValue(data, Product.class);
		}
		else
		{
			returnProduct = new Product();
		}
		
		return returnProduct;
	}
	
	//TODO:  Horrible way to find all the keys for a bucket. For production implement riak search
	public List<Product> getAllActiveProducts() throws ExecutionException, InterruptedException
	{
		Namespace ns = new Namespace ("default","activeProducts");
		Builder lk = new ListKeys.Builder(ns);
		ListKeys lks = lk.build();
		
		ListKeys.Response resp = client.execute(lks);
		List<Product> productList = new ArrayList<Product>();
		
		resp.forEach(l-> {
			try {
				productList.add(getActiveProduct(l.getKeyAsString()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
		
		
		return productList;
		
	}
	
	
	public void addActiveProducts(Product activeProduct) throws ExecutionException, InterruptedException, JsonProcessingException
	{
		Namespace ns = new Namespace("default","activeProducts");
		
		ObjectMapper m = new ObjectMapper();
		String product = m.writeValueAsString(activeProduct);
		Location location = new Location(ns, activeProduct.getId());
		RiakObject riakProduct = new RiakObject();
		riakProduct.setValue(BinaryValue.create(product));
		StoreValue store = new StoreValue.Builder(riakProduct).withLocation(location).withOption(Option.W, new Quorum(3)).build();
		client.execute(store);
	
	}
	
}
