package eu.grigoriev.persistence.entity;

import eu.grigoriev.persistence.generic.GenericEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cup")
public class CupEntity implements GenericEntity<Integer>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Override
    public Integer getPK() {
        return null;
    }
}
