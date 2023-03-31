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
public class MovieServiceImpl implements MovieService {

    private PersonDao personDao;
    private MovieDao movieDao;
    private GenreDao genreDao;
    private MovieServiceCheck movieServiceCheck;

    @Autowired //optionnel sur constructeur
    public MovieServiceImpl(PersonDao personDao,
                            MovieDao movieDao,
                            GenreDao genreDao,
                            MovieServiceCheck movieServiceCheck) {
        this.personDao = personDao;
        this.movieDao = movieDao;
        this.genreDao = genreDao;
        this.movieServiceCheck = movieServiceCheck;
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
        return personDao.getReferenceById(id);
    }

    @Override
    public void saveMovie(Movie movie) throws FilmException {

        //Contrôles selon règles métiers
        FilmException fe = new FilmException();
        movieServiceCheck.validateMovie(movie, fe);

        //TODO - Décomposition par champ
//        validateTitle(movie.getTitle(), fe);
//        validateGenre(movie.getGenre(), fe);
//        validateDirector(movie.getDirector(), fe);
//        validateYear(movie.getYear(), fe);
//        validateDuration(movie.getDuration(), fe);
//        validateSynopsis(movie.getSynopsis(), fe);

        if (fe.hasError()) {
            throw fe;
        }
        movieDao.save(movie);
    }
}
