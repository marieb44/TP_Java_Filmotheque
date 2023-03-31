package fr.eni.filmotheque.dal;

import fr.eni.filmotheque.bo.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreDao extends JpaRepository<Genre, Long> {
}
