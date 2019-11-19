package com.example.testies.sack.pojos;

import java.util.List;

public class LoginResponse {

    int userId;
    String username;
    String type;
    Integer year;
    List<TeacherClasses> teacherClasses;
    List<StudentSubjects> studentSubjects;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<TeacherClasses> getTeacherClasses() {
        return teacherClasses;
    }

    public void setTeacherClasses(List<TeacherClasses> teacherClasses) {
        this.teacherClasses = teacherClasses;
    }

    public List<StudentSubjects> getStudentSubjects() {
        return studentSubjects;
    }

    public void setStudentSubjects(List<StudentSubjects> studentSubjects) {
        this.studentSubjects = studentSubjects;
    }
}
