package de.streaming.service.model;

import de.streaming.service.entity.User;
import lombok.Getter;

@Getter
public class UserToken {
    public UserToken() {}

    public UserToken(User user, String token) {
        this.user = user;
        this.token = token;
    }

    User user;
    String token;
}
