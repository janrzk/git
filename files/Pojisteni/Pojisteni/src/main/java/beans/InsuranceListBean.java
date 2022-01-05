
package beans;


import daos.InsuranceDAO;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import models.Insurance;
import utils.Navigator;


@SessionScoped
@Named
public class InsuranceListBean implements Serializable
{
    
    private int personId;
    private String personName;
    private String personSurname;
    private String personPersonalNo;

    @Inject
    private InsuranceDAO dao;
    
    private List<Insurance> insuranceList;
    
    @Inject
    Navigator navigation;    

    
    public void loadInsuranceList()
    {
        this.insuranceList=dao.getInsuranceListForPerson(personId);
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
    
    
    public List<Insurance> getInsuranceList()
    {
        return this.insuranceList;
    }

}
