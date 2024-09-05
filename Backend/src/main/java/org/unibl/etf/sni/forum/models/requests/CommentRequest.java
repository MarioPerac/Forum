package org.unibl.etf.sni.forum.models.requests;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class CommentRequest {

    private String content;
    private Integer areaId;
    private String username;
    private Boolean published;
    private Boolean forbidden;
}
