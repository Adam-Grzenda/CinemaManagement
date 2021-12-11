package pl.put.CinemaManagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter @Setter
@NoArgsConstructor
public class CinemaHall extends CinemaEntity {
    private String type;
    private int number;

    @ManyToOne
    @JoinColumn(name = "cinema_id", nullable = false)
    private Cinema cinema;
}
