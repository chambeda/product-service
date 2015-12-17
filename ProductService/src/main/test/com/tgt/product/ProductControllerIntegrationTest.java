package com.tgt.product;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ProductApplication.class)
@WebIntegrationTest
public class ProductControllerIntegrationTest {


	RestTemplate template = new TestRestTemplate();

	@Test
	public void getActiveProduct() {
		Product result = template.getForObject("http://localhost:8080/products/123", Product.class);

		assertNotNull(result);
		assertTrue(result.getId().equalsIgnoreCase("123"));
	}

	 @Test
	public void getAllActiveProducts() {

		Products result = template.getForObject("http://localhost:8080/products/all", Products.class);

		assertNotNull(result);
		
		assertNotNull(result.getActiveProducts());
		
		assertThat(result.getActiveProducts().size(), greaterThan(1));
		

	}

}
