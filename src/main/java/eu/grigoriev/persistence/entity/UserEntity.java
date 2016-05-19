package eu.grigoriev.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(
                name = "findByName",
                query = "from UserEntity user where user.username = :username"
        )
})
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "display")
    private String display;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "expired")
    private boolean expired;

    @Column(name = "locked")
    private boolean locked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "security_role_id")
    private SecurityRoleEntity securityRoleEntity;

    @ManyToMany(mappedBy = "userEntities")
    private List<CupEntity> cupEntities;

    @ManyToMany
    @JoinTable(
            name = "bets_users",
            joinColumns = @JoinColumn(name = "bet_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private List<BetEntity> betEntities;

    public UserEntity() {
    }

    public UserEntity(String username, String password, String display, boolean enabled, boolean expired, boolean locked, SecurityRoleEntity securityRoleEntity) {
        this.username = username;
        this.password = password;
        this.display = display;
        this.enabled = enabled;
        this.expired = expired;
        this.locked = locked;
        this.securityRoleEntity = securityRoleEntity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean accountNonExpired) {
        this.expired = accountNonExpired;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean accountNonLocked) {
        this.locked = accountNonLocked;
    }

    public SecurityRoleEntity getSecurityRoleEntity() {
        return securityRoleEntity;
    }

    public void setSecurityRoleEntity(SecurityRoleEntity securityRoleEntity) {
        this.securityRoleEntity = securityRoleEntity;
    }

}
