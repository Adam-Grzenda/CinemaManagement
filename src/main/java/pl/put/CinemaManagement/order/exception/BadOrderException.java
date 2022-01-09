package pl.put.CinemaManagement.order.exception;

public class BadOrderException extends RuntimeException {
    public BadOrderException(String message) {
        super(message);
    }
}
