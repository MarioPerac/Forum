package org.unibl.etf.sni.forum.models.requests;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
