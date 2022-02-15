package pl.put.CinemaManagement.film.dto;

import pl.put.CinemaManagement.model.Film;

import javax.validation.constraints.NotNull;

public record CreateFilmRequest(@NotNull(message = "Film cannot be null") Film film, String poster) {
}
