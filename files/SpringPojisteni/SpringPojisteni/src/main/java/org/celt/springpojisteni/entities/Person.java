
package org.celt.springpojisteni.entities;


import java.io.Serializable;
import static java.lang.System.Logger.Level.ALL;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;


@Entity
@Data
public class Person implements Serializable
{

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private int id;
  
  @Size(max=50, message="Jméno nesmí být delší než 50 znaků.")
  @NotEmpty(message="Nebylo zadáno jméno.")
  private String name;
  
  @Size(max=50, message="Příjmení nesmí být delší než 50 znaků.")
  @NotEmpty(message="Nebylo zadáno příjmení.")
  private String surname;
  
  @Size(max=12, message="Rodné číslo nesmí být delší než 12 znaků.")
  @NotEmpty(message="Nebylo zadáno rodné číslo.")
  private String personalNo;
 
  @NotEmpty(message="Nebyla zadána ulice.")
  private String street;
  
  @NotEmpty(message="Nebyla zadána obec.")
  private String muni;
  
  @Size(max=15, message="Poštovní směrovací číslo nesmí být delší než 10 znaků.")
  @NotEmpty(message="Nebylo zadáno poštovní směrovací číslo.")
  private String postCode;
  
  @NotEmpty(message="Nebyla zadána e-mailová adresa.")
  @Email(message="Zadaná e-mailová adresa není platná.")
  private String email;

  @Size(max=15, message="Telefonní číslo nesmí být delší než 15 znaků.")
  @NotEmpty(message="Nebylo zadáno telefonní číslo.")
  private String phoneNo;
    
  
  @OneToMany(mappedBy="person", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
  private List<Insurance> insuranceList;
    
}
