package com.cs548.bookStore.services;


import com.cs548.bookStore.exceptions.BookNotFoundException;

public interface CartService {
	
	public int getBookCartCount();
	public void bookAddCart(String isbn, int quantity) throws BookNotFoundException;
	public void updateBookCart(String isbn, int quantity) throws BookNotFoundException;

}
