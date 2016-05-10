package com.cs548.bookStore.client;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cs548.bookStore.domain.Book;
import com.cs548.bookStore.exceptions.BookNotFoundException;
import com.cs548.bookStore.services.BookService;
import com.cs548.bookStore.services.CartService;
import com.cs548.bookStore.services.InventoryService;


public class BookManager {
	private static BookService bookService;
	private static InventoryService invService;
	private static CartService cartService;
	public static void main(String args[]) throws BookNotFoundException {
		AbstractApplicationContext container = new ClassPathXmlApplicationContext("service.xml");
		bookService = (BookService) container.getBean("bookService");
		invService =  (InventoryService) container.getBean("inventoryService");
		cartService = (CartService) container.getBean("cartService");
		//  Try one of these at a time.   
		successfulCreateBookInRepository();   
		int id = 8;
		Book book;
		
		book = readBook(id);
		System.out.println("The book with id "+book.getId() + " is "+book.toString());
		
		book.setBookTitle("Oracle Server page");
		successfulUpdateBookInRepository(book); 
		book = readBook(id);
		
		System.out.println("After update book in repository-- The book with id"
				+ " "+id + "is "+book.toString());
		//successfulDeleteBookInRepository(15);
		
		String isbn = "1010101010x";
		//System.out.println(successfulGetBookInInventory(isbn)); 
		successfulInsertBookToCart(isbn,6);
		
		List<Book> bookList = bookService.getTotalBooksList();
		for(Book b: bookList){
			System.out.println(b.toString());
		}
		
		container.close();
	}
	
	private static void successfulInsertBookToCart(String isbn, int qty) throws BookNotFoundException {
		try{
			cartService.bookAddCart(isbn, qty);
		}
		catch(BookNotFoundException ex){
			
		}
		// TODO Auto-generated method stub
		
	}
	private static int successfulGetBookInInventory(String isbn) {
			
		return invService.getBookStockByISBN(isbn);
		// TODO Auto-generated method stub
		
	}

	public static Book readBook(int id) throws BookNotFoundException{
		
		Book book = bookService.readBook(id);
		return book;
		
	}
	 public static Book getBookInst() {
		Book book;
		
		book = new Book();
		book.setBookTitle("Algos in Java");
		Integer uid = (int) (10000*Math.random());
		String uids=uid.toString();
		book.setIsbn("ISBNX-12-"+(10000)+uids);
	    book.setPublisherId(2);
		book.setPrice(50.25F);
		book.setId(11);
		return book;
	}
	 
	
	public static void successfulCreateBookInRepository() {
		
		Book book;

		book = getBookInst();
		
		try {
			book = bookService.createBook(book);
			System.out.println("Creation of Book record in repository - Success");
		} catch (Exception ex) {
			System.out.println("Creation Failed because of exception: " + ex);
		}
	}
	 
	public static void successfulUpdateBookInRepository(Book book){		
		try {
			int result = bookService.updateBook(book);
			System.out.println("Update of Book record in repository - Success");
		} catch (Exception ex) {
			System.out.println("Update Failed because of exception: " + ex);
		}			
	}

  public static void successfulDeleteBookInRepository(int id){	
		try {
			int result = bookService.deleteBook(id);
			System.out.println("Delete of Book record in repository - Success");
		} catch (Exception ex) {
			System.out.println("Delete Failed because of exception: " + ex);
		}
	}
}
