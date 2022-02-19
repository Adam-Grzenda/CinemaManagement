package pl.put.CinemaManagement.order.client;

public class InvalidOrderStatusException extends IllegalStateException {
    public InvalidOrderStatusException(String message) {
        super(message);
    }
}
