package eu.grigoriev.persistence.entity;

import eu.grigoriev.constants.Teams;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(mappedBy = "teamEntities")
    private List<CupEntity> cupEntities;

    public TeamEntity() {
    }

    public TeamEntity(String name, TeamTypeEntity teamTypeEntity) {
        this.name = name;
        this.teamTypeEntity = teamTypeEntity;
        if (teamTypeEntity.getType().equals(Teams.Types.NATIONAL)) {
            this.logo = "/resources/img/flags/Flag_of_" + name.replaceAll(" ", "_") + ".svg.png";
        }
        if (teamTypeEntity.getType().equals(Teams.Types.CLUB)) {
            this.logo = "/resources/img/logos/" + name.replaceAll(" ", "_") + ".png";
        }
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

    public List<CupEntity> getCupEntities() {
        return cupEntities;
    }

    public void setCupEntities(List<CupEntity> cupEntities) {
        this.cupEntities = cupEntities;
    }

    public void addCup(CupEntity cupEntity) {
        if (cupEntities == null) {
            cupEntities = new ArrayList<>();
        }
        cupEntities.add(cupEntity);
    }
}
