package pl.put.CinemaManagement.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import pl.put.CinemaManagement.order.exception.InvalidPaymentStatusException;
import pl.put.CinemaManagement.order.exception.OrderAlreadyRealizedException;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "clients_order")
public class ClientsOrder extends CinemaEntity {
    public enum PaymentType {
        CASH, CREDIT_CARD, DEBT_CARD, ONLINE_PAYMENT
    }

    public enum PaymentStatus {
        OPEN, IN_PROCESS, FAILED, CLOSED, CANCELLED
    }

    @Column(name = "realized")
    @NotNull
    private boolean realized;

    @CreationTimestamp
    @Column(name = "date")
    @NotNull
    private Timestamp date;

    @Column(name = "payment_type")
    @NotNull
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Column(name = "payment_status")
    @ColumnDefault(value = "'OPEN'")
    @NotNull
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "amount")
    @NotNull
    private Double amount;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "client_id",
            referencedColumnName = "id")
    private Client client;

    @OneToMany(mappedBy = "clientsOrder", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    List<Ticket> tickets;

    public void updatePaymentState(PaymentStatus requestedStatus) throws IllegalStateException {
        if (this.paymentStatus.compareTo(requestedStatus) > 0) {
            throw new InvalidPaymentStatusException("Requested state is invalid");
        } else {
            this.paymentStatus = requestedStatus;
        }
    }

    public void realizeOrder() {
        if (this.paymentStatus != PaymentStatus.CLOSED) {
            throw new InvalidPaymentStatusException("Order payment state isn't CLOSED");
        }

        if (this.realized) {
            throw new OrderAlreadyRealizedException();
        } else {
            this.realized = true;
        }
    }
}
