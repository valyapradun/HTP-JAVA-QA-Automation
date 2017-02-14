package com.epam.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UtilDao {
	public Connection connection(String driver, String user, String password) throws BookException{
		Connection connect = null;
		try {
		connect = DriverManager.getConnection(driver, user, password);
		  if (connect == null) {
              System.out.println("There is no connection with a DB!");
              System.exit(0);
          }
		} catch (SQLException e){
			throw new BookException("SQL Exception!" + e.getMessage() + e);
		} 
		return connect;
	}
}
