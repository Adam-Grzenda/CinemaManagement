package pl.put.CinemaManagement.cinema;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.put.CinemaManagement.cinema.film.show.FilmShow;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "employee",
        uniqueConstraints =
        @UniqueConstraint(columnNames = "pesel"))
public class Employee {
    private enum WorkPosition {
        ENTRY("entry"),
        MANAGER("manager"),
        TECH_SUPPORT("tech_support"),
        CLEANER("cleaner");
        private final String stringValue;

        WorkPosition(String stringValue) {
            this.stringValue = stringValue;
        }

        @Override
        public String toString() {
            return this.stringValue;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "pesel")
    @NotNull
    private String pesel;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "work_position")
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private WorkPosition workPosition;

    @Column(name = "employed_to")
    @NotNull
    private Timestamp employedTo;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "cinema_id",
            referencedColumnName = "id")
    private Cinema cinema;

    @ManyToMany
    @JoinTable(name = "film_show_employee",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "film_show_id"))
    private List<FilmShow> filmShows;

}

