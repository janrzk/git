
package org.celt.springpojisteni.controllers;


import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.celt.springpojisteni.entities.Person;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class PersonCreateController
{
  
  
  @Autowired
  private PersonRepository repo;
  
  
  
  @GetMapping("/person_create")
  public String viewPersonCreate(Model model)
  {
    if (!model.containsAttribute("person"))
    {
      model.addAttribute("person", new Person());
    }
    return "person_create";
  }

  
  
  @PostMapping("/person_create")
  public String submitPersonCreate(@Valid Person person,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes)
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
      redirectAttributes.addFlashAttribute("person",person);
      
      return "redirect:/person_create";
    }
    
    try
    {
      repo.save(person);
      redirectAttributes.addFlashAttribute("messages",
          new FlashMessage("Do databáze byla přidána osoba "+person.getName()+" "+person.getSurname()+".",
              FlashMessageType.success));
    }
    catch (DataAccessException e)
    {
      redirectAttributes.addFlashAttribute("messages",
          new FlashMessage("Při komunikaci s databází nastala chyba.",FlashMessageType.error));
      
      return "redirect:/person_create";
    }
    
    return "redirect:/person_list";
  }  
  
  
}
