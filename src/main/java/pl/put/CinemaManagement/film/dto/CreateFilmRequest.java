package pl.put.CinemaManagement.film.dto;

import org.springframework.web.multipart.MultipartFile;
import pl.put.CinemaManagement.model.Film;

import javax.validation.constraints.NotNull;

public record CreateFilmRequest(@NotNull(message = "Film cannot be null") Film film, MultipartFile filmPoster) {
}
