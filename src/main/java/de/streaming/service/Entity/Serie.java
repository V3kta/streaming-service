package de.streaming.service.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
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

    // Zuletzt gesehene Folge
    private Integer zgFolge;

    // Zuletzt gesehene Staffel
    private Integer zgStaffel;

    // Zuletzt gesehen am
    private String zgDatum;

    @ManyToMany(mappedBy = "serien")
    List<User> users;

}
