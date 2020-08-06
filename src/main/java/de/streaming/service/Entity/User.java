package de.streaming.service.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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

    private String vorname;

    private String nachname;

    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<UserSerie> userSerien;

}
