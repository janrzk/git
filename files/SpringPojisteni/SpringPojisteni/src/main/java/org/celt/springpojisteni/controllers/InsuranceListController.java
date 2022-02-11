
package org.celt.springpojisteni.controllers;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.celt.springpojisteni.entities.Insurance;
import org.celt.springpojisteni.entities.Person;
import org.celt.springpojisteni.repositories.InsuranceRepository;
import org.celt.springpojisteni.repositories.PersonRepository;
import org.celt.springpojisteni.utils.FlashMessage;
import org.celt.springpojisteni.utils.FlashMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@SessionAttributes("person")
@Controller
public class InsuranceListController
{
  
  @Autowired
  private PersonRepository personRepo;
  
  @Autowired
  private InsuranceRepository insuranceRepo;
  
  
  private List<Insurance> insuranceList;
 
  
  @GetMapping("/insurance_list")
  public String viewInsuranceList(Model model,
      @RequestParam(value="personId") Optional<Integer> personId,
      RedirectAttributes redirectAttributes,
      SessionStatus status
  )
  {
    Person person;

    if (personId.isPresent())
    {
      try
      {
        person=personRepo.findById(personId.get()).get();
        model.addAttribute("person",person);
      }
      catch (DataAccessException | NoSuchElementException e)
      {
        redirectAttributes.addFlashAttribute("messages",new FlashMessage("Chyba: Nepodařilo se načíst data osoby.",
            FlashMessageType.error));
        status.setComplete();
        
        return "redirect:/insurance_list";
      }
      
      insuranceList=(List)insuranceRepo.findByPerson(person, Sort.by(Sort.Direction.ASC,"contractNo"));
      model.addAttribute("insuranceItems",insuranceList);
      
      return "insurance_list";
    }
    
    return "insurance_list";
  }

}
