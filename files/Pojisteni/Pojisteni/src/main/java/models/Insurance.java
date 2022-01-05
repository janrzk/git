
package models;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class Insurance
{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @NotNull(message="Nebylo zadáno číslo smlouvy.")
    private Integer contractNo;
    
    @Size(max=50, message="Typ nesmí být delší než 30 znaků.")
    @NotEmpty(message="Nebyl zadán typ pojištění.")
    @NotNull(message="Nebyl zadán typ pojištění.")
    private String type;


    public int getId()
    {
        return id;
    }


    public void setId(int id)
    {
        this.id=id;
    }


    public Integer getContractNo()
    {
        return contractNo;
    }


    public void setContractNo(Integer contractNo)
    {
        this.contractNo=contractNo;
    }


    public String getType()
    {
        return type;
    }


    public void setType(String type)
    {
        this.type=type;
    }
    
}
