package pl.coderslab.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import pl.coderslab.validator.groups.FullUserValidationGroup;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotEmpty(groups = {Default.class, FullUserValidationGroup.class})
    @Size(min=3, max=100)
    private
    String login;

    @Size(min=3, max=100)
    @NotEmpty(groups = FullUserValidationGroup.class)
    String name;

    @Size(min=3, max=100)
    @NotEmpty(groups = {Default.class, FullUserValidationGroup.class})
    private
    String password;

    @NotEmpty(groups = FullUserValidationGroup.class)
    @Email
    private
    String email;

    @ManyToMany
    private
    List<Game> games = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private
    List<Comment> comments = new ArrayList<>();

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return login;
    }
}
