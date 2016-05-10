package com.cs548.bookStore.dao;

import java.util.List;

import com.cs548.bookStore.domain.Publisher;


public interface PublisherDAO {
	public int getPublisherCount();
	public String findPublisherNameById(int id);
	public Publisher findPublisherById(int publisherId);
	public void insertPublisher(Publisher pub);
	public List<Publisher> findAllPublishers();
	
}
