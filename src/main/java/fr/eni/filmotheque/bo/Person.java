package fr.eni.filmotheque.bo;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person {
    //Attributs d'instance
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String lastName;
    private String firstName;

    //Constructeurs
    public Person() {
    }
    public Person(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }
    public Person(long id, String lastName, String firstName) {
        this(lastName, firstName);
        this.id = id;
    }

    //Getters & setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //Méthodes d'instance

    @Override
    public String toString() {
        return firstName + " " + lastName + " [id=" + id + "] ";

    /*    return "Person{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    */
    }
}
