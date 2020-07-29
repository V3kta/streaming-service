package de.streaming.service.Entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
public class User implements Serializable {
    @Id
    private Integer id;

    private String username;

    private String password;

    @OneToMany(mappedBy = "user")
    List<Serie> serien;

}
