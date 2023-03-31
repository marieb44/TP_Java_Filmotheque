package fr.eni.filmotheque.bo;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Movie {
    //Attributs d'instance
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String title;
    @Column(name = "m_year")   //year = mot clé pour base H2
    @Min(value = 1900)
    @NotNull
    private int year;
    @NotNull
    @Min(value = 0)
    private int duration;
    @NotBlank
    @Size(min = 20, max = 250)
//    @Size(min = 20, max = 2500)
//    @Lob
    private String synopsis;
    @ManyToOne
    @NotNull
    private Genre genre;
    @ManyToOne
    @NotNull
    private Person director;
    @ManyToMany
//    @ManyToMany(fetch = FetchType.EAGER)
    private List<Person> actors;

    //Constructeurs
    public Movie() {
        this.year = 2020;
        setActors(null);
    }
    public Movie(String title, int year, int duration, String synopsis, Genre genre, Person director) {
        this();
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.synopsis = synopsis;
        this.genre = genre;
        this.director = director;
    }
    public Movie(long id, String title, int year, int duration, String synopsis, Genre genre, Person director) {
        this(title, year, duration, synopsis, genre, director);
        this.id = id;
    }
    //Getters & Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Person getDirector() {
        return director;
    }

    public void setDirector(Person director) {
        this.director = director;
    }

    public List<Person> getActors() {
        return actors;
    }

    public void setActors(List<Person> actors) {
        if (actors == null) {
            this.actors = new ArrayList<>();
        } else {
            this.actors = actors;
        }
    }
    //Méthodes d'instance
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Movie [id=");
        builder.append(id);
        builder.append("]\n\ttitle : ");
        builder.append(title);
        builder.append("[year : ");
        builder.append(year);
        builder.append(", duration : ");
        builder.append(duration);
        builder.append("]\n\tsynopsis : ");
        builder.append(synopsis);
        builder.append("\n\tDirector : ");
        builder.append(director);
        builder.append("\n\tActors : ");
        builder.append(actors);
        builder.append("\n\tGenre : ");
        builder.append(genre);
        builder.append("\n ");
        return builder.toString();

       /* return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", duration=" + duration +
                ", synopsis='" + synopsis + '\'' +
                ", genre=" + genre +
                ", director=" + director +
                ", actors=" + actors +
                '}';

        */
    }

    public void addActor(Person actor) {
        actors.add(actor);
    }
}
