package fr.eni.filmotheque.bll.bdd;

import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.bo.Movie;
import fr.eni.filmotheque.bo.Person;
import fr.eni.filmotheque.dal.MovieDao;
import fr.eni.filmotheque.dal.PersonDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.internal.configuration.CaptorAnnotationProcessor;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
@ExtendWith(MockitoExtension.class)
public class MovieServiceImplBisTest {
    @Mock
    private PersonDao personDao;
    @Mock
    private MovieDao movieDao;
    @InjectMocks
    private MovieServiceImplBis implBis;
    @Captor
    private ArgumentCaptor<Movie> captor;

    @Test
    void testGetPersonByIdAvecRetourObjet(){
        Mockito.when(personDao.findById(5L)).thenReturn(Optional.of(new Person()));

        Person p = implBis.getPersonById(5L);

        Assertions.assertNotNull(p);
        Mockito.verify(personDao, Mockito.times(1)).findById(5L);
    }
    @Test
    void testGetPersonByIdAvecRetourNull(){
        Mockito.when(personDao.findById(5L)).thenReturn(Optional.empty());

        Person p = implBis.getPersonById(5L);

        Assertions.assertNull(p);
        Mockito.verify(personDao, Mockito.times(1)).findById(5L);
    }

    @Test
    void testSaveMovieOk() {
        Genre g = new Genre(1, "Thriller");
        Person d = new Person(1, "Legrand", "Joe");
        Movie m = new Movie(1, "titre",2000, 90, "C'est l'histoire de ...", g, d);

        Assertions.assertDoesNotThrow(() -> implBis.saveMovie(m));
        Mockito.verify(movieDao).save(captor.capture());

        Assertions.assertSame(m, captor.getValue());
    }

}
