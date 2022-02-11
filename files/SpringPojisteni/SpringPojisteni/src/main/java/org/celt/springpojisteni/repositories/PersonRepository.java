
package org.celt.springpojisteni.repositories;


import org.celt.springpojisteni.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonRepository extends JpaRepository<Person, Integer>
{
  
  
}