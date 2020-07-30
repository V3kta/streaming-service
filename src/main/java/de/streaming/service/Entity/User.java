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
    @Id
    private Integer id;

    private String username;

    private String password;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_serie_data",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "serie_id"))
    List<Serie> serien;

}
