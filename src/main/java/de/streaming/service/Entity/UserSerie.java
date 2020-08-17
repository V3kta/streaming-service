package de.streaming.service.Entity;

import de.streaming.service.Model.UserSerieKey;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "user_serie_data")
@Getter
public class UserSerie {

    public UserSerie() {
    }

    public UserSerie(UserSerieKey userSerieKey, User user, Serie serie) {
        id = userSerieKey;
        this.user = user;
        this.serie = serie;
        zgDatum = null;
        zgFolge = 0;
        zgStaffel = 0;
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

    // Zuletzt gesehen am
    private String zgDatum;

    // Zuletzt gesehene Folge
    private Integer zgFolge;

    // Zuletzt gesehene Staffel
    private Integer zgStaffel;

}
