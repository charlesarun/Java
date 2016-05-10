package com.cs548.bookStore.dao;

import java.util.List;

import com.cs548.bookStore.domain.Book;
import com.cs548.bookStore.exceptions.BookNotFoundException;


public interface BookDAO {
	public int getBookCount();
	public List<Book> findAllBooks();
	public Book findBookById(int id);
	public Book readBookByISBN(String isbn) throws BookNotFoundException;
	public List<Book> findAllBooksByPubId(int publisherId);
	public void insertBook(Book book);
	public int updateBook(Book book);
	public Book updatedBook(int id, Book newBook);
	public int deleteBook(int id);
}
