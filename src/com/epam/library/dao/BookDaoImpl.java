package com.epam.library.dao;
	
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.epam.library.domain.Book;

public class BookDaoImpl implements BookDao{
		
	private static final String DRIVER = "jdbc:mysql://localhost:3306/library?useSSL=false";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";
		
	public BookDaoImpl(){
			
	}
		
	@Override
	public Book[] getAllBook() throws BookException{
		Connection connection = new UtilDao().connection(DRIVER, USER, PASSWORD);
		Statement query;
		Book[] arrayBook;
		try {
			query = connection.createStatement();
			ResultSet result = query.executeQuery("SELECT * FROM book");
			result.last();
	        int size = result.getRow();
			arrayBook = new Book[size];
			int i = 0;
			result.beforeFirst();
			while (result.next()) { 
				arrayBook[i] = new Book(result.getString("TITLE"), result.getString("AUTHOR"), result.getInt("PUBLISH_YEAR"));
	            i++;                
	        }
		} catch (SQLException e) {
			throw new BookException("SQL Exception" + e.getMessage() + e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new BookException("SQL Exception" + e.getMessage() + e);
				}
			}
		}
		return arrayBook;
	}

	@Override
	public Book getBookByTitle(String title) throws BookException {
		Book book = null;
		if (title != null) {
			Connection connection = new UtilDao().connection(DRIVER, USER, PASSWORD);
			if (connection != null){
				Statement query;
				try {
					query = connection.createStatement();
					ResultSet result = query.executeQuery("SELECT ID, TITLE, AUTHOR, PUBLISH_YEAR FROM book WHERE TITLE LIKE'" + title + "%';");
					if (result.next()) {
						book = new Book(result.getString("TITLE"), result.getString("AUTHOR"), result.getInt("PUBLISH_YEAR"));
					} else {
						throw new BookException ("Book not found!");
					}
				} catch (SQLException e) {
					throw new BookException("SQL Exception" + e.getMessage() + e);
				} finally {
					if (connection != null) {
						try {
							connection.close();
						} catch (SQLException e) {
							throw new BookException("SQL Exception" + e.getMessage() + e);
						}
					}
				}	
			}
		}
		return book;
	}

	@Override
	public boolean addBook(Book newbook) throws BookException {
		boolean resultAdd = false;
		if (newbook != null) {
			Connection connection = new UtilDao().connection(DRIVER, USER, PASSWORD);
			if (connection != null){
				Statement query;
				try {
					query = connection.createStatement();
					int result = query.executeUpdate("INSERT INTO book(`TITLE`, `PUBLISH_YEAR`, `AUTHOR`) VALUES ('" + newbook.getTitle() + "', '" + newbook.getYearPublishing() + "', '" + newbook.getAuthor() + "');");
					if (result != 0) {
						resultAdd = true;
					}	
				} catch (SQLException e) {
					throw new BookException("SQL Exception" + e.getMessage() + e);
				} finally {
					if (connection != null) {
						try {
							connection.close();
						} catch (SQLException e) {
							throw new BookException("SQL Exception" + e.getMessage() + e);
						}
					}
				}	
			}	
		}
		return resultAdd;
	}
		
		
	@Override
	public Book updateBookByTitle(Book oldbook, String newtitle) throws BookException{
		Book newBook = null;
		if ((newtitle != null) & (oldbook != null)) {
			Connection connection = new UtilDao().connection(DRIVER, USER, PASSWORD);
			if (connection != null){
				Statement query;
				String q = "UPDATE book SET TITLE = '" + newtitle + "' WHERE TITLE LIKE '" + oldbook.getTitle() + "%' and ID > 0;";
				try {
					query = connection.createStatement();

					int result = query.executeUpdate(q);
					if (result != 0) {
						newBook = getBookByTitle(newtitle);
					}	
				} catch (SQLException e) {
					throw new BookException("SQL Exception" + e.getMessage() + e);
				} finally {
					if (connection != null) {
						try {
							connection.close();
						} catch (SQLException e) {
							throw new BookException("SQL Exception" + e.getMessage() + e);
						}
					}
				}		
			}
		}
		return newBook;
	}

	@Override
	public boolean deleteBook(Book book) throws BookException {
		boolean resultAdd = false;
		if (book != null) {
			Connection connection = new UtilDao().connection(DRIVER, USER, PASSWORD);
			if (connection != null){
				Statement query;
				try {
					query = connection.createStatement();
					int result = query.executeUpdate("DELETE FROM book WHERE TITLE = '" + book.getTitle() + "';");
					if (result != 0) {
						resultAdd = true;
					}	
				} catch (SQLException e) {
					throw new BookException("SQL Exception" + e.getMessage() + e);
				} finally {
					if (connection != null) {
						try {
							connection.close();
						} catch (SQLException e) {
							throw new BookException("SQL Exception" + e.getMessage() + e);
						}
					}
				}	
			}	
		}
		return resultAdd;
	}
}

