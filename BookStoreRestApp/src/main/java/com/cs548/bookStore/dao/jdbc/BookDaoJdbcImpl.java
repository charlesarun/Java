package com.cs548.bookStore.dao.jdbc;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.cs548.bookStore.dao.BookDAO;
import com.cs548.bookStore.domain.Book;
import com.cs548.bookStore.exceptions.BookNotFoundException;


@Repository("BookDaoJdbc")
public class BookDaoJdbcImpl implements BookDAO {
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedTemplate;
	private SimpleJdbcInsert jdbcInsert;
	private BookRowMapper bookRowMapper;
	
	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		namedTemplate = new NamedParameterJdbcTemplate(dataSource);
		jdbcInsert = new SimpleJdbcInsert(dataSource)
							.withTableName("books")
							.usingGeneratedKeyColumns("id");
		bookRowMapper = new BookRowMapper();

	}
	

	public int getBookCount() {
		String sql = "select count(*) from books";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public List<Book> findAllBooks() {
		String sql = "SELECT * FROM books";
		List<Book> BookList = jdbcTemplate.query(sql, bookRowMapper);
		return BookList;
	}

	public Book findBookById(int id) {
		String sql = "SELECT * FROM books WHERE id =:id";
		MapSqlParameterSource params = new MapSqlParameterSource("id", id);
		Book book = namedTemplate.queryForObject(sql, params, bookRowMapper);

		return book;
	}

	public List<Book> findAllBooksByPubId(int publisherId) {
		String sql = "SELECT * FROM books WHERE publisherId=?";
	
		List<Book> bookList = jdbcTemplate.queryForList(sql, Book.class, publisherId);

		return bookList;
	}

	public void insertBook(Book book) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(book);
		Number newId = jdbcInsert.executeAndReturnKey(params);
		
		book.setId(newId.intValue());
		
	}
	public int updateBook(Book book) {
		String sql = "update books set publisherId=:publisherId, isbn=:isbn, bookTitle=:bookTitle, price=:price where id=:id";
		
		MapSqlParameterSource params;
		int rowsAffected;
		int bookId = book.getId();
		
		int publisherId = book.getPublisherId();
		String isbn = book.getIsbn(), bookTitle = book.getBookTitle();
		float price = book.getPrice();
		params = new MapSqlParameterSource("id", bookId);
		
		params.addValue("publisherId", publisherId);
		params.addValue("isbn", isbn);
		params.addValue("bookTitle", bookTitle);
		params.addValue("price", price);
		rowsAffected = namedTemplate.update(sql, params);
		return rowsAffected;
		
	}

	public int deleteBook(int id) {
	
		String sql = "delete from books WHERE id=:id";
		MapSqlParameterSource params;
		int rowsAffected;
		params = new MapSqlParameterSource("id", id);
		rowsAffected = namedTemplate.update(sql, params);
		return rowsAffected;
	}


	public Book readBookByISBN(String isbn) throws BookNotFoundException {
		String sql = "SELECT * FROM books WHERE isbn =:isbn";
		MapSqlParameterSource params = new MapSqlParameterSource("isbn", isbn);
		Book book = namedTemplate.queryForObject(sql, params, bookRowMapper);

		return book;
	}


	@Override
	public Book updatedBook(int id, Book newBook) {
		String sql = "update books set publisherId=:publisherId, isbn=:isbn, bookTitle=:bookTitle, price=:price where id=:id";
		
		MapSqlParameterSource params;
		int rowsAffected;
		
		
		int publisherId = newBook.getPublisherId();
		String isbn = newBook.getIsbn(), bookTitle = newBook.getBookTitle();
		float price = newBook.getPrice();
		params = new MapSqlParameterSource("id", id);
		
		params.addValue("publisherId", publisherId);
		params.addValue("isbn", isbn);
		params.addValue("bookTitle", bookTitle);
		params.addValue("price", price);
		rowsAffected = namedTemplate.update(sql, params);
		Book book = findBookById(id);
		return book;
		
	}


	
}
