
package beans;


import daos.InsuranceDAO;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import models.FlashMessageType;
import models.Insurance;
import utils.Navigator;


@ViewScoped
@Named
public class InsuranceDetailBean implements Serializable
{
    
    private int id;
    
    private int personId;
    private String personName;
    private String personSurname;
    private String personPersonalNo;
    
    private Insurance insurance;
    
    @Inject
    private Navigator navigation;
    
    @Inject
    private InsuranceDAO dao;
    
    @Inject()
    private FlashMessageBean flashMessageBean;        

    
    public void loadInsurance() throws IOException
    {
        if (this.insurance==null)
        {
            this.insurance=dao.getInsurance(id);
            if (this.insurance==null)
            {
                flashMessageBean.add("Chyba: Detail pojištění nelze načíst.", FlashMessageType.error);
                navigation.redirect("/insurance_list.xhtml");
            }
        }
        
    }
    
    
    public void updateInsurance() throws IOException
    {
        System.out.println(this.insurance.getId());
        
        Integer result=dao.updateInsurance(this.insurance);
                
        if (result==null || result<0)
        {
            flashMessageBean.add("Chyba: Nepodařilo se uložit změny.", FlashMessageType.error);
        }
        else
        {
            flashMessageBean.add("Změny byly uloženy.", FlashMessageType.success);
        }

        navigation.redirect("/insurance_list.xhtml");
    }
    
    
    public void deleteInsurance() throws IOException
    {
        Integer result=dao.deleteInsurance(id);
        
        System.out.println(result);

        if (result==null || result<0)
        {
            flashMessageBean.add("Chyba: Pojištění se nepodařilo smazat.", FlashMessageType.error);
        }
        else
        {
            flashMessageBean.add("Pojištění č. "+insurance.getContractNo()+" "+" bylo vymazáno z databáze.", FlashMessageType.success);
        }
        
        navigation.redirect("/insurance_list.xhtml");        
    }
    
    
    public int getId()
    {
        return id;
    }


    public void setId(int id)
    {
        this.id=id;
    }


    public Insurance getInsurance()
    {
        return insurance;
    }


    public void setInsurance(Insurance insurance)
    {
        this.insurance=insurance;
    }


    public int getPersonId()
    {
        return personId;
    }


    public void setPersonId(int personId)
    {
        this.personId=personId;
    }


    public String getPersonName()
    {
        return personName;
    }


    public void setPersonName(String personName)
    {
        this.personName=personName;
    }


    public String getPersonSurname()
    {
        return personSurname;
    }


    public void setPersonSurname(String personSurname)
    {
        this.personSurname=personSurname;
    }


    public String getPersonPersonalNo()
    {
        return personPersonalNo;
    }


    public void setPersonPersonalNo(String personPersonalNo)
    {
        this.personPersonalNo=personPersonalNo;
    }


    public Navigator getNavigation()
    {
        return navigation;
    }


    public void setNavigation(Navigator navigation)
    {
        this.navigation=navigation;
    }
    
}
