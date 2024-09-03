package org.unibl.etf.sni.forum.models.entites;

import jakarta.persistence.*;
import lombok.Data;
import org.unibl.etf.sni.forum.base.BaseEntity;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "comment", schema = "forum", catalog = "")
public class CommentEntity implements BaseEntity<Integer> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "content")
    private String content;
    @Basic
    @Column(name = "date_time")
    private Timestamp dateTime;
    @Basic
    @Column(name = "area_id")
    private Integer areaId;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "published")
    private Boolean published;
    @Basic
    @Column(name = "forbidden")
    private Boolean forbidden;
    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false, insertable=false, updatable=false)
    private UserEntity userByUsername;
}
