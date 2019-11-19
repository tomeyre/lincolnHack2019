package com.example.testies.sack.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "class_info")
public class ClassEntity {
    @Id
    @Column(name = "class_id")
    private Integer classId;
    @Column(name = "class_name_id")
    private Integer classNameId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "result")
    private Integer result;
    @Column(name = "image_id")
    private String imageId;
    @Column(name = "band")
    private String band;
    @Column(name = "teacher_id")
    private Integer teacherId;
    @Column(name = "test_id")
    private Integer testId;
    @Column(name = "test_time")
    private String testTime;

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getClassNameId() {
        return classNameId;
    }

    public void setClassNameId(Integer classNameId) {
        this.classNameId = classNameId;
    }

    public Integer getClassId() {
        return classId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public String getTestTime() {
        return testTime;
    }

    public void setTestTime(String testTim) {
        this.testTime = testTim;
    }
}