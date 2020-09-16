package de.streaming.service.controller;

import de.streaming.service.dto.DTO;
import de.streaming.service.entity.User;
import de.streaming.service.repository.UserRepository;
import de.streaming.service.service.DbService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class LoginController {

    private final  UserRepository userRepository;
    private final DbService dbService;
    LoginController(DbService dbService, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.dbService = dbService;
    }

    @PostMapping(value = "/user/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DTO.UserDTO login(@RequestBody DTO.LoginInfoDTO loginInfoDTO) {
        return dbService.validateLogin(loginInfoDTO.getLogin(), loginInfoDTO.getPassword());
    }

    @PostMapping(value = "/user/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus register(@RequestBody DTO.RegisterInfoDTO registerInfoDTO) {
        if (!userRepository.existsByUsername(registerInfoDTO.getUsername())) {
            User user = new User(registerInfoDTO.getEmail(), registerInfoDTO.getUsername(), registerInfoDTO.getVorname(), registerInfoDTO.getNachname(), registerInfoDTO.getPassword());
            userRepository.save(user);
            return HttpStatus.ACCEPTED;
        }
        return HttpStatus.FORBIDDEN;
    }
}
