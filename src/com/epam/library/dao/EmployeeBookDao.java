package com.epam.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.epam.library.domain.Book;
import com.epam.library.domain.Employee;


public class EmployeeBookDao {
	private static final String DRIVER = "jdbc:mysql://localhost:3306/library?useSSL=false";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";
	
	public EmployeeBookDao(){
		
	}
	
	public boolean fillEmployeeBookTable() throws BookException {
		boolean resultFill = false;
		int numberRowRandom = 0;
		numberRowRandom = (int)(100 + (Math.random() * (300 - 100)));
		Connection connection = new UtilDao().connection(DRIVER, USER, PASSWORD);
		if (connection != null){
			Statement query;
			try {
				query = connection.createStatement();
				ResultSet result = query.executeQuery("SELECT * FROM employee ORDER BY RAND() LIMIT 1");
				result.next();
	            int ignoredEmployeeId = result.getInt("ID");   // one user with no Book relations
	            int randomEmployeeId; 
	            int randomBookId;
	            for (int i = 0; i < numberRowRandom; i++){
	            	result = query.executeQuery("SELECT * FROM employee WHERE ID <> "+ ignoredEmployeeId + " ORDER BY RAND() LIMIT 1");
	            	result.next();
	                randomEmployeeId = result.getInt("ID");
	                
	                result = query.executeQuery("SELECT * FROM book ORDER BY RAND() LIMIT 1");
	                result.next();
	                randomBookId = result.getInt("ID");
	            
	                query.executeUpdate("INSERT INTO `library`.`employee_book` (`BOOK_ID`, `EMPLOYEE_ID`) VALUES (" + randomBookId + "," + randomEmployeeId + ")");
	            }
	            resultFill = true;
			} catch (SQLException e) {
				throw new BookException("Library SQL Exception" + e.getMessage() + e);
			} finally {
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						throw new BookException("Not close connection" + e.getMessage() + e);
					}
				}
			}	
		}
		return resultFill;
	}
	
	
	public String[] getEmployeeMoreOneBook() throws BookException {
		Connection connection = new UtilDao().connection(DRIVER, USER, PASSWORD);
		Statement query;
		String[] arrayEmployee;
		try {
			query = connection.createStatement();
			ResultSet result = query.executeQuery("SELECT employee.NAME, COUNT(employee_book.EMPLOYEE_ID) AS NumberBooks "
				                               	+ "FROM employee "
				                               	+ "JOIN employee_book ON employee.ID = employee_book.EMPLOYEE_ID "
				                               	+ "GROUP BY employee.ID "
				                               	+ "ORDER BY NumberBooks;");
			result.last();
	        int size = result.getRow();
	        arrayEmployee = new String[size];
			int i = 0;
			result.beforeFirst();
			while (result.next()) { 
				arrayEmployee[i] = result.getString("NAME") + "\t" +result.getString("NumberBooks"); 
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
		return arrayEmployee;
	}
	
	public String[] getEmployeeLessTwoBook() throws BookException {
		Connection connection = new UtilDao().connection(DRIVER, USER, PASSWORD);
		Statement query;
		String[] arrayEmployee;
		try {
			query = connection.createStatement();
			ResultSet result = query.executeQuery("SELECT employee.NAME, employee.DATE_OF_BIRTH, COUNT(employee_book.EMPLOYEE_ID) AS NumberBooks "
					+ "FROM employee "
					+ "JOIN employee_book ON employee.ID = employee_book.EMPLOYEE_ID "
					+ "GROUP BY employee.ID "
					+ "HAVING NumberBooks <= 2 "
					+ "ORDER BY employee.DATE_OF_BIRTH, NumberBooks;");
			result.last();
	        int size = result.getRow();
	        arrayEmployee = new String[size];
			int i = 0;
			result.beforeFirst();
			while (result.next()) { 
				arrayEmployee[i] = result.getString("NAME") + "\t" + result.getString("DATE_OF_BIRTH") + "\t" + result.getString("NumberBooks"); 
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
		return arrayEmployee;
	}
	

}
