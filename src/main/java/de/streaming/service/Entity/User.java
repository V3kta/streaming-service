package de.streaming.service.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {
    @Id
    Integer id;

    String username;

    String password;

    @OneToMany
    @JoinColumn(name = "user_id")
    List<Serie> geseheneSerien;


}
