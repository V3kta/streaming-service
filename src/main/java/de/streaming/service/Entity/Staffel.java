package de.streaming.service.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "staffeln")
public class Staffel {

    @Id
    Integer staffel_nr;

    @OneToMany
    List<Folge> folgeList;
}
