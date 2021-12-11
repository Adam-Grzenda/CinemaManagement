package pl.put.CinemaManagement.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ClientsOrder extends CinemaEntity{
    private enum PaymentType {
        CASH, CREDIT_CARD, DEBT_CARD, ONLINE_PAYMENT
    }
    private enum PaymentStatus {
        OPEN, IN_PROCESS, FAILED, CLOSED
    }

    @NotNull
    private boolean realized;
    @NotNull
    private Timestamp date;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(32) default 'CASH'")
    private PaymentType paymentType;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(32) default 'OPEN'")
    private PaymentStatus paymentStatus;
    @NotNull
    private float amount;
}
