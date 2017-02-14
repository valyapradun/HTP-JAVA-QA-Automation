package com.epam.library.dao;

import com.epam.library.domain.Book;

public interface BookDao {
	
	public boolean addBook(Book newbook) throws BookException;
	public Book getBookByTitle(String title) throws BookException;
	public Book[] getAllBook() throws BookException;
	public Book updateBookByTitle(Book oldBook, String newtitle) throws BookException;
	public boolean deleteBook(Book book) throws BookException;

}
