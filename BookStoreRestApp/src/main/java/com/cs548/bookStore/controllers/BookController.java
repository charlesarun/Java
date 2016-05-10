package com.cs548.bookStore.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cs548.bookStore.domain.Book;
import com.cs548.bookStore.services.BookService;


/*
     To get to the home page, point your browser to:  http://localhost:9999/bookStore/
     or get the new book data form presented directly by going to:  http://localhost:9999/bookStore/newBookDataForm
*/

@Controller
public class BookController {
	@Autowired
	BookService bookService;
	
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	// Present the book data form
	@RequestMapping(value = "/newBookDataForm", method = RequestMethod.GET)
	public ModelAndView newStudentDataForm() {
		ModelAndView modelView;
		
 		modelView = new ModelAndView("bookDataForm");
 		modelView.addObject("book", new Book());
		return modelView;
	}
	
	// Process the data the user has entered in the book data form
	@RequestMapping(value = "/processNewBookProfile", method = RequestMethod.POST)
	public ModelAndView processNewStudentForm(@ModelAttribute("book") @Valid Book book, BindingResult result, HttpSession session) 
	{
		ModelAndView modelView;
		
		if (result.hasErrors()) {
			/*  Re-present the form with error messages */
			modelView = new ModelAndView("bookDataForm");
			return modelView;
		}
		
		bookService.createBook(book);
 		modelView = new ModelAndView("newBookProfileSuccess");
 		session.setAttribute("book", book);
 		modelView.addObject("book", book);
		
		return modelView;
	}
	
}
