package pl.put.CinemaManagement.cinema.film;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.put.CinemaManagement.cinema.film.dto.FilmErrorResponse;

@ControllerAdvice("pl.put.CinemaManagement.cinema.film")
public class FilmControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(FilmNotFoundException.class)
    public ResponseEntity<Object> filmNotFound(FilmNotFoundException e, WebRequest webRequest) {
        return handleExceptionInternal(e, new FilmErrorResponse(e.getMessage()), new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> otherException(Exception e, WebRequest webRequest) {
        return handleExceptionInternal(e, new FilmErrorResponse(e.getMessage()), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
    }
}
