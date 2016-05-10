package com.cs548.bookStore.resthandlers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.cs548.bookStore.domain.Book;
import com.cs548.bookStore.domain.BookList;
import com.cs548.bookStore.exceptions.BookNotFoundException;
import com.cs548.bookStore.exceptions.UnknownResourceException;
import com.cs548.bookStore.services.BookService;

@Path("/bookrestapp")
public class BookRestHandler {
	@Autowired
	private BookService bookService;
	private Logger logger = Logger.getLogger(BookRestHandler.class);
	
	/* Test Url:
	 * http://localhost:9999/bookStore/webservices/bookrestapp/book/2
	 * See web.xml file for Jersey configuration
	 */
	@GET
	@Path("/book/{id}")
	@Produces("application/xml, application/json")
	public Book getBook(@PathParam("id") int id) {
		Book book = null;
		
		try {
			book = lookupBook(id);
		} catch (BookNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (book == null) {
			throw new UnknownResourceException("Book id: " + id + " is invalid");
		}
		
		return book;
	}
	

	private Book lookupBook(int id) throws BookNotFoundException {
		Book student;
		
		student = bookService.readBook(id);
		if (student == null) {
			throw new UnknownResourceException("Student id: " + id + " is invalid");
		}
		
		return student;
	}
	/* Test Url:
	 * http://localhost:9999/bookStore/webservices/bookrestapp/book/100
	 * See web.xml file for Jersey configuration
	 */
	@GET
	@Path("/book")
	@Produces("application/xml")
	public BookList getBookList() {
		List<Book> studList;
		BookList listOfBooks = new BookList();
		
		studList = bookService.getTotalBooksList();
		listOfBooks.setStudentList(studList);
		return listOfBooks;
	}
	
	
	/* Test Url -- Post the data from file student.xml to:
	 * http://localhost:9999/bookStore/webservices/bookrestapp/book/
	 * After doing the post, use a get command to retrieve the student (and verify that the post was successful).
	 */
	@POST
	@Path("/book")
	@Produces("application/json, application/xml")
	@Consumes("application/json, application/xml")
	public Response addBook(Book book) {
		ResponseBuilder respBuilder;
		
		bookService.createBook(book);
		respBuilder = Response.status(Status.CREATED);
		respBuilder.entity(book);
		return respBuilder.build();
	}
	
	/* Test Url -- Put (HTTP Put Command) the data from file student.xml to:
	 * http://localhost:9999/bookStore/webservices/bookrestapp/book/100
	 * After doing the post, use a get command to retrieve the student (and verify that the post was successful).
	 */
	@PUT
	@Path("/book/{id}")
	@Produces("application/json, application/xml")
	@Consumes("application/json, application/xml")
	public Response updateBook(@PathParam("id") int id, Book book) {
		ResponseBuilder respBuilder;
		Book updatedBook;
		
		updatedBook = bookService.updatedBook(id, book);
		//updateStudent(id, newStudent);
		if (updatedBook == null) {
			respBuilder = Response.status(Status.NOT_FOUND);
		} else {
			respBuilder = Response.status(Status.OK);
			respBuilder.entity(updatedBook);
		}
		
		return respBuilder.build();
	}
	/* Test Url:  Use HTTP Delete command
	 * http://localhost:9999/bookStore/webservices/bookrestapp/book/100
	 */
	@DELETE
	@Path("/book/{id}")
	public Response deleteBook(@PathParam("id") int id) {
		int removedBook;
		ResponseBuilder respBuilder;
		
		removedBook = bookService.deleteBook(id);
		if (removedBook == 0) {
			respBuilder = Response.status(Status.NOT_FOUND);
		} else {
			respBuilder = Response.ok();
		}
		return respBuilder.build();
	}
	
}
