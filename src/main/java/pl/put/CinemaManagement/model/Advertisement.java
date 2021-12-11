package pl.put.CinemaManagement.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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
}
