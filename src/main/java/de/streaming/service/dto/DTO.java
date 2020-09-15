package de.streaming.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
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
        private LocalDate zgDatum;
        private Integer zgFolge;
        private Integer zgStaffel;

        public static Comparator<SerieDTO> SerieNameAscComp = new Comparator<SerieDTO>() {

            public int compare(SerieDTO s1, SerieDTO s2) {
                String serieName1 = s1.getName().toUpperCase();
                String serieName2 = s2.getName().toUpperCase();

                return serieName1.compareTo(serieName2);

            }};

        public static Comparator<SerieDTO> SerieNameDescComp = new Comparator<SerieDTO>() {

            public int compare(SerieDTO s1, SerieDTO s2) {
                String serieName1 = s1.getName().toUpperCase();
                String serieName2 = s2.getName().toUpperCase();

                return serieName2.compareTo(serieName1);

            }};

        public static Comparator<SerieDTO> SerieDateAscComp = new Comparator<SerieDTO>() {

            public int compare(SerieDTO s1, SerieDTO s2) {
                LocalDate serieDate1 = s1.getZgDatum();
                LocalDate serieDate2 = s2.getZgDatum();

                return serieDate1.compareTo(serieDate2);

            }};

        public static Comparator<SerieDTO> SerieDateDescComp = new Comparator<SerieDTO>() {

            public int compare(SerieDTO s1, SerieDTO s2) {
                LocalDate serieDate1 = s1.getZgDatum();
                LocalDate serieDate2 = s2.getZgDatum();

                return serieDate2.compareTo(serieDate1);

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
