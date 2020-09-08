package de.streaming.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SerieDto {
    Integer id;

    String name;

    String beschreibung;

    String bildPfad;

    String zgDatum;

    Integer zgFolge;

    Integer zgStaffel;
}
