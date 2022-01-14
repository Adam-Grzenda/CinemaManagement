package pl.put.CinemaManagement.order.dto;

import lombok.Data;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@ToString
@Data
public class OrderDisplay {
    private String filmShowTitle;
    private Date filmShowDate;
    private List<Ticket> tickets;
}
