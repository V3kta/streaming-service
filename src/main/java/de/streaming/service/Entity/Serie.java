package de.streaming.service.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "serien")
public class Serie {

    @Id
    Integer serie_id;

    String name;

    String beschreibung;

    @OneToMany
    List<Staffel> staffelList;
}
