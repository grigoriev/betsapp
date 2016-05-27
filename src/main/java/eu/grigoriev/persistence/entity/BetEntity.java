package eu.grigoriev.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bets")
public class BetEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private MatchEntity matchEntity;

    @Column(name = "host_scores")
    private Integer hostScores;

    @Column(name = "guest_scores")
    private Integer guestScores;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public BetEntity() {
    }

    public BetEntity(MatchEntity matchEntity, Integer hostScores, Integer guestScores, UserEntity userEntity) {
        this.matchEntity = matchEntity;
        this.hostScores = hostScores;
        this.guestScores = guestScores;
        this.userEntity = userEntity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MatchEntity getMatchEntity() {
        return matchEntity;
    }

    public void setMatchEntity(MatchEntity matchEntity) {
        this.matchEntity = matchEntity;
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

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
