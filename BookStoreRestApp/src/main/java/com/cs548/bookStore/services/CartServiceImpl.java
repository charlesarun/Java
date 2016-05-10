package com.cs548.bookStore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cs548.bookStore.dao.CartDAO;
import com.cs548.bookStore.exceptions.BookNotFoundException;


@Service("cartService")
@Transactional(readOnly=true, rollbackFor = BookNotFoundException.class)
public class CartServiceImpl implements CartService {
	@Autowired
	private CartDAO cartDAO;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { BookNotFoundException.class },readOnly = false)
	public void bookAddCart(String isbn, int quantity) throws BookNotFoundException{
		cartDAO.bookAddCart(isbn, quantity);		
	}

	public int getBookCartCount() {

		return cartDAO.getBookCartCount();
	}

	@Transactional(readOnly=false, rollbackForClassName="BookNotFoundException")
	public void updateBookCart(String isbn, int quantity) throws BookNotFoundException {
		
		cartDAO.updateBookCart(isbn, quantity);
	}


	
	
}
