package eu.grigoriev.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "team_types")
public class TeamTypeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "type", unique = true)
    private String type;
}
