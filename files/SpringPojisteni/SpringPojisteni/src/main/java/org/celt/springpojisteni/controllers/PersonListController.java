
package org.celt.springpojisteni.controllers;


import java.util.List;
import org.celt.springpojisteni.entities.Person;
import org.celt.springpojisteni.repositories.PersonRepository;
import org.celt.springpojisteni.utils.FlashMessage;
import org.celt.springpojisteni.utils.FlashMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class PersonListController
{
  
  @Autowired
  private PersonRepository repo;
  
  private List<Person> personList;
  
  
  @GetMapping("/person_list")
  public String viewPersonList(Model model,
      RedirectAttributes redirectAttributes)
  {
    
    try
    {
      personList=(List)repo.findAll();
      model.addAttribute("persons",personList);
    }
    catch (DataAccessException e)
    {
      redirectAttributes.addFlashAttribute("messages",new FlashMessage("Při komunikaci s databází nastala chyba.",
          FlashMessageType.error));

      return "redirect:/index";      
    }
    
    return "person_list";
  
  }

}
