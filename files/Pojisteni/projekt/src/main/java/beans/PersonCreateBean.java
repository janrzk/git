
package beans;


import daos.PersonDAO;
import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import models.FlashMessageType;

import models.Person;
import utils.Navigator;


@RequestScoped
@Named
public class PersonCreateBean
{

    private final Person person=new Person();
    
    @Inject
    Navigator navigation;
    
    @Inject
    private PersonDAO dao;
    
    @Inject()
    private FlashMessageBean flashMessageBean;
    

    public void addNewPerson() throws IOException
    {
        String existingPerson=dao.checkForPersonalNo(person.getPersonalNo());
        
        if (!existingPerson.isEmpty())
        {
            flashMessageBean.add("Chyba: Rodné číslo "+person.getPersonalNo()
                    +" je v systému již přiřazeno osobě "+existingPerson+"!", FlashMessageType.error);
        }
        else
        {
            Integer result=dao.addPerson(person);

            if (result==null)
            {
                flashMessageBean.add("Chyba: Nepodařilo se přidat osobu.", FlashMessageType.error);
            }
            else
            {
                flashMessageBean.add("Do databáze byla přidána osoba "
                        +person.getName()+" "+person.getSurname()+".", FlashMessageType.success);
            }
        }

        navigation.redirect("/person_list.xhtml");
    }

    
    public Person getPerson()
    {
        return this.person;
    }
    
}
