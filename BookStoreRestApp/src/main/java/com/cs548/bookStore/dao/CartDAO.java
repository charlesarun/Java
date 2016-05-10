package com.cs548.bookStore.dao;



import com.cs548.bookStore.domain.Cart;
import com.cs548.bookStore.exceptions.BookNotFoundException;


public interface CartDAO {

	public int getBookCartCount();
	public int getBookStockByISBN(String isbn);
	public Cart findBookByISBN(String isbn);
	public int findBookInCart(String isbn);
	public int updateBookCart(String isbn, int quantity) throws BookNotFoundException;
	public void bookAddCart(String isbn, int quantity) throws BookNotFoundException ;
	
}
