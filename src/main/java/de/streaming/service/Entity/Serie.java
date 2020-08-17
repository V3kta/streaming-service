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
@Table(name = "serien")
@Getter
public class Serie implements Serializable {

    @Id
    private Integer id;

    private String name;

    private String beschreibung;

    private String bildPfad;

    @JsonIgnore
    @OneToMany(mappedBy = "serie")
    List<UserSerie> userSerien;

}
