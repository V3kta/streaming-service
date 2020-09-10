package de.streaming.service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "settings")
@Getter
public class Settings {

    @Id
    Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    User user;

    String cardViewMode;

    String theme;


    public Settings() {

    }

    public Settings(User user, String cardViewMode, String theme) {
        this.id = user.getId();
        this.user = user;
        this.cardViewMode = cardViewMode;
        this.theme = theme;
    }
}
