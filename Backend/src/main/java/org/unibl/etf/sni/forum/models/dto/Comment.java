package org.unibl.etf.sni.forum.models.dto;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Comment {
    private Integer id;
    private String content;
    private String username;
    private Timestamp dateTime;
    private Integer areaId;
}
