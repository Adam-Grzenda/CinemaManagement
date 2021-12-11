package pl.put.CinemaManagement.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Movie extends CinemaEntity {
    @NotNull
    private String title;
    @NotNull
    private String director;
    @NotNull
    private float duration;
    @ColumnDefault("false")
    private boolean is3D;
    private Timestamp licenseTo;
    private Timestamp premiereDate;
}
