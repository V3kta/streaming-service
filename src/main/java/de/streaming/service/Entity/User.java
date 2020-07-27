package de.streaming.service.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class User {
    @Id
    Integer user_id;

    String username;

    String password;

    @OneToMany
    @JoinColumn(name = "user_id")
    List<Serie> geseheneSerien;


}
