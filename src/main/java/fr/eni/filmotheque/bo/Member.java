package fr.eni.filmotheque.bo;

public class Member {
    private long id;
    private String lastName;
    private String firstName;
    private String login;
    private String password;
    private boolean isAdmin;

    //Constructeurs
    public Member() {
    }
    public Member(long id, String lastName, String firstName, String login, String password, boolean isAdmin) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;
    }
    public Member(String lastName, String firstName, String login, String password, boolean isAdmin) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    //Getters & Setters
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
    //Méthodes d'instance

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Member {");
        sb.append("id=").append(id);
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", isAdmin=").append(isAdmin);
        sb.append('}');
        return sb.toString();
    }
}
