package pl.put.CinemaManagement.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.put.CinemaManagement.order.client.ClientsOrder;

@Data
@AllArgsConstructor
public class PlacedOrder {
    Long orderId;
    String status;

    public static PlacedOrder of(ClientsOrder clientsOrder) {
        return new PlacedOrder(clientsOrder.getId(), "SUCCESS");
    }

    public static PlacedOrder failed(ClientsOrder clientsOrder) {
        return new PlacedOrder(clientsOrder.getId(), "FAILED");
    }
}
