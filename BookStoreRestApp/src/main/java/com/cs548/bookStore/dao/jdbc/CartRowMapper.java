package com.cs548.bookStore.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.cs548.bookStore.domain.Cart;


public class CartRowMapper implements RowMapper<Cart> {

	public Cart mapRow(ResultSet resultSet, int row) throws SQLException {
		Cart cart;
		cart = new Cart();
		cart.setId(resultSet.getInt("id"));
		cart.setIsbn(resultSet.getString("isbn"));
		cart.setQuantity(resultSet.getInt("quantity"));	
		return cart;
	}

}

