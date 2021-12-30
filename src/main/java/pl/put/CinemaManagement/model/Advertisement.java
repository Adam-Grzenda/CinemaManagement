package pl.put.CinemaManagement.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "advertisement"
)
public class Advertisement extends CinemaEntity {

    @Column(name = "company_name")
    @ColumnDefault(value = "'own'")
    @NotNull
    private String companyName;

    @Column(name = "duration")
    @NotNull
    private float duration;

    @OneToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToMany
    @JoinTable(name = "film_show_advertisement",
            joinColumns = @JoinColumn(name = "advertisement_id"),
            inverseJoinColumns = @JoinColumn(name = "film_show_id"))
    private List<FilmShow> filmShows;

}

