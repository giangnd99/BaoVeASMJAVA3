package com.poly.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users implements Serializable {

    private Integer id;
    private String fullname;
    private String password;
    private String email;
    private String phone;
    private Date birthday;
    private boolean gender;
    private boolean role;

    public Users(String email, String fullName, String password) {
        this.email = email;
        this.fullname = fullName;
        this.password = password;
    }
    public Users(int userId, String email, String fullName, String password) {
        this.id = userId;
        this.email = email;
        this.fullname = fullName;
        this.password = password;
    }
}
