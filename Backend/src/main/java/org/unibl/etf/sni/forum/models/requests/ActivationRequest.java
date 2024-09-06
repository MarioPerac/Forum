package org.unibl.etf.sni.forum.models.requests;

import lombok.Data;

@Data
public class ActivationRequest {
    private Boolean activated;
    private int roleId;
}
