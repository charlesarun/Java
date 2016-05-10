package com.cs548.bookStore.domain;

public class Cart {

	int id;
	String isbn;
	int quantity;
	
	public Cart(){
		
	}

	public Cart(int id, String isbn, int quantity) {
		this.id = id;
		this.isbn = isbn;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", isbn=" + isbn + ", quantity=" + quantity + "]";
	}
	
	
	
	
}
