

package daos;


import java.sql.Connection;
import java.sql.SQLException;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import utils.Connector;


@ApplicationScoped
@Named
public class ConnectionTestDAO
{
    
    @Inject
    private Connector databaseConnector;

    
    public Connection getConnection() throws SQLException
    {
        return databaseConnector.getConnection();
    }

}
