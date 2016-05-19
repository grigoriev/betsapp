package eu.grigoriev.persistence.entity;

import eu.grigoriev.constants.Matches;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "bets")
public class BetEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="match_id")
    private MatchEntity matchEntity;

    @Column(name = "host_scores")
    private Integer hostScores;

    @Column(name = "guest_scores")
    private Integer guestScores;

    @ManyToMany(mappedBy="betEntities")
    private List<UserEntity> userEntities;
}
