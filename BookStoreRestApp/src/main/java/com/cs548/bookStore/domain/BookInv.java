package com.cs548.bookStore.domain;

public class BookInv {

	int id;
	String isbn;
	int instock;
	
	public BookInv(){
		
	}
	public BookInv(int id, String isbn, int instock) {
		this.id = id;
		this.isbn = isbn;
		this.instock = instock;
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
	public int getInstock() {
		return instock;
	}
	public void setInstock(int instock) {
		this.instock = instock;
	}
	@Override
	public String toString() {
		return "Inventory [id=" + id + ", isbn=" + isbn + ", instock=" + instock + "]";
	}
	
	
	
}
