package org.unibl.etf.sni.forum.models.requests;

import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String mail;
}
