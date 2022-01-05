
package org.celt.kopidlno.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connector
{
  
  private static Connection connection=null;
  
  
  public static void openConnection(String url, String username, String password)
      throws SQLException
  {
    connection=DriverManager.getConnection(url, username, password);
  }
  
  
  public static void closeConnection()
      throws SQLException
  {
    connection.close();
  }
  
  
  public static Connection getConnetion()
  {
    return connection;
  }
  
}
