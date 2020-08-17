package de.streaming.service.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SerieDto {
    int id;

    String name;

    String beschreibung;

    String bildPfad;

    String zgDatum;

    int zgFolge;

    int zgStaffel;
}
