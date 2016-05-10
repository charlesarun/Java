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

import com.cs548.bookStore.dao.PublisherDAO;
import com.cs548.bookStore.domain.Publisher;

@Repository("publisherDaoJdbc")
public class PublisherDaoJdbcImpl implements PublisherDAO {
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate dbTemplate;
	private SimpleJdbcInsert jdbcInsert;
	private PublisherRowMapper publisherRowMapper;
	
	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		dbTemplate = new NamedParameterJdbcTemplate(dataSource);
		publisherRowMapper = new PublisherRowMapper();
		jdbcInsert = new SimpleJdbcInsert(dataSource)
		                 .withTableName("publishers")
		                 .usingGeneratedKeyColumns("id")
		                 .usingColumns("publisherName");
	}
	

	public int getPublisherCount() {
		String sql = "select count(*) FROM publishers";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public String findPublisherNameById(int id) {
		// TODO Auto-generated method stub
		return findPublisherById(id).getPublisherName();
	}

	public Publisher findPublisherById(int publisherId) {
		String sql = "SELECT * FROM publishers WHERE id = :id";
		MapSqlParameterSource params = new MapSqlParameterSource("id", publisherId);
		Publisher publisher = dbTemplate.queryForObject(sql, params, publisherRowMapper);

		return publisher;
	}

	public void insertPublisher(Publisher pub) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(pub);
	    Number newId = jdbcInsert.executeAndReturnKey(params);
	    
	    pub.setId(newId.intValue());
		
	}

	public List<Publisher> findAllPublishers() {
		String sql = "SELECT * FROM publishers";
		List<Publisher> publisherList = jdbcTemplate.query(sql, publisherRowMapper);
		return publisherList;
	}
	
}
