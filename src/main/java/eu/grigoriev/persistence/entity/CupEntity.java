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

    public CupEntity() {
    }

    public CupEntity(String name) {
        this.name = name;
    }
}
