package fr.eni.filmotheque.bll;

import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.bo.Movie;
import fr.eni.filmotheque.bo.Person;
import fr.eni.filmotheque.exception.FilmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieServiceCheck {

    private List<Genre> lstGenres;
    private List<Person> lstPeople;
    @Value("${film.annee.min}")
    private int anneeMin;
    @Value("${film.annee.max}")
    private int anneeMax;

    @Autowired //optionnel sur constructeur
    public MovieServiceCheck(List<Genre> lstGenres, List<Person> lstPeople) {
        this.lstGenres = lstGenres;
        this.lstPeople = lstPeople;
    }

    public void validateMovie (Movie film, FilmException fe) {

        if (film.getTitle() == null || film.getTitle().isBlank()) {
            fe.addError("Le titre est obligatoire.");
        }
        if (// (film.getYear() > LocalDateTime.now().getYear() + 1) || (film.getYear() < 1900 )) {
            (film.getYear() > anneeMax) || (film.getYear() < anneeMin )) {
            fe.addError("L'année doit être comprise entre " + anneeMin + " et " + anneeMax + ".");
        }
        if (film.getGenre() == null || film.getGenre().getId() == 0) {
            fe.addError("Le genre est obligatoire.");
        }
        if (film.getDirector() == null || film.getDirector().getId() == 0)  {
            fe.addError("Le réalisateur est obligatoire.");
        }
        if (film.getDuration() < 0) {
            fe.addError("La durée ne peut pas être négative.");
        }
        if (film.getSynopsis() == null || film.getSynopsis().isBlank() || film.getSynopsis().length() < 20 || film.getSynopsis().length() > 250 ) {
            fe.addError("Le synopsis doit contenir entre 20 et 250 caractères.");
        }
        System.out.println("CheckAjout (hasError) : " + fe.hasError());
    }
}
