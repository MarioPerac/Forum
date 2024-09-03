package org.unibl.etf.sni.forum.models.entites;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "role", schema = "forum")
public class RoleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
}
