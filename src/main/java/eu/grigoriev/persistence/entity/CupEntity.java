package eu.grigoriev.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cups")
public class CupEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="cupEntity")
    private List<MatchEntity> matchEntities;

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="cupEntity")
    private List<GroupEntity> groupEntities;

    @OneToOne
    @PrimaryKeyJoinColumn
    private TriumvirateEntity triumvirateEntity;

    @OneToOne
    @PrimaryKeyJoinColumn
    private GoldenBallEntity goldenBallEntity;

    @OneToOne
    @PrimaryKeyJoinColumn
    private GoldenBootEntity goldenBootEntity;

    @OneToOne
    @PrimaryKeyJoinColumn
    private GoldenGloveEntity goldenGloveEntity;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "cups_users",
            joinColumns = @JoinColumn(name = "cup_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private List<UserEntity> userEntities;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "cups_teams",
            joinColumns = @JoinColumn(name = "cup_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id")
    )
    private List<TeamEntity> teamEntities;

    public CupEntity() {
    }

    public CupEntity(String name) {
        this.name = name;
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

    public List<MatchEntity> getMatchEntities() {
        return matchEntities;
    }

    public void setMatchEntities(List<MatchEntity> matchEntities) {
        this.matchEntities = matchEntities;
    }

    public List<GroupEntity> getGroupEntities() {
        return groupEntities;
    }

    public void setGroupEntities(List<GroupEntity> groupEntities) {
        this.groupEntities = groupEntities;
    }

    public TriumvirateEntity getTriumvirateEntity() {
        return triumvirateEntity;
    }

    public void setTriumvirateEntity(TriumvirateEntity triumvirateEntity) {
        this.triumvirateEntity = triumvirateEntity;
    }

    public GoldenBallEntity getGoldenBallEntity() {
        return goldenBallEntity;
    }

    public void setGoldenBallEntity(GoldenBallEntity goldenBallEntity) {
        this.goldenBallEntity = goldenBallEntity;
    }

    public GoldenBootEntity getGoldenBootEntity() {
        return goldenBootEntity;
    }

    public void setGoldenBootEntity(GoldenBootEntity goldenBootEntity) {
        this.goldenBootEntity = goldenBootEntity;
    }

    public GoldenGloveEntity getGoldenGloveEntity() {
        return goldenGloveEntity;
    }

    public void setGoldenGloveEntity(GoldenGloveEntity goldenGloveEntity) {
        this.goldenGloveEntity = goldenGloveEntity;
    }

    public List<UserEntity> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(List<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }

    public List<TeamEntity> getTeamEntities() {
        return teamEntities;
    }

    public void setTeamEntities(List<TeamEntity> teamEntities) {
        this.teamEntities = teamEntities;
    }
}
