package pl.put.CinemaManagement.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
public class CinemaHall extends CinemaEntity {
    @NotNull
    private int number;
    private String type;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "cinema",
            referencedColumnName = "cinema_id")
    private Cinema cinema;

    @OneToMany(mappedBy = "cinemaHall")
    private List<Chair> chairs;
}
