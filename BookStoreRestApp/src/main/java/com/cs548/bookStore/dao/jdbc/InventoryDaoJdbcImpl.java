package com.cs548.bookStore.dao.jdbc;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.cs548.bookStore.dao.InventoryDAO;
import com.cs548.bookStore.domain.Book;
import com.cs548.bookStore.domain.BookInv;

@Repository("BookInvDaoJdbc")
public class InventoryDaoJdbcImpl implements InventoryDAO {

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedTemplate;
	private SimpleJdbcInsert jdbcInsert;
	private InventoryRowMapper bookInvRowMapper;
	

	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		namedTemplate = new NamedParameterJdbcTemplate(dataSource);
		jdbcInsert = new SimpleJdbcInsert(dataSource)
							.withTableName("inventory")
							.usingGeneratedKeyColumns("id");
		bookInvRowMapper = new InventoryRowMapper();

	}
	
	public int getBookStockByISBN(String isbn) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM inventory WHERE isbn = :isbn";
		MapSqlParameterSource params = new MapSqlParameterSource("isbn", isbn);
		BookInv book = namedTemplate.queryForObject(sql, params, bookInvRowMapper);
		return book.getInstock();
	}

	public BookInv findBookById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<BookInv> findAllBooksInInventory() {
		// TODO Auto-generated method stub
		return null;
	}

	public void insertBookInInventory(BookInv bookInv) {
		// TODO Auto-generated method stub

	}

	public int updateBookStock(String isbn, int stock) {
		// TODO Auto-generated method stub
		return 0;
	}

}
