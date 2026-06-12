package model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(@JsonAlias("Title") String ttulo,@JsonAlias("imdbRating") String imdbAvaliação,@JsonAlias("totalSeasons") Integer totalTemporadas) {
}
