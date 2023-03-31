package fr.eni.filmotheque.dal;


import fr.eni.filmotheque.bo.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieDao extends JpaRepository<Movie, Long> {
}
