package com.poly.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class News implements Serializable {

    private Integer id;
    private String title;
    private String content;
    private Integer author;
    private Date postedDate;
    private String image;
    private int viewCount;
    private Integer categoryId;
    private boolean home;
}
