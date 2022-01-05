
package beans;


import daos.PersonDAO;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import models.FlashMessageType;
import models.Person;
import utils.Navigator;


@ViewScoped
@Named
public class PersonDetailBean implements Serializable
{
    
    private int personId;
    
    private Person person;
    
    @Inject
    Navigator navigation;
    
    @Inject
    private PersonDAO dao;
    
    @Inject()
    private FlashMessageBean flashMessageBean;
    

    public void loadPerson() throws IOException
    {
        if (this.person==null)
        {
            this.person=dao.getPerson(this.personId);
            if (this.person==null)
            {
                flashMessageBean.add("Chyba: Detail osoby nelze načíst.", FlashMessageType.error);
                navigation.redirect("/person_list.xhtml");
            }
        }
    }


    public void updatePerson() throws IOException
    {
        Integer result=dao.updatePerson(this.person);
                
        if (result==null || result<0)
        {
            flashMessageBean.add("Chyba: Nepodařilo se uložit změny.", FlashMessageType.error);
        }
        else
        {
            flashMessageBean.add("Změny byly uloženy.", FlashMessageType.success);
        }

        navigation.redirect("/person_list.xhtml");
    }       

    
    public void deletePerson() throws IOException
    {
        Integer result=dao.deletePerson(this.personId);

        if (result==null || result<0)
        {
            flashMessageBean.add("Chyba: Osobu se nepodařilo smazat.", FlashMessageType.error);
        }
        else
        {
            flashMessageBean.add("Z databáze byla vymazána osoba "+person.getName()+" "+person.getSurname()
                    +" a všechna její pojištění.", FlashMessageType.success);
        }
        
        navigation.redirect("/person_list.xhtml");
    }       
    
    
    public int getPersonId()
    {
        return this.personId;
    }
    
    
    public void setPersonId(int id)
    {
        this.personId=id;
    }

    
    public Person getPerson()
    {
        return this.person;
    }
    
    public void setPerson(Person person)
    {
        this.person=person;
    }
    
}
