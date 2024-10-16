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
public class Category implements Serializable {

    private Integer id;
    private String name;

    public Category(String name) {
        this.name = name;
    }
}
