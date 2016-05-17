package eu.grigoriev.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "teams")
public class TeamEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "logo")
    private String logo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="team_type_id")
    private TeamTypeEntity teamTypeEntity;
}
