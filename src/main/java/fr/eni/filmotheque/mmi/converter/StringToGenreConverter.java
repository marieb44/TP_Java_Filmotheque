package fr.eni.filmotheque.mmi.converter;

import fr.eni.filmotheque.bll.MovieService;
import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.bo.Person;
import fr.eni.filmotheque.exception.FilmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StringToGenreConverter implements Converter<String, Genre> {

    private MovieService movieService;

    @Autowired    //Optionnel sur constructeur si pas d'ambiguité
    public StringToGenreConverter(MovieService ms) {
        this.movieService = ms;
    }

    @Override
    public Genre convert(String idStr)  {
        Genre genre = null;

        try {
            long id = Long.parseLong(idStr);

            genre = movieService.getGenres().stream()
                    .filter(g -> g.getId() == id)
                    .findFirst()
                    .orElse(null);

        } catch (NumberFormatException e) {
//            FilmException fe = new FilmException();
//            fe.ajouterErreur("Erreur de conversion (Genre)");
//            throw fe;
        }

        return genre;
    }
}
