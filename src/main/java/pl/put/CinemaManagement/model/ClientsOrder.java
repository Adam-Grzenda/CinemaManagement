package pl.put.CinemaManagement.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

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
    private enum PaymentType {
        CASH, CREDIT_CARD, DEBT_CARD, ONLINE_PAYMENT
    }

    private enum PaymentStatus {
        OPEN, IN_PROCESS, FAILED, CLOSED
    }

    @Column(name = "realized")
    @NotNull
    private boolean realized;

    @Column(name = "date")
    @NotNull
    private Timestamp date;

    @Column(name = "payment_type")
    @NotNull
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Column(name = "payment_status")
    @NotNull
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "amount")
    @NotNull
    private float amount;

    @ManyToOne
    @JoinColumn(name = "client_id",
            referencedColumnName = "id")
    private Client client;

    @OneToMany(mappedBy = "clientsOrder")
    List<Ticket> tickets;

    public float calculateOrderCost() {
        return 10; //TODO
    }
}
