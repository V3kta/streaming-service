package de.streaming.service.Controller;

import de.streaming.service.Entity.User;
import de.streaming.service.Model.LoginInfo;
import de.streaming.service.Model.UserSerieIds;
import de.streaming.service.Model.UserToken;
import de.streaming.service.Repository.UserRepository;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

import static javax.crypto.Cipher.SECRET_KEY;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class LoginController {

    final private UserRepository userRepository;
    LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/user/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserToken login(@RequestBody LoginInfo loginInfo) {
        User user = userRepository.findByUsernameAndPassword(loginInfo.getUsername(), loginInfo.getPassword());
        if (user != null) {
            Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

            String jws = Jwts.builder().setSubject(user.getUsername()).signWith(key).compact();
            log.info(user.getUsername());
            log.info(jws);
            return new UserToken(user, jws);
        } else {
            return null;
        }
    }
}
