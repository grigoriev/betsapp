package eu.grigoriev.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "matches")
public class MatchEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "serial_number")
    private Integer serialNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cup_id")
    private CupEntity cupEntity;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="host_team_id")
    private TeamEntity hostTeamEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="guest_team_id")
    private TeamEntity guestTeamEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="match_type_id")
    private MatchTypeEntity matchTypeEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cup_stage_id")
    private CupStageEntity cupStageEntity;

    @Column(name = "host_scores")
    private Integer hostScores;

    @Column(name = "guest_scores")
    private Integer guestScores;

    @Column(name = "host_aet_scores")
    private Integer hostAetScores;

    @Column(name = "guest_aet_scores")
    private Integer guestAetScores;

    @Column(name = "host_penalty_series_scores")
    private Integer hostPenaltySeriesScores;

    @Column(name = "guest_penalty_series_scores")
    private Integer guestPenaltySeriesScores;

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="matchEntity")
    private List<BetEntity> betEntities;

    public MatchEntity() {
    }

    public MatchEntity(Integer serialNumber, CupEntity cupEntity, Timestamp timestamp, TeamEntity hostTeamEntity, TeamEntity guestTeamEntity, MatchTypeEntity matchTypeEntity, CupStageEntity cupStageEntity) {
        this.serialNumber = serialNumber;
        this.cupEntity = cupEntity;
        this.timestamp = timestamp;
        this.hostTeamEntity = hostTeamEntity;
        this.guestTeamEntity = guestTeamEntity;
        this.matchTypeEntity = matchTypeEntity;
        this.cupStageEntity = cupStageEntity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public CupEntity getCupEntity() {
        return cupEntity;
    }

    public void setCupEntity(CupEntity cupEntity) {
        this.cupEntity = cupEntity;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public TeamEntity getHostTeamEntity() {
        return hostTeamEntity;
    }

    public void setHostTeamEntity(TeamEntity hostTeamEntity) {
        this.hostTeamEntity = hostTeamEntity;
    }

    public TeamEntity getGuestTeamEntity() {
        return guestTeamEntity;
    }

    public void setGuestTeamEntity(TeamEntity guestTeamEntity) {
        this.guestTeamEntity = guestTeamEntity;
    }

    public MatchTypeEntity getMatchTypeEntity() {
        return matchTypeEntity;
    }

    public void setMatchTypeEntity(MatchTypeEntity matchTypeEntity) {
        this.matchTypeEntity = matchTypeEntity;
    }

    public CupStageEntity getCupStageEntity() {
        return cupStageEntity;
    }

    public void setCupStageEntity(CupStageEntity cupStageEntity) {
        this.cupStageEntity = cupStageEntity;
    }

    public Integer getHostScores() {
        return hostScores;
    }

    public void setHostScores(Integer hostScores) {
        this.hostScores = hostScores;
    }

    public Integer getGuestScores() {
        return guestScores;
    }

    public void setGuestScores(Integer guestScores) {
        this.guestScores = guestScores;
    }

    public Integer getHostAetScores() {
        return hostAetScores;
    }

    public void setHostAetScores(Integer hostAetScores) {
        this.hostAetScores = hostAetScores;
    }

    public Integer getGuestAetScores() {
        return guestAetScores;
    }

    public void setGuestAetScores(Integer guestAetScores) {
        this.guestAetScores = guestAetScores;
    }

    public Integer getHostPenaltySeriesScores() {
        return hostPenaltySeriesScores;
    }

    public void setHostPenaltySeriesScores(Integer hostPenaltySeriesScores) {
        this.hostPenaltySeriesScores = hostPenaltySeriesScores;
    }

    public Integer getGuestPenaltySeriesScores() {
        return guestPenaltySeriesScores;
    }

    public void setGuestPenaltySeriesScores(Integer guestPenaltySeriesScores) {
        this.guestPenaltySeriesScores = guestPenaltySeriesScores;
    }

    public List<BetEntity> getBetEntities() {
        return betEntities;
    }

    public void setBetEntities(List<BetEntity> betEntities) {
        this.betEntities = betEntities;
    }

    public void setResult(int hostScores, int guestScores) {
        setHostScores(hostScores);
        setGuestScores(guestScores);
    }

    public void setResult(int hostScores, int guestScores, int hostAetScores, int guestAetScores) {
        setHostScores(hostScores);
        setGuestScores(guestScores);
        setHostAetScores(hostAetScores);
        setGuestAetScores(guestAetScores);
    }

    public void setResult(int hostScores, int guestScores, int hostAetScores, int guestAetScores, int hostPenaltySeriesScores, int guestPenaltySeriesScores) {
        setHostScores(hostScores);
        setGuestScores(guestScores);
        setHostAetScores(hostAetScores);
        setGuestAetScores(guestAetScores);
        setHostPenaltySeriesScores(hostPenaltySeriesScores);
        setGuestPenaltySeriesScores(guestPenaltySeriesScores);
    }
}
