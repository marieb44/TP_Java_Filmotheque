package fr.eni.filmotheque.bll;

import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.bo.Movie;
import fr.eni.filmotheque.bo.Person;
import fr.eni.filmotheque.exception.FilmException;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> getAllMovies();
    Optional<Movie> getMovieById(long id);
    List<Genre> getGenres();
    List<Person> getPeople();
    Genre getGenreById(long id);
    Person getPersonById(long id);
    void saveMovie(Movie movie) throws FilmException;
}
