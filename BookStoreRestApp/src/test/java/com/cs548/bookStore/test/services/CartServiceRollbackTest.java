package com.cs548.bookStore.test.services;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cs548.bookStore.domain.Cart;
import com.cs548.bookStore.services.CartService;



@ContextConfiguration("classpath:orderdao-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CartServiceRollbackTest {

	@Autowired
	CartService cartService;
	
	Cart cart;
	
	@Before
	public void init(){
		/*   A BOOK that should not be in our database -- to help us test error scenarios */	
		cart =new Cart();
		cart.setIsbn("badCart100");
		cart.setQuantity(10);			
	}
	
	@Test
	public void testNonExistingBookInCart(){
		String isbn = cart.getIsbn();
		
		int oriCount,newcount;
		oriCount = cartService.getBookCartCount();
		try{
			cartService.bookAddCart(isbn, cart.getQuantity());;
			System.out.println("Adding success");
		}
		catch(Exception e){
			System.out.println("Adding failed");
			//e.printStackTrace();
		}
		newcount = cartService.getBookCartCount();
		System.out.println("Orig Cnt: " + oriCount + "    New Cnt: " + newcount);
		assertTrue(oriCount == newcount);  /* if no change, then the changes were rolled back */
	}

}
