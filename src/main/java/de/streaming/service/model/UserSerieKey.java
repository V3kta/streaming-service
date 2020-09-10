package de.streaming.service.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserSerieKey implements Serializable {

    public UserSerieKey() {}

    public UserSerieKey(Integer user_id, Integer serie_id) {
        this.user_id = user_id;
        this.serie_id = serie_id;
    }

    @Column(name = "user_id")
    Integer user_id;

    @Column(name = "serie_id")
    Integer serie_id;
}
