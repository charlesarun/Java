package com.cs548.bookStore.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cs548.bookStore.dao.BookDAO;
import com.cs548.bookStore.domain.Book;
import com.cs548.bookStore.exceptions.BookNotFoundException;

@Service("bookService")
@Transactional(readOnly=true)
public class BookServiceImpl implements BookService {
	@Autowired
	private BookDAO bookDao;

	
	@Transactional(readOnly=false)
	public Book createBook(Book book) {
				
		bookDao.insertBook(book);
		
		return book;
	}

	@Transactional(readOnly=false)
	public int updateBook(Book book) {
		// TODO Auto-generated method stub
		int rowsAffected=bookDao.updateBook(book);
		return rowsAffected;
	}

	@Transactional(readOnly=false)
	public int deleteBook(int bookId) {
		int rowsAffected=bookDao.deleteBook(bookId);
		return rowsAffected;
	}

	public Book readBook(int bookId) throws BookNotFoundException {
		// TODO Auto-generated method stub
		Book book = bookDao.findBookById(bookId);
		return book;
	}

	
	public List<Book> getTotalBooksList() {
		
		List<Book> bookList = bookDao.findAllBooks();
		return bookList;
	}

	public int getBookCount() {
		// TODO Auto-generated method stub
		return bookDao.getBookCount();
	}

	public Book readBookByISBN(String isbn) throws BookNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly=false)
	public Book updatedBook(int id, Book newBook) {
		// TODO Auto-generated method stub
		return bookDao.updatedBook(id, newBook);
	}
		


}
