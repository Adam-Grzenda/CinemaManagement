package pl.put.CinemaManagement.order;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.put.CinemaManagement.order.InvalidOrderException;
import pl.put.CinemaManagement.order.dto.OrderErrorResponse;

@ControllerAdvice("pl.put.CinemaManagement.order")
public class OrderControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler({IllegalStateException.class})
    public ResponseEntity<Object> handleInvalidState(IllegalStateException e, WebRequest webRequest) {
        return handleExceptionInternal(e, new OrderErrorResponse(e.getMessage()), new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, webRequest);
    }

    @ExceptionHandler(InvalidOrderException.class)
    public ResponseEntity<Object> handleInvalidState(InvalidOrderException e, WebRequest webRequest) {
        return handleExceptionInternal(e, new OrderErrorResponse(e.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);

    }

}
