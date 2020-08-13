package de.streaming.service.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {
    private String username;

    private String vorname;

    private String nachname;

    private String password;

    private String token;
}
