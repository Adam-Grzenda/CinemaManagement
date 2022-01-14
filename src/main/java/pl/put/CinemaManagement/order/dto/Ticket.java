package pl.put.CinemaManagement.order.dto;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Ticket {
    private Long hallRowId;
    private Long hallColumnId;
    private Double discount;
    private Double basePrice;
    private Double finalPrice;
}
