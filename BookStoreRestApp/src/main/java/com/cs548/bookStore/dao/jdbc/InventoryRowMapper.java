package com.cs548.bookStore.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.cs548.bookStore.domain.BookInv;


public class InventoryRowMapper implements RowMapper<BookInv> {

	public BookInv mapRow(ResultSet resultSet, int row) throws SQLException {
		BookInv bookinv;
		bookinv = new BookInv();
		bookinv.setId(resultSet.getInt("id"));
		bookinv.setIsbn(resultSet.getString("isbn"));
		bookinv.setInstock(resultSet.getInt("instock"));	
		return bookinv;
	}

}

