package fr.eni.filmotheque.bo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Genre {
    //Attributs d'instance
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String label;

    //Constructeurs
    public Genre() {
    }

    public Genre(String label) {
        this();
        this.label = label;
    }

    public Genre(long id, String label) {
        this(label);
        this.id = id;
    }

    //Getters & Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    //Méthodes d'instances
    @Override
    public String toString() {
        return label + "[id=" + id + "] ";

    /*  return "Genre {" +
                "id=" + id +
                ", label='" + label + '\'' +
                '}';
     */
    }

//    public String toString() {
//        final StringBuilder sb = new StringBuilder("Genre [");
//        sb.append("id=").append(id);
//        sb.append(", label='").append(label).append('\'');
//        sb.append(']');
//        return sb.toString();
//    }
}
