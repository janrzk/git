
package org.celt.springpojisteni.entities;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;


@Entity
@Data
public class Insurance
{
  
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private int id;
  
  @NotNull(message="Nebylo zadáno číslo smlouvy.")
  private Integer contractNo;

  @Size(max=50, message="Typ nesmí být delší než 30 znaků.")
  @NotEmpty(message="Nebyl zadán typ pojištění.")
  private String type;  
  
  @ManyToOne(fetch=FetchType.LAZY, optional=false)
  @JoinColumn(name="id_person", nullable=false)
  private Person person;

}
