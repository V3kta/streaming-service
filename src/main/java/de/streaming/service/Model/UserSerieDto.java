package de.streaming.service.Model;

import de.streaming.service.Entity.Serie;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserSerieDto {

    private Integer userId;
    private SerieDto serie;


}
