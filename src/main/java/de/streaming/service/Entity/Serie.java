package de.streaming.service.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
