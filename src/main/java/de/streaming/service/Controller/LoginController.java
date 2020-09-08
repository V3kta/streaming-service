package de.streaming.service.Controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import de.streaming.service.Entity.User;
import de.streaming.service.Model.*;
import de.streaming.service.Repository.UserRepository;
import de.streaming.service.Service.DbService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

import static javax.crypto.Cipher.SECRET_KEY;

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
