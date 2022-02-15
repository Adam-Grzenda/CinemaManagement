package pl.put.CinemaManagement.order.exception;

public class InvalidOrderStatusException extends IllegalStateException {
    public InvalidOrderStatusException(String message) {
        super(message);
    }
}
