package de.streaming.service.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "folgen")
public class Folge {

    @Id
    Integer folge_nr;

    String name;

    String dauer;

}
