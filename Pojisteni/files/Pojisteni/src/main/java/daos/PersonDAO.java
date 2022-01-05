
package daos;


import utils.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import models.Person;
import java.util.List;
import java.util.ArrayList;
import javax.annotation.PostConstruct;


@ApplicationScoped
@Named
public class PersonDAO
{
    
    @Inject
    private Connector databaseConnector;
    
    private Connection connection;
    
    
    @PostConstruct
    public void initConnection()
    {
        this.connection=databaseConnector.getConnection();
    }
    
    
    private void readPersonId(ResultSet rs, Person person) throws SQLException
    {
        person.setId(rs.getInt(1));
    }

    
    private void readPersonDetails(ResultSet rs, Person person) throws SQLException
    {
        person.setName(rs.getString("jmeno"));
        person.setSurname(rs.getString("prijmeni"));
        person.setPersonalNo(rs.getString("rodne_cislo"));
        person.setStreet(rs.getString("ulice"));
        person.setMuni(rs.getString("obec"));
        person.setPostCode(rs.getString("psc"));
        person.setEmail(rs.getString("email"));
        person.setPhoneNo(rs.getString("telefon"));
    }
    
    
    private void storePersonDetails(Person person, PreparedStatement ps) throws SQLException
    {
        ps.setString(1, person.getName());
        ps.setString(2, person.getSurname());
        ps.setString(3, person.getPersonalNo());
        ps.setString(4, person.getStreet());
        ps.setString(5, person.getMuni());
        ps.setString(6, person.getPostCode());
        ps.setString(7, person.getEmail());
        ps.setString(8, person.getPhoneNo());
    }

    
    public Person getPerson(int id)
    {
        Person person=null;
        try (PreparedStatement statement=this.connection.prepareStatement("SELECT * FROM osoby WHERE id=?"))
        {
            statement.setInt(1,id);
            ResultSet rs=statement.executeQuery();
            if (rs.next())
            {
                person=new Person();
                person.setId(id);
                do
                {
                    readPersonDetails(rs,person);
                }
                while (rs.next());
            }
        }
        catch (NullPointerException | SQLException e) {}
        return person;
    }

    
    public List<Person> getAllPersons()
    {
        List persons=null;
        try (PreparedStatement statement=this.connection.prepareStatement("SELECT * FROM osoby"
                + " ORDER BY prijmeni ASC, jmeno ASC"))
        {
            ResultSet rs=statement.executeQuery();
            persons=new ArrayList();
            if (rs.next())
            {
                do
                {
                    Person person=new Person();
                    readPersonId(rs,person);
                    readPersonDetails(rs,person);
                    persons.add(person);
                }
                while (rs.next());
            }
            
        }
        catch (NullPointerException | SQLException e) {}
        return persons;
    }
    
    
    public Integer addPerson(Person person)
    {
        Integer result=null;
        try (PreparedStatement statement=this.connection.prepareStatement("INSERT INTO osoby"
                    + " (jmeno, prijmeni, rodne_cislo, ulice, obec, psc, email, telefon)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)"))
        {
            storePersonDetails(person, statement);
            result=statement.executeUpdate();
        }
        catch (NullPointerException | SQLException e) {}
        return result;
    }
    
    
    public Integer updatePerson(Person person)
    {
        Integer result=null;
        try (PreparedStatement statement=this.connection.prepareStatement("UPDATE osoby"
                    + " SET jmeno=?, prijmeni=?, rodne_cislo=?, ulice=?, obec=?, psc=?, email=?, telefon=?"
                    + " WHERE id=?"))
        {
            storePersonDetails(person, statement);
            statement.setInt(9,person.getId());
            result=statement.executeUpdate();
        }
        catch (NullPointerException | SQLException e) {}
        return result;
    }

    
    public Integer deletePerson(int id)
    {
        Integer result=null;
        try (PreparedStatement statement=this.connection.prepareStatement("DELETE FROM osoby"
                    + " WHERE id=?"))
        {
            
            statement.setInt(1,id);
            result=statement.executeUpdate();
        }
        catch (NullPointerException | SQLException e) {}
        return result;
    }
    
    
    public String checkForPersonalNo(String personalNo)
    {
        String name="";
        try (PreparedStatement statement=this.connection.prepareStatement("SELECT jmeno, prijmeni FROM osoby"
                + " WHERE rodne_cislo=?"))
        {
            statement.setString(1,personalNo);
            ResultSet rs=statement.executeQuery();
            if (rs.next())
            {
                name=rs.getString("jmeno")+" "+rs.getString("prijmeni");
            }
        }
        catch (NullPointerException | SQLException e) {}
        return name;
    }    

}
