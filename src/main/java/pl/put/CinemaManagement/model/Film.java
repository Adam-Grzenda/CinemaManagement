package pl.put.CinemaManagement.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "movie"
)
public class Movie extends CinemaEntity {

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

    @Column(name = "license_to")
    @ColumnDefault("'2200-12-31'")
    @NotNull
    private Date licenseTo;

    @Column(name = "premiere_date")
    @NotNull
    private Date premiereDate;

    @Column(columnDefinition="TEXT")
    private String description;

    private String imageSource;

}
