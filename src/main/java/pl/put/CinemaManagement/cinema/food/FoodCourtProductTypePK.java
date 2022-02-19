package pl.put.CinemaManagement.cinema.food;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class FoodCourtProductTypePK implements Serializable {

    @Column(name = "food_court_id")
    private Long foodCourt;

    @Column(name = "product_type_id")
    private Long productType;
}
