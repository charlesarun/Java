package com.cs548.bookStore.domain;

public class Book {
	private int id;
	private int publisherId;
	private String isbn;
	private String bookTitle;
	private float price;
	
	public Book(){
		
	}
	public Book(int id, int publisherId, String isbn, String bookTitle, float price) {
		this.id = id;
		this.publisherId = publisherId;
		this.isbn = isbn;
		this.bookTitle = bookTitle;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", publisherId=" + publisherId + ", isbn=" + isbn + ", bookTitle=" + bookTitle
				+ ", price=" + price + "]";
	}
	
	
}
