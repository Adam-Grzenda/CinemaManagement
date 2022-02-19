package pl.put.CinemaManagement.cinema.film;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.*;
import pl.put.CinemaManagement.cinema.film.dto.CreateFilmRequest;

import javax.annotation.security.RolesAllowed;

@RequiredArgsConstructor
@RepositoryRestController
@RestController
public class FilmController {

    private final FilmService filmService;

    @RolesAllowed("admin")
    @PostMapping("/films")
    public Film createFilm(@RequestBody CreateFilmRequest filmDto) {
        return filmService.save(filmDto);
    }

    @RolesAllowed("admin")
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
