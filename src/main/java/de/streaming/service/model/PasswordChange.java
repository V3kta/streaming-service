package de.streaming.service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordChange {
    private Integer id;
    private String oldPassword;
    private String newPassword;
}
