package pl.put.CinemaManagement.cinema.film.dto;

import pl.put.CinemaManagement.cinema.film.Film;

import javax.validation.constraints.NotNull;

public record CreateFilmRequest(@NotNull(message = "Film cannot be null") Film film, String poster) {
}
