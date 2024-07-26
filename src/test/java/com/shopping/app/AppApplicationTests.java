package com.shopping.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.shopping.app.model.Customer;
import com.shopping.app.utils.model.Name;

@SpringBootTest
class AppApplicationTests {

	@Test
	public void test1(){

		// Test the Customer controller
		Customer customer = new Customer();
		customer.setName(new Name("Arvind", "Kejriwal"));
		
		assertEquals(customer.getAge(), -1);
	}

	@Test 
	public void test2(){

	}

	@Test
	public void test3(){

	}

	@Test
	public void test4(){
		
	}

}
