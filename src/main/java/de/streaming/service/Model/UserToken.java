package de.streaming.service.Model;

import de.streaming.service.Entity.User;
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
