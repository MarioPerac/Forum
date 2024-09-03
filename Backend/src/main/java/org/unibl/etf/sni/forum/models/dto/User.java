package org.unibl.etf.sni.forum.models.dto;

import lombok.Data;
@Data
public class User{

    private String username;
    private String name;
    private String password;
    private String surname;
    private String mail;
    private Boolean activated;
    private String roleId;
}
