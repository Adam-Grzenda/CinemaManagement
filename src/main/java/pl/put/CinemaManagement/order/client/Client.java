package pl.put.CinemaManagement.order.client;

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
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "external_id")
    private String externalId;

    @ManyToMany
    @JoinTable(
            name = "client_client_segment",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "client_segment_id"))
    private List<ClientSegment> clientSegments;

    public static Client fromExternalId(String externalId, String name) {
        Client client = new Client();
        client.externalId = externalId;
        client.name = name;
        return client;
    }
}
