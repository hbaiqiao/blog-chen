package com.evan.wj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "type")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})

public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    String name;
    String cover;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}

