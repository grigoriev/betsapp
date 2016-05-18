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

    public TeamEntity() {
    }

    public TeamEntity(String name, String logo, TeamTypeEntity teamTypeEntity) {
        this.name = name;
        this.logo = logo;
        this.teamTypeEntity = teamTypeEntity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public TeamTypeEntity getTeamTypeEntity() {
        return teamTypeEntity;
    }

    public void setTeamTypeEntity(TeamTypeEntity teamTypeEntity) {
        this.teamTypeEntity = teamTypeEntity;
    }
}
