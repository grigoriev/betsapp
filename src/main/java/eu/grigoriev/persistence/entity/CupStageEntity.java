package eu.grigoriev.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cup_stages")
public class CupStageEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "stage")
    private String stage;

    @Column(name = "url")
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cup_id")
    private CupEntity cupEntity;

    public CupStageEntity() {
    }

    public CupStageEntity(String stage, String url, CupEntity cupEntity) {
        this.stage = stage;
        this.url = url;
        this.cupEntity = cupEntity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CupEntity getCupEntity() {
        return cupEntity;
    }

    public void setCupEntity(CupEntity cupEntity) {
        this.cupEntity = cupEntity;
    }
}
