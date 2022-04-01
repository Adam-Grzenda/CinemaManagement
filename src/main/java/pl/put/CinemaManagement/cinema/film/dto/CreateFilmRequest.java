package pl.put.CinemaManagement.cinema.film.dto;

import pl.put.CinemaManagement.cinema.film.Film;
import pl.put.CinemaManagement.file.FileDetails;

import javax.validation.constraints.NotNull;

public record CreateFilmRequest(@NotNull(message = "Film cannot be null") Film film, FileDetails poster) {
}
