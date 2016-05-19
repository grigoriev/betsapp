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
}
