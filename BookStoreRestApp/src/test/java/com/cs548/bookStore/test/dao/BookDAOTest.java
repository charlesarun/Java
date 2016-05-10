package com.cs548.bookStore.test.dao;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cs548.bookStore.dao.BookDAO;
import com.cs548.bookStore.domain.Book;

@ContextConfiguration("classpath:orderdao-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class BookDAOTest<ProductDAO> {
	@Autowired
	@Qualifier("BookDaoJdbc")
	private BookDAO bookDAO;
	
    private Book book = getBookInst();
    
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
    
    //Test Book count
	@Test
	public void testBookCount() {
		int cnt = bookDAO.getBookCount();
		System.out.println(cnt);
	}
	
	//Test insert book record
	@Test
	public void testInsertBook() {
		bookDAO.insertBook(book);
		int id = book.getId();
		Book book1 = bookDAO.findBookById(id);
		assertEquals(book.getIsbn(), book1.getIsbn());
	
	}
	
	//Test update book
	@Test
	public void testUpdateBook() {
		Book bookD = getBookInst();
		bookDAO.insertBook(bookD);
		bookD.setBookTitle("Oracle and plsql");
		int id = bookD.getId();
		bookDAO.updateBook(bookD);
		Book book1 = bookDAO.findBookById(id);
		assertEquals(bookD.getBookTitle(), book1.getBookTitle());
	
	}
	
	//Test delete book
	@Test
	public void testDeleteBook() {
		Book bookD = getBookInst();
		bookDAO.insertBook(bookD);
		int id = bookD.getId();
		int res = bookDAO.deleteBook(id);
		assertEquals(1, res);
	
	}
}