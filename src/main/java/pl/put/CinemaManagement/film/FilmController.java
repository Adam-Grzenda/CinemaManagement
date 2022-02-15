package pl.put.CinemaManagement.film;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.*;
import pl.put.CinemaManagement.film.service.FilmService;
import pl.put.CinemaManagement.film.dto.CreateFilmRequest;
import pl.put.CinemaManagement.model.Film;

import javax.annotation.security.RolesAllowed;
import java.io.IOException;

@RequiredArgsConstructor
@RepositoryRestController
@RestController
public class FilmController {

    private final FilmService filmService;

    @RolesAllowed("${admin.role}")
    @PostMapping("/films")
    public Film createFilm(@RequestBody CreateFilmRequest filmDto) throws IOException {
        return filmService.save(filmDto);
    }

    @RolesAllowed("${admin.role}")
    @DeleteMapping("/films/{id}")
    public void deleteFilm(@PathVariable Long id) {
        filmService.delete(id);
    }

    @GetMapping("/films")
    public Iterable<Film> getAllFilms() {
        return filmService.findAllFilms();
    }

    @GetMapping("/films/{id}")
    public Film getFilm(@PathVariable Long id) {
        return filmService.findFilmById(id);
    }

    @GetMapping("/films/{id}/poster")
    public byte[] getFilmPoster(@PathVariable Long id) {
        return filmService.getFilmPoster(id);
    }

}
