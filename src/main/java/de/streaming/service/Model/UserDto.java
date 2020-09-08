package de.streaming.service.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {
    private Integer id;

    private String email;

    private String username;

    private String vorname;

    private String nachname;

    private String token;
}
