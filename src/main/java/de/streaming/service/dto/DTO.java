package de.streaming.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;

public class DTO {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class UserDTO {
        private Integer id;
        private String email;
        private String username;
        private String vorname;
        private String nachname;
        private String token;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class SerieDTO {
        private Integer id;
        private String name;
        private String beschreibung;
        private String bildPfad;
        private String zgDatum;
        private Integer zgFolge;
        private Integer zgStaffel;

        public static Comparator<SerieDTO> SerieNameAscComp = new Comparator<SerieDTO>() {

            public int compare(SerieDTO s1, SerieDTO s2) {
                String StudentName1 = s1.getName().toUpperCase();
                String StudentName2 = s2.getName().toUpperCase();

                //ascending order
                return StudentName1.compareTo(StudentName2);

                //descending order
                //return StudentName2.compareTo(StudentName1);
            }};
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class UserSerieDTO {
        private UserDTO userDTO;
        private SerieDTO serieDTO;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class SettingsDTO {
        private String cardViewMode;
        private String theme;
    }

    @Getter
    @Setter
    public static class RegisterInfoDTO {
        private String email;
        private String username;
        private String password;
        private String vorname;
        private String nachname;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class LoginInfoDTO {
        private String login;
        private String password;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class UsernameInfoDTO {
        private Integer id;
        private String username;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class PasswordInfoDTO {
        private Integer id;
        private String oldPassword;
        private String newPassword;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class EmailInfoDTO {
        private Integer id;
        private String email;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class UserSerieIdsDTO {
        private Integer userId;
        private Integer serieId;
    }




}
