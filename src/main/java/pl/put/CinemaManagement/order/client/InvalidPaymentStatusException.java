package pl.put.CinemaManagement.order.client;

public class InvalidPaymentStatusException extends IllegalStateException {
    public InvalidPaymentStatusException(String message) {
        super(message);
    }
}
