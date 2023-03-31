package fr.eni.filmotheque.bll.bdd;

import fr.eni.filmotheque.bll.MovieService;
import fr.eni.filmotheque.bll.MovieServiceCheck;
import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.bo.Movie;
import fr.eni.filmotheque.bo.Person;
import fr.eni.filmotheque.dal.GenreDao;
import fr.eni.filmotheque.dal.MovieDao;
import fr.eni.filmotheque.dal.PersonDao;
import fr.eni.filmotheque.exception.FilmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Profile("dev")
public class MovieServiceImplBis implements MovieService {

    @Autowired
    private PersonDao personDao;
    @Autowired
    private MovieDao movieDao;
    @Autowired
    private GenreDao genreDao;
    @Autowired
    private MovieServiceCheck movieServiceCheck;

    public MovieServiceImplBis() {
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieDao.findAll();
    }

    @Override
    public Optional<Movie> getMovieById(long id) {
        return movieDao.findById(id);
    }

    @Override
    public List<Genre> getGenres() {
        return genreDao.findAll();
    }

    @Override
    public List<Person> getPeople() {
        return personDao.findAll();
    }

    @Override
    public Genre getGenreById(long id) {

        //return genreDao.getReferenceById(id);

//        Genre retour = null;
//        Optional<Genre> opt = genreDao.findById(id);
//        if (opt.isPresent())
//            retour = opt.get();
//        return retour;
        return genreDao.findById(id).orElse(null);
    }

    @Override
    public Person getPersonById(long id) {
        Person retour = null;
        Optional<Person> opt = personDao.findById(id);
        if (opt.isPresent())
            retour = opt.get();
        return retour;
        //return personDao.getReferenceById(id);
    }

    @Override
    public void saveMovie(Movie movie) throws FilmException {

        //Contrôles selon règles métiers
        FilmException fe = new FilmException();
        //Passage sur validation détaillée pour appel de méthode de test unitaire
//       movieServiceCheck.validateMovie(movie, fe);

        //TODO - Décomposition par champ dans MovieServiceCheck
        validateTitle(movie.getTitle(), fe);
        validateGenre(movie.getGenre(), fe);
        validateDirector(movie.getDirector(), fe);
        validateYear(movie.getYear(), fe);
        validateDuration(movie.getDuration(), fe);
        validateSynopsis(movie.getSynopsis(), fe);

        if (fe.hasError()) {
            throw fe;
        }
        movieDao.save(movie);
    }

    private void validateTitle(String data, FilmException be) {
        if (data == null || data.isBlank()) {
            be.addError("Le titre est obligatoire");
        }
    }

    private void validateGenre(Genre data, FilmException be) {
        if (data == null || data.getId() == 0) {
            be.addError("Le genre est obligatoire");
        }
    }

    private void validateDirector(Person data, FilmException be) {
        if (data == null || data.getId() == 0) {
            be.addError("Le réalisateur est obligatoire");
        }
    }

    private void validateYear(int data, FilmException be) {
        if (data < 1900 || data > 2100) {
            be.addError("L'année n'est pas correcte");
        }
    }

    private void validateDuration(int data, FilmException be) {
        if (data <= 0) {
            be.addError("La durée est positive");
        }
    }

    private void validateSynopsis(String data, FilmException be) {
        if (data == null || data.isBlank() || (!data.isBlank() && (data.length() < 20 || data.length() > 250))) {
            be.addError("Le synopsis doit faire entre 20 et 250 caractères");
        }
    }
}
