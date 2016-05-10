package com.cs548.bookStore.services;

import java.util.List;

import com.cs548.bookStore.domain.Book;
import com.cs548.bookStore.exceptions.BookNotFoundException;



public interface BookService {
	public Book createBook(Book book);
	public int updateBook(Book book);
	public Book updatedBook(int id, Book newBook);
	public int deleteBook(int bookId);
	public Book readBook(int bookId) throws BookNotFoundException;
	public Book readBookByISBN(String isbn) throws BookNotFoundException;
	public int getBookCount();
	public List<Book> getTotalBooksList();
}
