package com.poly.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users implements Serializable {

    private String id;
    private String fullname;
    private String password;
    private boolean admin;
}