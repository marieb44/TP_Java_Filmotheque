package fr.eni.filmotheque.bll.bdd;

import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.bo.Movie;
import fr.eni.filmotheque.bo.Person;
import fr.eni.filmotheque.dal.MovieDao;
import fr.eni.filmotheque.dal.PersonDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class MovieServiceImplTest {

    @Test
    void testGetPersonByIdAvecRetourObjet(){
        PersonDao mock = Mockito.mock(PersonDao.class);
        //Stubbing
        Optional<Person> opt = Optional.of(new Person());
        Mockito.when(mock.findById(5L)).thenReturn(opt);

        MovieServiceImpl impl = new MovieServiceImpl(mock,null,null, null);
        Person retour = impl.getPersonById(5L);

        Assertions.assertNotNull(retour);
        Mockito.verify(mock, Mockito.times(1)).findById(5L);
    }

    @Test
    void testGetPersonByIdAvecRetourNull(){
        PersonDao mock = Mockito.mock(PersonDao.class);
        //Stubbing
        Optional<Person> opt = Optional.empty();
        Mockito.when(mock.findById(5L)).thenReturn(opt);

        MovieServiceImpl impl = new MovieServiceImpl(mock,null,null, null);
        Person retour = impl.getPersonById(5L);

        Assertions.assertNull(retour);
        Mockito.verify(mock, Mockito.times(1)).findById(5L);
    }
    @Test
    void testSaveMovieOk() {
        Genre g = new Genre(1, "Thriller");
        Person d = new Person(1, "Legrand", "Joe");
        Movie m = new Movie(1, "titre",2000, 90, "C'est l'histoire de ...", g, d);

        MovieDao mock = Mockito.mock(MovieDao.class);
        MovieServiceImpl impl = new MovieServiceImpl(null, mock,null, null);

        Assertions.assertDoesNotThrow(() -> impl.saveMovie(m));

    }
}
