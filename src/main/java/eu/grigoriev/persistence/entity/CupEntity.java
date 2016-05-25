package eu.grigoriev.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cups")
@NamedQueries({
        @NamedQuery(
                name = "CupEntity.findByName",
                query = "select cup from CupEntity cup where cup.name = :name"
        )
})
public class CupEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "utc_offset")
    private Integer utcOffset;

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="cupEntity")
    private List<CupStageEntity> cupStageEntities;

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="cupEntity")
    private List<CupMenuItemEntity> cupMenuItemEntities;

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

    @ManyToMany
    @JoinTable(
            name = "cups_users",
            joinColumns = @JoinColumn(name = "cup_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private List<UserEntity> userEntities;

    @ManyToMany
    @JoinTable(
            name = "cups_teams",
            joinColumns = @JoinColumn(name = "cup_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id")
    )
    private List<TeamEntity> teamEntities;

    public CupEntity() {
    }

    public CupEntity(String name, String displayName, Integer utcOffset) {
        this.name = name;
        this.displayName = displayName;
        this.utcOffset = utcOffset;
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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Integer getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(Integer utcOffset) {
        this.utcOffset = utcOffset;
    }

    public List<CupStageEntity> getCupStageEntities() {
        return cupStageEntities;
    }

    public void setCupStageEntities(List<CupStageEntity> cupStageEntities) {
        this.cupStageEntities = cupStageEntities;
    }

    public List<CupMenuItemEntity> getCupMenuItemEntities() {
        return cupMenuItemEntities;
    }

    public void setCupMenuItemEntities(List<CupMenuItemEntity> cupMenuItemEntities) {
        this.cupMenuItemEntities = cupMenuItemEntities;
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
