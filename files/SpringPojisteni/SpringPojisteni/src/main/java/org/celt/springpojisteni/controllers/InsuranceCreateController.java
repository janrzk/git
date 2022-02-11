
package org.celt.springpojisteni.controllers;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.validation.Valid;
import org.celt.springpojisteni.entities.Insurance;
import org.celt.springpojisteni.entities.Person;
import org.celt.springpojisteni.repositories.InsuranceRepository;
import org.celt.springpojisteni.repositories.PersonRepository;
import org.celt.springpojisteni.utils.FlashMessage;
import org.celt.springpojisteni.utils.FlashMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@SessionAttributes("person")
@Controller
public class InsuranceCreateController
{
  
  
  @Autowired
  private PersonRepository personRepo;
  
  @Autowired
  private InsuranceRepository insuranceRepo;
 
  
  @GetMapping("/insurance_create")
  public String viewInsuranceCreate(Model model,
      @RequestParam(value="personId") Optional<Integer> personId,
      RedirectAttributes redirectAttributes,
      SessionStatus status
  )
  {

    if (personId.isPresent())
    {
      try
      {
        Person person=personRepo.findById(personId.get()).get();
        model.addAttribute("person",person);
      }
      catch (DataAccessException | NoSuchElementException e)
      {
        redirectAttributes.addFlashAttribute("messages",new FlashMessage("Chyba: Nepodařilo se načíst data osoby.",
            FlashMessageType.error));
        status.setComplete();
        
        return "redirect:/insurance_create";
      }
    }


    if (model.containsAttribute("person")
        && !model.containsAttribute("insurance"))
    {
      model.addAttribute("insurance",new Insurance());
    }
    

    return "insurance_create";

  }
  
  
  @PostMapping("/insurance_create")
  public String submitInsuranceCreate(@Valid Insurance insurance,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes,
      SessionStatus status,
      @ModelAttribute("person") Person person)
  {
    
    if (bindingResult.hasErrors())
    {
      List<FieldError> errors=bindingResult.getFieldErrors();
      
      String errorMessageText="Formulář obsahuje chyby: ";
      for (FieldError error:errors)
      {
        errorMessageText+=error.getDefaultMessage()+" ";
      }      

      redirectAttributes.addFlashAttribute("messages",new FlashMessage(errorMessageText,FlashMessageType.error));
      redirectAttributes.addFlashAttribute("insurance",insurance);
      
      return "redirect:/insurance_create";
    }


    try
    {
      insurance.setPerson(person);
      insuranceRepo.save(insurance);
        redirectAttributes.addFlashAttribute("messages",
            new FlashMessage("K osobě "+person.getName()+" "+person.getSurname()
                +" bylo přidáno pojištění #"+insurance.getContractNo()+".",FlashMessageType.success));
    }
    catch (DataAccessException e)
    {
      redirectAttributes.addFlashAttribute("messages",
          new FlashMessage("Při komunikaci s databází nastala chyba.",FlashMessageType.error));
      
      return "redirect:/insurance_create";
    }
    
    
    status.setComplete();
      
    return "redirect:/person_list";
        
  }

}
