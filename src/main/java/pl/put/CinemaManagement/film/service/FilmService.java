package pl.put.CinemaManagement.film.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.put.CinemaManagement.file.service.FileService;
import pl.put.CinemaManagement.film.dto.CreateFilmRequest;
import pl.put.CinemaManagement.film.repository.FilmRepository;
import pl.put.CinemaManagement.model.Film;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FilmService {
    private final FilmRepository filmRepository;
    private final FileService fileService;

    public Film save(CreateFilmRequest filmDto) throws IOException {
        Film film = filmDto.film();

        if (filmDto.filmPoster() != null) {
            String posterId = UUID.randomUUID().toString();
            fileService.put(posterId, filmDto.filmPoster().getInputStream());
            film.setImageSource(posterId);
        }

        return filmRepository.save(film);
    }

    public void delete(Long filmId) {
        Film persistedFilm = findFilmById(filmId);
        String posterId = persistedFilm.getImageSource();

        if (posterId != null) {
            fileService.delete(posterId);
        }

        filmRepository.delete(persistedFilm);
    }


    public Film findFilmById(Long filmId) {
        return filmRepository.findById(filmId).orElseThrow(() -> new FilmNotFoundException("Cannot get film with id=" + filmId + "requested film does not exist"));
    }

    public Iterable<Film> findAllFilms() {
        return filmRepository.findAll();
    }

    public byte[] getFilmPoster(Long filmId) {
        Film film = findFilmById(filmId);
        String posterId = film.getImageSource();
        try {
            return fileService.get(posterId);
        } catch (IOException e) {
            throw new FilmNotFoundException("Could not retrieve film poster for filmId = " + filmId);
        }
    }

}
