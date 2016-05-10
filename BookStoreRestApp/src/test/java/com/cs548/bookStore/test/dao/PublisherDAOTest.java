package com.cs548.bookStore.test.dao;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cs548.bookStore.dao.PublisherDAO;
import com.cs548.bookStore.domain.Publisher;




@ContextConfiguration("classpath:orderdao-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class PublisherDAOTest<ProductDAO> {
	@Autowired
	@Qualifier("publisherDaoJdbc")
	private PublisherDAO publisherDAO;
	
    private Publisher publisher = getPublisherInst();
    
    public Publisher getPublisherInst(){
    	Publisher publisher;
    	publisher = new Publisher();
    	publisher.setPublisherName("Nagarjuna");		
		return publisher;
    }
    
    //Test count of publishers
	@Test
	public void testPublisherCount() {
		int cnt = publisherDAO.getPublisherCount();
		System.out.println(cnt);
	}
	
	//Test insert data
	@Test
	public void testInsertPublisher() {
		publisherDAO.insertPublisher(publisher);
		int id = publisher.getId();
		Publisher pub = publisherDAO.findPublisherById(id);
		assertEquals(pub.getPublisherName(), publisher.getPublisherName());
	
	}
	
}