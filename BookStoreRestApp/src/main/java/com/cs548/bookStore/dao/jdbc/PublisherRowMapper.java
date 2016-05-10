package com.cs548.bookStore.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cs548.bookStore.domain.Publisher;

public class PublisherRowMapper implements RowMapper<Publisher> {

	public Publisher mapRow(ResultSet resultSet, int row) throws SQLException {
		int id;
		String publisherName;
		
		Publisher publisher;
		
		id = resultSet.getInt("id");
		publisherName = resultSet.getString("publisherName");
		
		publisher = new Publisher(id, publisherName);
		
		return publisher;
	}

}
