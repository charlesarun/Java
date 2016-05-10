package com.cs548.bookStore.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.cs548.bookStore.domain.Book;


public class BookRowMapper implements RowMapper<Book> {

	public Book mapRow(ResultSet resultSet, int row) throws SQLException {
		Book book;
		
		book = new Book();
		
		book.setId(resultSet.getInt("id"));
		book.setPublisherId(resultSet.getInt("publisherId"));
		book.setPrice(resultSet.getFloat("price"));
		book.setBookTitle(resultSet.getString("bookTitle"));
		book.setIsbn(resultSet.getString("isbn"));
		
		return book;
	}

}
