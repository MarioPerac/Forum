package org.unibl.etf.sni.forum.models.entites;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.unibl.etf.sni.forum.base.BaseEntity;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "user", schema = "forum")
public class UserEntity implements BaseEntity<String>, UserDetails {
    @Id
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "surname")
    private String surname;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "mail")
    private String mail;
    @Basic
    @Column(name = "activated")
    private Boolean activated = true;
    @Basic
    @Column(name = "role_id")
    private Integer roleId  = 2;
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false, insertable=false, updatable=false)
    private RoleEntity roleByRoleId;

    @Override
    public void setId(String object) {
        this.username = object;
    }

    @Override
    public String getId() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roleByRoleId.getName()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
