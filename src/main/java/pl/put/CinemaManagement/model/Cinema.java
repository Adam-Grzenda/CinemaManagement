package pl.put.CinemaManagement.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(
        name = "cinema",
        uniqueConstraints=
        @UniqueConstraint(columnNames = {"name", "address"}))
public class Cinema extends CinemaEntity {
    @NotNull
    private String name;
    @NotNull
    private String address;

    @OneToMany(mappedBy = "cinema")
    private List<CinemaHall> cinemaHalls;
}
