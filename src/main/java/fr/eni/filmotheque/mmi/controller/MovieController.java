package fr.eni.filmotheque.mmi.controller;

import fr.eni.filmotheque.bll.MovieService;
import fr.eni.filmotheque.bo.Movie;
import fr.eni.filmotheque.exception.FilmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller("movieBean")
public class MovieController {

    private MovieService movieService;

    @Autowired  //Optionnel sur le constructeur
    public MovieController(MovieService ms) {

        this.movieService = ms;
    }

    @GetMapping("/films")
    public String listeFilms(Model model) {
        List<Movie> films = movieService.getAllMovies();
//        if (films == null) {
//            films = new ArrayList<>();
//        }
        //System.out.println(films);
        model.addAttribute("films", films);
        model.addAttribute("date", LocalDateTime.now());
        return "films";
    }
    @GetMapping("/films/{id}")
    public String detailFilm(@PathVariable("id") int id, Model model) {

        Optional<Movie> optFilm = movieService.getMovieById(id);
        if (optFilm.isPresent()) {
            //System.out.println(optFilm.get());
            model.addAttribute("film", optFilm.get());
        }
        return "un-film";
    }

    @GetMapping("/films/ajout")
    public String ajoutFilmForm (Model model, HttpSession session) {
        if (session.getAttribute("member") == null) {
            return "redirect:/login";
        } else {
            Movie nouveauFilm = new Movie();
            model.addAttribute("film", nouveauFilm);
            model.addAttribute("listeGenres", movieService.getGenres());
            model.addAttribute("listePeople", movieService.getPeople());
            model.addAttribute("annee", LocalDateTime.now().getYear());
            //System.out.println("annee: " + LocalDateTime.now().getYear());
            return "nouveau-film";
        }
    }

    @PostMapping("/films/ajout")
    public String ajoutFilmForm (Movie nouveau, Model model, HttpSession session) {
//  TODO prise en compte des éléments de validation
//  public String ajoutFilmForm (@Valid @ModelAttribute("movie") Movie nouveau,, BindingResult br, Model model, HttpSession session) {
        if (session.getAttribute("member") == null) {
            return "redirect:/login";
        } else {
            try {
                movieService.saveMovie(nouveau);
            } catch (FilmException fe) {
                System.out.println("Erreurs : " + fe.getErrors());
                model.addAttribute("errors", fe.getErrors());
                model.addAttribute("listeGenres", movieService.getGenres());
                model.addAttribute("listePeople", movieService.getPeople());
                model.addAttribute("annee", LocalDateTime.now().getYear());
                model.addAttribute("film", nouveau);
                return "nouveau-film";
            }
            return "redirect:/films";
        }
    }

    private Optional<Movie> findMovie(int id) {
        return movieService.getMovieById(id);
    }

    private List<Movie> findAllMovies() {
        return movieService.getAllMovies();
    }
}
