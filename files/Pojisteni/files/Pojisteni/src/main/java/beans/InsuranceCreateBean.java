
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
public class InsuranceCreateBean implements Serializable
{
    
    private int personId;
    private String personName;
    private String personSurname;
    private String personPersonalNo;
    
    private final Insurance insurance=new Insurance();
    
    @Inject
    Navigator navigation;
    
    @Inject
    private InsuranceDAO dao;
    
    @Inject()
    private FlashMessageBean flashMessageBean;
    

    public void addNewInsurance() throws IOException
    {
        
        Integer result=dao.addInsuranceToPerson(personId, insurance);

        if (result==null)
        {
            flashMessageBean.add("Chyba: Nepodařilo se přidat pojištění.", FlashMessageType.error);
        }
        else
        {
            flashMessageBean.add("K osobě "+personName+" "+personSurname
                    +" bylo přidáno pojištění č. "+insurance.getContractNo()+".", FlashMessageType.success);
        }

        navigation.redirect("/insurance_list.xhtml");
    }

    
    public Insurance getInsurance()
    {
        return this.insurance;
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
    
}
