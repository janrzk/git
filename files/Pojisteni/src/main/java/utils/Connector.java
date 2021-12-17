
package utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;


@ApplicationScoped
@Named
public class Connector
{
    
    private final String url="jdbc:mysql://localhost/pojistovna";
    private final String username="squealer";
    private final String password="snort4oink";
    
    private Connection connection=null;
    
    
    public Connector()
    {
        try
        {
            this.connection=DriverManager.getConnection(url, username, password);
        }
        catch (SQLException e) {}
    }


    @PreDestroy
    public void closeConnection()
    {
        try
        {
            this.connection.close();
        }
        catch (SQLException e) {}
    }
    
    
    public Connection getConnection()
    {
        return this.connection;
    }

}
