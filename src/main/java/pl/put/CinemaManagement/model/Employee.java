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
public class Employee extends CinemaEntity{
    private enum WorkPosition {
        ENTRY, MANAGER, TECH_SUPPORT, CLEANER
    }
    @NotNull
    private String lastName;
    @NotNull
    private String firstName;
    @NotNull
    @Enumerated(EnumType.STRING)
    private WorkPosition workPosition;
    @NotNull
    private Timestamp employedTo;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "cinema",
            referencedColumnName = "cinema_id")
    private Cinema cinema;

    @ManyToMany
    @JoinTable(name = "FilmShowsCrossEmployees",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "filmshow_id"))
    private List<FilmShow> filmShows;
}

