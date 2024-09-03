package org.unibl.etf.sni.forum.models.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Data
@Entity
@Table(name = "area", schema = "forum")
public class AreaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "areaId")
    private Collection<CommentEntity> commentsById;
}
