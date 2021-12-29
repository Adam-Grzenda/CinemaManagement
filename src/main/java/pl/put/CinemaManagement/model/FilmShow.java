package pl.put.CinemaManagement.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class FilmShow extends CinemaEntity{
    private enum FilmShowType {
        IMAX, TWO_DIM, THREE_DIM
    }
    @NotNull
    private Timestamp date;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(32) default 'TWO_DIM'")
    private FilmShowType filmShowType;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "movie",
            referencedColumnName = "movie_id")
    private Movie movie;

    @ManyToMany
    @JoinTable(name = "FilmShowsCrossAdvertisements",
            joinColumns = @JoinColumn(name = "filmshow_id"),
            inverseJoinColumns = @JoinColumn(name = "advertisement_id"))
    private List<Advertisement> advertisements;

    @ManyToMany
    @JoinTable(name = "FilmShowsCrossEmployees",
            joinColumns = @JoinColumn(name = "filmshow_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> employees;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "cinemaHall",
            referencedColumnName = "cinemaHall_id")
    private CinemaHall cinemaHall;
}
