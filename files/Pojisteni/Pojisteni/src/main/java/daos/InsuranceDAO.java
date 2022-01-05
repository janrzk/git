
package daos;


import utils.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.annotation.PostConstruct;
import models.Insurance;


@ApplicationScoped
@Named
public class InsuranceDAO
{
    
    @Inject
    private Connector databaseConnector;
    
    private Connection connection;
    
    
    @PostConstruct
    public void initConnection()
    {
        this.connection=databaseConnector.getConnection();
    }


    private void readInsuranceId(ResultSet rs, Insurance insurance) throws SQLException
    {
        insurance.setId(rs.getInt(1));
    }

    
    private void readInsuranceDetails(ResultSet rs, Insurance insurance) throws SQLException
    {
        insurance.setContractNo(rs.getInt(3));
        insurance.setType(rs.getString("typ"));
    }    
    
    private void storeInsuranceDetails(Insurance insurance, PreparedStatement ps) throws SQLException
    {
        ps.setInt(1, insurance.getContractNo());
        ps.setString(2, insurance.getType());
    }
    
    
    public List<Insurance> getInsuranceListForPerson(int personId)
    {
        List insuranceList=null;
        try (PreparedStatement statement=this.connection.prepareStatement("SELECT * FROM pojisteni"
                + " WHERE id_osoba=?"
                + " ORDER BY cislo_smlouvy ASC"))
        {
            statement.setInt(1, personId);
            ResultSet rs=statement.executeQuery();
            insuranceList=new ArrayList();
            if (rs.next())
            {
                do
                {
                    Insurance insurance=new Insurance();
                    readInsuranceId(rs,insurance);
                    readInsuranceDetails(rs,insurance);
                    insuranceList.add(insurance);
                }
                while (rs.next());
            }
        }
        catch (NullPointerException | SQLException e) {}
        return insuranceList;
    }    
    
    
    public Integer addInsuranceToPerson(int personId, Insurance insurance)
    {
        Integer result=null;
        try (PreparedStatement statement=this.connection.prepareStatement("INSERT INTO pojisteni"
                    + " (cislo_smlouvy, typ, id_osoba)"
                    + " VALUES (?, ?, ?)"))
        {
            storeInsuranceDetails(insurance, statement);
            statement.setInt(3, personId);
            result=statement.executeUpdate();
        }
        catch (NullPointerException | SQLException e) {}
        return result;
    }    


    public Integer deleteInsurance(int id)
    {
        Integer result=null;
        try (PreparedStatement statement=this.connection.prepareStatement("DELETE FROM pojisteni"
                    + " WHERE id=?"))
        {
            statement.setInt(1,id);
            result=statement.executeUpdate();
        }
        catch (NullPointerException | SQLException e) {}
        return result;
    }


    public Insurance getInsurance(int id)
    {
        Insurance insurance=null;
        try (PreparedStatement statement=this.connection.prepareStatement("SELECT * FROM pojisteni"
                + " WHERE id=?"))
        {
            statement.setInt(1, id);
            ResultSet rs=statement.executeQuery();
            if (rs.next())
            {
                insurance=new Insurance();
                insurance.setId(id);
                readInsuranceDetails(rs,insurance);
            }
        }
        catch (NullPointerException | SQLException e) {}
        return insurance;
    }
    
    
    public Integer updateInsurance(Insurance insurance)
    {
        Integer result=null;
        try (PreparedStatement statement=this.connection.prepareStatement("UPDATE pojisteni"
                    + " SET cislo_smlouvy=?, typ=?"
                    + " WHERE id=?"))
        {
            storeInsuranceDetails(insurance, statement);
            statement.setInt(3,insurance.getId());
            result=statement.executeUpdate();
        }
        catch (NullPointerException | SQLException e) {}
        return result;
    }    
    
}
