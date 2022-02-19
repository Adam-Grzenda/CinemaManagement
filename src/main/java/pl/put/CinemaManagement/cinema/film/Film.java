package pl.put.CinemaManagement.cinema.film;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "film",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"title"}))
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "title")
    @NotNull
    private String title;

    @Column(name = "director")
    @NotNull
    private String director;

    @Column(name = "duration")
    @NotNull
    private float duration;

    @Column(name = "is3D")
    @ColumnDefault("false")
    @NotNull
    private boolean is3D;

    @Column(name = "premiere_date")
    @NotNull
    private Date premiereDate;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_source", columnDefinition = "TEXT")
    private String imageSource;

}
