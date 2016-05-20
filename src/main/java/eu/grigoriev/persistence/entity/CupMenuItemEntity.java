package eu.grigoriev.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cup_menu_items")
public class CupMenuItemEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "menu_item")
    private String menuItem;

    @Column(name = "url")
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cup_id")
    private CupEntity cupEntity;

    public CupMenuItemEntity() {
    }

    public CupMenuItemEntity(String menuItem, String url, CupEntity cupEntity) {
        this.menuItem = menuItem;
        this.url = url;
        this.cupEntity = cupEntity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(String menuItem) {
        this.menuItem = menuItem;
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
