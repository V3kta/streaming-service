package de.streaming.service.Model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserSerieKey implements Serializable {
    @Column(name = "user_id")
    Integer user_id;

    @Column(name = "serie_id")
    Integer serie_id;
}
