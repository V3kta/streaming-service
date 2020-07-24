package de.streaming.service.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    Integer uniqueId;

    String username;

    String password;
}
