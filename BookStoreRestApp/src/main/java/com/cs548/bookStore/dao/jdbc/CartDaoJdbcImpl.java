package com.cs548.bookStore.dao.jdbc;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.cs548.bookStore.dao.BookDAO;
import com.cs548.bookStore.dao.CartDAO;
import com.cs548.bookStore.dao.InventoryDAO;
import com.cs548.bookStore.domain.Book;
import com.cs548.bookStore.domain.BookInv;
import com.cs548.bookStore.domain.Cart;
import com.cs548.bookStore.exceptions.BookNotFoundException;


@Repository("CartDaoJdbc")
public class CartDaoJdbcImpl implements CartDAO {

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	@Autowired
	@Qualifier("BookDaoJdbc")
	private BookDAO bookDao;
	
	
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedTemplate;
	private SimpleJdbcInsert jdbcInsert;
	private CartRowMapper cartRowMapper;
	

	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		namedTemplate = new NamedParameterJdbcTemplate(dataSource);
		jdbcInsert = new SimpleJdbcInsert(dataSource)
							.withTableName("cart")
							.usingGeneratedKeyColumns("id");
		cartRowMapper = new CartRowMapper();

	}
	
	public int getBookStockByISBN(String isbn) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM cart WHERE isbn =:isbn";
		MapSqlParameterSource params = new MapSqlParameterSource("isbn", isbn);
		Cart cart = namedTemplate.queryForObject(sql, params, cartRowMapper);
		if(cart==null){
			return 0;
		}
		return cart.getQuantity();
	}

	public BookInv findBookById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateBookCart(String isbn, int quantity) throws BookNotFoundException {
		String sql = "update cart set quantity=:quantity where isbn=:isbn";
		
		MapSqlParameterSource params;
		int rowsAffected;
		
		
		params = new MapSqlParameterSource("isbn", isbn);
		
		params.addValue("quantity", quantity);
		rowsAffected = namedTemplate.update(sql, params);
		return rowsAffected;
	}

	
	public void bookAddCart(String isbn, int quantity) throws BookNotFoundException {
		Cart cart = new Cart();
		cart.setIsbn(isbn);
		cart.setQuantity(quantity);
		SqlParameterSource params = new BeanPropertySqlParameterSource(cart);
		Number newId = jdbcInsert.executeAndReturnKey(params);
		
		cart.setId(newId.intValue());
		Book book;
		try{
			book=bookDao.readBookByISBN(isbn);
		
		}
		catch(EmptyResultDataAccessException ex){
			throw new BookNotFoundException("Book not found");
		}
		
	}

	public int getBookCartCount() {
		String sql = "select count(*) from cart";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public Cart findBookByISBN(String isbn) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM cart WHERE isbn =:isbn";
		MapSqlParameterSource params = new MapSqlParameterSource("isbn", isbn);
		Cart cart = namedTemplate.queryForObject(sql, params, cartRowMapper);
		return cart;
	}

	public int findBookInCart(String isbn) {
		String sql = "select * from cart WHERE isbn =:isbn";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
}
