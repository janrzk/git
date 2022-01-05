
package beans;


import daos.ConnectionTestDAO;
import java.sql.Connection;
import java.sql.SQLException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;


@RequestScoped
@Named
public class ConnectionTestBean
{
    
    @Inject
    private ConnectionTestDAO dao;
    
    
    public Connection getConnection()
    {
        Connection connection=null;
        
        try
        {
            connection=dao.getConnection();
        }
        catch (SQLException e) {}
        
        return connection;
    }

}
