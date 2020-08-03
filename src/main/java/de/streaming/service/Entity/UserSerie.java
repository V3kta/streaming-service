package de.streaming.service.Entity;

import de.streaming.service.Model.UserSerieKey;

import javax.persistence.*;

@Entity
@Table(name = "user_serie_data")
public class UserSerie {

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