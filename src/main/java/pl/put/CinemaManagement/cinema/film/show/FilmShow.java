package pl.put.CinemaManagement.cinema.film.show;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import pl.put.CinemaManagement.cinema.Employee;
import pl.put.CinemaManagement.cinema.assets.CinemaHall;
import pl.put.CinemaManagement.cinema.film.advertisement.Advertisement;
import pl.put.CinemaManagement.CinemaEntity;
import pl.put.CinemaManagement.cinema.film.Film;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "film_show",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {
                "cinema_hall_id",
                "date"}))
//  #TODO czas filmu +- => zajęta sala, nwm czy to da się w constraint walnąć
// moze pola start i end i trigger ustawiajacy end na start+film.duration?
public class FilmShow extends CinemaEntity {
    public enum FilmShowType {
        IMAX, TWO_DIM, THREE_DIM, FIVE_DIM
    }

    @Column(name = "date")
    @NotNull
    private Date date;

    @Column(name = "film_show_type")
    @ColumnDefault(value = "'TWO_DIM'")
    @NotNull
    @Enumerated(EnumType.STRING)
    private FilmShowType type;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "film_id",
            referencedColumnName = "id")
    private Film film;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "cinema_hall_id",
            referencedColumnName = "id")
    private CinemaHall cinemaHall;

    @ManyToMany
    @JoinTable(name = "film_show_employee",
            joinColumns = @JoinColumn(name = "film_show_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> employees;

    @ManyToMany
    @JoinTable(name = "film_show_advertisement",
            joinColumns = @JoinColumn(name = "film_show_id"),
            inverseJoinColumns = @JoinColumn(name = "advertisement_id"))
    private List<Advertisement> advertisements;

}
