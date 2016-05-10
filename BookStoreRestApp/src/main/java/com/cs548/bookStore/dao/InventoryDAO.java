package com.cs548.bookStore.dao;

import java.util.List;

import com.cs548.bookStore.domain.BookInv;

public interface InventoryDAO {

	public int getBookStockByISBN(String isbn);
	public BookInv findBookById(int id);
	public List<BookInv> findAllBooksInInventory();
	public void insertBookInInventory(BookInv bookInv);
	public int updateBookStock(String isbn, int stock);
	
}
