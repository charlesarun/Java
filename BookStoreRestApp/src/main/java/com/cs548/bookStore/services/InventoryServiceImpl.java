package com.cs548.bookStore.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cs548.bookStore.dao.InventoryDAO;


@Service("inventoryService")
@Transactional(rollbackFor = Exception.class)
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private InventoryDAO invDao;
	@Autowired
	CartService cartService;

	public int getBookStockByISBN(String isbn) {
		// TODO Auto-generated method stub
		return invDao.getBookStockByISBN(isbn);
	
	}
	
}
