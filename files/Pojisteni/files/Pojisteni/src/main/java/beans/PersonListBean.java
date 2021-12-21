
package beans;


import daos.InsuranceDAO;
import daos.PersonDAO;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import models.Insurance;
import models.Person;
import utils.Navigator;


@RequestScoped
@Named
public class PersonListBean
{
    
    @Inject
    private PersonDAO personDAO;

    @Inject
    private InsuranceDAO insuranceDAO;
    
    @Inject
    Navigator navigation;
    
    private List<Person> personList=null;
    
    
    public void loadPersonList()
    {
        this.personList=personDAO.getAllPersons();

        if (this.personList!=null)
        {
            for (Person person:this.personList)
            {
                List<Insurance> insuranceList=insuranceDAO.getInsuranceListForPerson(person.getId());
                person.setInsuranceList(insuranceList);
            }
        }
    }
     
   
    public List<Person> getPersonList()
    {
        return this.personList;
    }
    
}
