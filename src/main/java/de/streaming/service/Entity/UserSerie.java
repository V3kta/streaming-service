package de.streaming.service.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.streaming.service.Model.UserSerieKey;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "user_serie_data")
@Getter
public class UserSerie {

    public UserSerie() {}

    public UserSerie(UserSerieKey id, User user, Serie serie) {
        this.id = id;
        this.user = user;
        this.serie = serie;
    }

    @EmbeddedId
    UserSerieKey id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("serie_id")
    @JoinColumn(name = "serie_id")
    Serie serie;

    // Zuletzt gesehene Folge
    private Integer zgFolge;

    // Zuletzt gesehene Staffel
    private Integer zgStaffel;

    // Zuletzt gesehen am
    private String zgDatum;


}
