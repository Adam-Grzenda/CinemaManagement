package pl.put.CinemaManagement.order.exception;

public class InvalidPaymentStatusException extends IllegalStateException {
    public InvalidPaymentStatusException(String message) {
        super(message);
    }
}
