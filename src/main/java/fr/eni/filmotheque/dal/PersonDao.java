package fr.eni.filmotheque.dal;

import fr.eni.filmotheque.bo.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonDao extends JpaRepository<Person, Long> {
}
