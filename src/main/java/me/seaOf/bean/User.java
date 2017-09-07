package me.seaOf.bean;

import lombok.Data;

@Data
public class User {
    private int id;
    private String username;
    private String password;
    private String password2;
    private String nickname;
    private String email;

}
