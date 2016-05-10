package com.cs548.bookStore.test.services;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.cs548.bookStore.domain.Book;
import com.cs548.bookStore.exceptions.BookNotFoundException;
import com.cs548.bookStore.services.BookService;



@ContextConfiguration("classpath:orderdao-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration  
public class BookServiceTest {
	@Autowired
	private BookService bookService;

	public Book getBookInst(){
    	Book book;
		book = new Book();
		book.setBookTitle("Algos in Java");
		Integer uid = (int) (10000*Math.random());
		String uids=uid.toString();
		book.setIsbn("ISBNX-12-"+(10000)+uids);
	    book.setPublisherId(2);
		book.setPrice(50.25F);
		return book;
    }
	/* Test that a DuplicateKeyException is thrown when a duplicate book is added to book table */
	@Test(expected=DuplicateKeyException.class) 
	@Transactional
	public void testExceptionOnDoubleInsert() {
		Book book;
		book = getBookInst();
		
		bookService.createBook(book);
		bookService.createBook(book);
	}
	
	@Test(expected=BookNotFoundException.class)
	public void testNoUpdateWhenProductNameNotFound() throws BookNotFoundException {
		
		Book nonExistingBook=getBookInst();
		
		bookService.updateBook(nonExistingBook);
	}
}
