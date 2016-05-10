package com.cs548.bookStore.domain;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BookList implements Serializable {
	private static final long serialVersionUID = 1L;
	// XmlElement sets the name of the entities
	@XmlElement(name = "book")
	private List<Book> sList;

	public BookList() {
	}
	
	public List<Book> getSList() {
		return sList;
	}

	public void setStudentList(List<Book> newStudList) {
		this.sList = newStudList;
	}
	
	public int numEntries() {
		if (sList == null) return 0;
		return sList.size();
	}
	
	public Book getStudent(int idx) {
		return sList.get(idx);
	}
	
	public String toString() {
		String listStr;
		
		listStr = "BookList{";
		for (Book entry: sList) {
			listStr = listStr + "\n\t" + entry;
		}
		
		listStr = listStr + "\n}";
		return listStr;
	}
}