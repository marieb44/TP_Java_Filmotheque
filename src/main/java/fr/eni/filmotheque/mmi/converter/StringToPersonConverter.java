package fr.eni.filmotheque.mmi.converter;

import fr.eni.filmotheque.bll.MovieService;
import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.bo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StringToPersonConverter implements Converter<String, Person> {

    private MovieService movieService;

    @Autowired    //Optionnel sur constructeur si pas d'ambiguité
    public StringToPersonConverter(MovieService ms) {
        this.movieService = ms;
    }

    @Override
    public Person convert(String idStr) {
        Person person = null;

        try {
            long id = Long.parseLong(idStr);
            person = movieService.getPersonById(id);

//            person = movieService.getPeople().stream()
//                    .filter(p -> p.getId() == id)
//                    .findFirst()
//                    .orElse(null);

        } catch (NumberFormatException e) {
//            FilmException fe = new FilmException();
//            fe.ajouterErreur("Erreur de conversion (Person)");
//            throw fe;
        }
        return person;
    }
}
