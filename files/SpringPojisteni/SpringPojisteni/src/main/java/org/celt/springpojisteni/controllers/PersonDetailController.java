
package org.celt.springpojisteni.controllers;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@SessionAttributes("person")
@Controller
public class PersonDetailController
{

  @Autowired
  private PersonRepository repo;
  
  @GetMapping("/person_detail")
  public String viewPersonDetail(Model model,
      @RequestParam(value="id") Optional<Integer> id,
      RedirectAttributes redirectAttributes,
      SessionStatus status)
  {

    if (id.isPresent())
    {
      try
      {
        Person person=repo.findById(id.get()).get();
        model.addAttribute("person", person);
      }
      catch (DataAccessException | NoSuchElementException e)
      {
        FlashMessage errorMessage=new FlashMessage("Chyba: Nelze načíst data.",FlashMessageType.error);
        redirectAttributes.addFlashAttribute("messages",errorMessage);
        status.setComplete();
        return "redirect:/person_detail";
      }
    }
    
    return "person_detail";
    
  }
  
  @PostMapping(value="/person_detail", params="update")
  public String submitPersonDetail(@Valid Person person,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes,
      SessionStatus status)
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
      
      return "redirect:/person_detail";
    }

    
    try
    {
      repo.save(person);
      redirectAttributes.addFlashAttribute("messages",
          new FlashMessage("Změny byly uloženy.",FlashMessageType.success));
    }
    catch (DataAccessException e)
    {
      redirectAttributes.addFlashAttribute("messages",
          new FlashMessage("Při komunikaci s databází nastala chyba.",FlashMessageType.error));
      
      return "redirect:/person_detail";
    }
        
    
    status.setComplete();
    
    return "redirect:/person_list";
  }


  @PostMapping(value="/person_detail", params="delete")
  public String deletePerson(@ModelAttribute("person") Person person,
      RedirectAttributes redirectAttributes,
      SessionStatus status)
  {
    
    repo.delete(person);
    
    FlashMessage successMessage=new FlashMessage(
        "Osoba "+person.getName()+" "+person.getSurname()+" byla vymazána z databáze.",FlashMessageType.success);
    redirectAttributes.addFlashAttribute("messages",successMessage);    
    
    status.setComplete();

    return "redirect:/person_list";
  }  

}
