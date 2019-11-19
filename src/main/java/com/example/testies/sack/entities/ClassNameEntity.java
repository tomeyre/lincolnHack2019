package com.example.testies.sack.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "class_name")
public class ClassNameEntity {
    @Id
    @Column(name = "class_name_id")
    private Integer classNameId;
    @Column(name = "class_name")
    private String className;

    public Integer getClassNameId() {
        return classNameId;
    }

    public void setClassNameId(Integer classNameId) {
        this.classNameId = classNameId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}