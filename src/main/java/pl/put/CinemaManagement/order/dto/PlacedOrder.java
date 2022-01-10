package pl.put.CinemaManagement.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.put.CinemaManagement.model.ClientsOrder;

@Data
@AllArgsConstructor
public class PlacedOrder {
    Long orderId;

    public static PlacedOrder of(ClientsOrder clientsOrder) {
        return new PlacedOrder(clientsOrder.getId());
    }
}
