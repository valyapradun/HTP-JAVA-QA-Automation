package com.epam.library.service;

import com.epam.library.dao.BookDao;
import com.epam.library.dao.BookDaoImpl;
import com.epam.library.dao.BookException;
import com.epam.library.dao.EmployeeBookDao;

import com.epam.library.domain.Book;
import com.epam.library.domain.Employee;

public class BookServiceImpl {
	
	public boolean fillTableEmployeeBook(){
		boolean result = false;
		EmployeeBookDao ebd = new EmployeeBookDao();
		try {
			result = ebd.fillEmployeeBookTable();
		} catch (BookException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public Book[] viewAllBook() {
        BookDao bd = new BookDaoImpl();
        Book[] arrayBook = null;
		try {
			arrayBook = bd.getAllBook();
		} catch (BookException e) {
			System.out.println(e.getMessage());
		}
		return arrayBook;
	}
	
	
	public Book viewOneBook(String title) {
        BookDao bd = new BookDaoImpl();
        Book book = null;
		try {
			book = bd.getBookByTitle(title);
		} catch (BookException e) {
			System.out.println(e.getMessage());
		}
		return book;
	}
	

	public boolean viewAddBook(Book newbook) {
		boolean result = false;
        BookDao bd = new BookDaoImpl();
		try {
			bd.addBook(newbook);
			result = true;
		} catch (BookException e) {
			System.out.println(e.getMessage());
		}
        return result;
	}
	
	public boolean updateBookTitle(String oldTitle, String newTitle) {
		boolean result = false;
        BookDao bd = new BookDaoImpl();
        Book book = null;

		try {
			book = bd.getBookByTitle(oldTitle);
			book = bd.updateBookByTitle(book, newTitle);
			result = true;
		} catch (BookException e) {
			System.out.println(e.getMessage());
		}
        return result;
	}
	
	
	public boolean deleteBook(String title) {
		boolean result = false;
        BookDao bd = new BookDaoImpl();
        Book book = null;
		try {
			book = bd.getBookByTitle(title);
			bd.deleteBook(book);
			result = true;
		} catch (BookException e) {
			System.out.println(e.getMessage());
		}
        return result;
	}
	
	public String[] viewEmployee(int operation) {
        EmployeeBookDao ebd = new EmployeeBookDao();
        String[] arrayEmployee = null;
		try {
			switch (operation){
			case 1: 
				arrayEmployee = ebd.getEmployeeMoreOneBook();
				break;
			case 2:
				arrayEmployee = ebd.getEmployeeLessTwoBook();
			}
		} catch (BookException e) {
			System.out.println(e.getMessage());
		}
		return arrayEmployee;
	}
	
	
	
	
}
