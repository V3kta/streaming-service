package de.streaming.service.controller;

import de.streaming.service.dto.UserDto;
import de.streaming.service.dto.UserRegisterDto;
import de.streaming.service.entity.User;
import de.streaming.service.model.*;
import de.streaming.service.repository.UserRepository;
import de.streaming.service.service.DbService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    public UserDto login(@RequestBody LoginInfo loginInfo) {
        return dbService.validateLogin(loginInfo.getLogin(), loginInfo.getPassword());
    }

    @PostMapping(value = "/user/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus register(@RequestBody UserRegisterDto userRegisterDto) {
        if (!userRepository.existsByUsername(userRegisterDto.getUsername())) {
            User user = new User(userRegisterDto.getEmail(),userRegisterDto.getUsername(), userRegisterDto.getVorname(), userRegisterDto.getNachname(), userRegisterDto.getPassword());
            userRepository.save(user);
            return HttpStatus.ACCEPTED;
        }
        return HttpStatus.FORBIDDEN;
    }
}
