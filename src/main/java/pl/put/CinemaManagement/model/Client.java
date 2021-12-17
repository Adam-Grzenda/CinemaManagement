package pl.put.CinemaManagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "client")
public class Client extends CinemaEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "client_client_segment",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "client_segment_id"))
    private List<ClientSegment> clientSegments;
}
