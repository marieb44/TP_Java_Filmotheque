package fr.eni.filmotheque.bll.mock;

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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@Profile("test")
public class MovieServiceMock implements MovieService {
    private List<Movie> lstMovies;
    private List<Genre> lstGenres;
    private List<Person> lstPeople;
    private MovieServiceCheck movieServiceCheck;

    private static final String[] GENRES = { "Animation",
                                             "Science-fiction",
                                             "Documentaire",
                                             "Action",
                                             "Comédie",
                                             "Drame" };

    @Autowired //optionnel sur le constructeur
    public MovieServiceMock(MovieServiceCheck movieServiceCheck) {

        this.movieServiceCheck = movieServiceCheck;

        lstGenres = new ArrayList<>();
        Genre g1 = new Genre(2,GENRES[1]);
        lstGenres.add(g1);
        Genre g2 = new Genre(5, GENRES[4]);
        lstGenres.add(g2);
        Genre g3 = new Genre(3, GENRES[2]);
        lstGenres.add(g3);

        lstPeople = new ArrayList<>();
        Person sp = new Person(1,"Spielberg", "Steven");
        lstPeople.add(sp);
        Person dc = new Person(2,"Cronenberg", "David");
        lstPeople.add(dc);
        Person ld = new Person(3, "Dern", "Laura");
        lstPeople.add(ld);
        Person jg = new Person(4, "Goldblum", "Jeff");
        lstPeople.add(jg);
        Person gd = new Person(5, "Davies", "Geena");
        lstPeople.add(gd);
        Person mr = new Person(6, "Rylance", "Mark");
        lstPeople.add(mr);
        Person rb = new Person(7, "Barnhill", "Ruby");
        lstPeople.add(rb);
        Person jja = new Person(8, "Annaud", "Jean-Jacques");
        lstPeople.add(jja);

        lstMovies = new ArrayList<>();
        Movie m1 = new Movie(1,"Jurassic Park",1993, 128,
                "Le film raconte l'histoire d'un milliardaire et son équipe de généticiens parvenant à ramener à la vie des dinosaures grâce au clonage.",
                 g1, sp);
        m1.addActor(ld);
        m1.addActor(jg);
        lstMovies.add(m1);

        Movie m2 = new Movie(2,"The Fly",1986, 95,
                "Il s'agit de l'adaptation cinématographique de la nouvelle éponyme de l'auteur George Langelaan.",
                g1, dc);
        m2.addActor(jg);
        m2.addActor(gd);
        lstMovies.add(m2);

        Movie m3 = new Movie(3,"The BFG",2016, 117,
                "Le Bon Gros Géant est un géant bien différent des autres habitants du Pays des Géants.",
                g2, sp);
        m3.addActor(mr);
        m3.addActor(rb);
        lstMovies.add(m3);

        Movie m4 = new Movie(4,"L'Ours",1988, 96,
                "Un ourson orphelin, inconscient et maladroit, fait l'apprentissage de la vie avec un ours solitaire.",
                g3, jja);
        lstMovies.add(m4);
    }

    @Override
    public List<Movie> getAllMovies() {
        return lstMovies;
    }

    /**
     * Méthode renvoyant un Optional<Movie> à partir de l'Id passé en paramètre
     * @param id clé primaire
     * @return : un optional de Movie
     */
    @Override
    public Optional<Movie> getMovieById(long id) {

        //Version Iterator
        Iterator<Movie> it = lstMovies.iterator();
        boolean trouve = false;
        //Movie m = null;
        Optional<Movie> optMovie = Optional.empty();
        while(it.hasNext() && ! trouve) {
            Movie m = it.next();
            if (m.getId() == id) {
                optMovie = Optional.of(m);
                trouve = true;
            }
        }
        return optMovie;

/*
        //Version ForEach
        //Movie found = null;
        Optional<Movie> optMovie = Optional.empty();
        for (Movie movie : lstMovies) {
            if (movie.getId() == id) {
                //found = movie;
                optMovie = Optional.of(movie);
                break;
            }
        }
        //return found;
        return optMovie;
*/
    }

    @Override
    public List<Genre> getGenres() {
        return lstGenres;
    }

    @Override
    public List<Person> getPeople() {
        return lstPeople;
    }

    @Override
    public Genre getGenreById(long id) {
        Iterator<Genre> it = lstGenres.iterator();
        boolean trouve = false;
        Genre g = null;
        while(it.hasNext() && ! trouve) {
            g = it.next();
            if (g.getId() == id) {
                trouve = true;
            }
        }
        return g;
    }

    @Override
    public Person getPersonById(long id) {
        Iterator<Person> it = lstPeople.iterator();
        boolean trouve = false;
        Person p = null;
        while (it.hasNext() && ! trouve) {
            p = it.next();
            if (p.getId() == id) {
                trouve = true;
            }
        }
        return p;
    }

    @Override
    public void saveMovie(Movie movie) throws FilmException {

        System.out.println("Nouveau : " + movie);

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

        // si les contrôles sont OK, on donne un identifiant au film
        movie.setId(lstMovies.size());

        lstMovies.add(movie);
    }
}
