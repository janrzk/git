
package models;


import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class Person
{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Size(max=50, message="Jméno nesmí být delší než 50 znaků.")
    @NotEmpty(message="Nebylo zadáno jméno.")
    @NotNull(message="Nebylo zadáno jméno.")
    private String name;
    
    @Size(max=50, message="Příjmení nesmí být delší než 50 znaků.")
    @NotEmpty(message="Nebylo zadáno příjmení.")
    @NotNull(message="Nebylo zadáno příjmení.")
    private String surname;

    @Size(max=12, message="Rodné číslo nesmí být delší než 12 znaků.")
    @NotEmpty(message="Nebylo zadáno rodné číslo.")
    @NotNull(message="Nebylo zadáno rodné číslo.")
    private String personalNo;

    @NotEmpty(message="Nebyla zadána ulice.")
    @NotNull(message="Nebyla zadána ulice.")
    private String street;

    @NotEmpty(message="Nebyla zadána obec.")
    @NotNull(message="Nebyla zadána obec.")
    private String muni;

    @Size(max=15, message="Poštovní směrovací číslo nesmí být delší než 10 znaků.")
    @NotEmpty(message="Nebylo zadáno poštovní směrovací číslo.")
    @NotNull(message="Nebylo zadáno poštovní směrovací číslo.")
    private String postCode;

    @NotEmpty(message="Nebyla zadána e-mailová adresa.")
    @NotNull(message="Nebyla zadána e-mailová adresa.")
    @Email(message="Zadaná e-mailová adresa není platná.")
    private String email;

    @Size(max=15, message="Telefonní číslo nesmí být delší než 15 znaků.")
    @NotEmpty(message="Nebylo zadáno telefonní číslo.")
    @NotNull(message="Nebylo zadáno telefonní číslo.")
    private String phoneNo;
    
    
    private List<Insurance> insuranceList;


    public int getId()
    {
        return id;
    }
    
    
    public void setId(int id)
    {
        this.id=id;
    }

    
    public String getName()
    {
        return name;
    }
    

    public void setName(String name)
    {
        this.name=name;
    }

    
    public String getSurname()
    {
        return surname;
    }
    

    public void setSurname(String surname)
    {
        this.surname=surname;
    }


    public String getPersonalNo()
    {
        return personalNo;
    }


    public void setPersonalNo(String personalNo)
    {
        this.personalNo=personalNo;
    }


    public String getStreet()
    {
        return street;
    }


    public void setStreet(String street)
    {
        this.street=street;
    }


    public String getMuni()
    {
        return muni;
    }


    public void setMuni(String muni)
    {
        this.muni=muni;
    }


    public String getPostCode()
    {
        return postCode;
    }


    public void setPostCode(String postCode)
    {
        this.postCode=postCode;
    }


    public String getEmail()
    {
        return email;
    }


    public void setEmail(String email)
    {
        this.email=email;
    }


    public String getPhoneNo()
    {
        return phoneNo;
    }


    public void setPhoneNo(String phoneNo)
    {
        this.phoneNo=phoneNo;
    }


    public List<Insurance> getInsuranceList()
    {
        return insuranceList;
    }


    public void setInsuranceList(List<Insurance> insuranceList)
    {
        this.insuranceList=insuranceList;
    }
    
}
