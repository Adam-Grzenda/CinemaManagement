package pl.put.CinemaManagement.model;

import javax.persistence.*;

@MappedSuperclass
public class CinemaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
}
