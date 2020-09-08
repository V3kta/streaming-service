package de.streaming.service.dto;

import lombok.Getter;

@Getter
public class UserRegisterDto {

    private String email;

    private String username;

    private String password;

    private String vorname;

    private String nachname;
}
