package pl.put.CinemaManagement.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Advertisement extends CinemaEntity{
    @NotNull
    private String companyName;
    @NotNull
    private float duration;

    @OneToOne(optional = true)
    @JoinColumn(name = "movie_id", nullable = true)
    private Movie movie;

    @ManyToMany
    @JoinTable(name = "FilmShowsCrossAdvertisements",
            joinColumns = @JoinColumn(name = "advertisement_id"),
            inverseJoinColumns = @JoinColumn(name = "filmshow_id"))
    private List<FilmShow> filmShows;
}
