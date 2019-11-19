package com.example.testies.sack.entities;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "type")
    private String type;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "school_year")
    private Integer schoolYear;
    @Column(name = "name")
    private String name;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(Integer schoolYear) {
        this.schoolYear = schoolYear;
    }
}