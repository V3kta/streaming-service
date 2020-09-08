package de.streaming.service.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
public class User implements Serializable {

    public User () {}

    public User (String email, String username, String vorname, String nachname, String password) {
        this.email = email;
        this.username = username;
        this.vorname = vorname;
        this.nachname = nachname;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String username;

    private String vorname;

    private String nachname;

    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<UserSerie> userSerien;

    @OneToOne(mappedBy = "user")
    Settings settings;

}
