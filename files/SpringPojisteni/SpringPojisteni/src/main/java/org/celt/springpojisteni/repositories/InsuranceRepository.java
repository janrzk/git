
package org.celt.springpojisteni.repositories;


import java.util.List;
import org.celt.springpojisteni.entities.Insurance;
import org.celt.springpojisteni.entities.Person;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InsuranceRepository extends JpaRepository<Insurance, Integer>
{
  
  List<Insurance> findByPerson(Person person, Sort sort);
  
}