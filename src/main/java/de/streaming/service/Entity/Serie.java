package de.streaming.service.Entity;

import javax.persistence.*;

@Entity
@Table(name = "serien")
public class Serie {

    @Id
    @GeneratedValue
    Integer serie_id;

    String name;

    String beschreibung;

    // Zuletzt gesehene Folge
    Integer zgFolge;

    // Zuletzt gesehene Staffel
    Integer zgStaffel;


}
