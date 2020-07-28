package de.streaming.service.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "serien")
@Getter
@Setter
public class Serie {

    @Id
    Integer id;

    String name;

    String beschreibung;

    // Zuletzt gesehene Folge
    Integer zgFolge;

    // Zuletzt gesehene Staffel
    Integer zgStaffel;

}
