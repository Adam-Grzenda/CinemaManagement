package pl.put.CinemaManagement;

import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass
public class CinemaEntity { //TODO - remove this to avoid unnecessary coupling
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
}
