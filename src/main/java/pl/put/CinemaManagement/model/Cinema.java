package pl.put.CinemaManagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class Cinema extends CinemaEntity{
    private String name;
    private String address;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    List<CinemaHall> cinemaHalls;
}
