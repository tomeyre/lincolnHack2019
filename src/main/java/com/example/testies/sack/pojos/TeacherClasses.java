package com.example.testies.sack.pojos;

import java.util.List;

public class TeacherClasses {

    Integer classId;
    String subjectName;
    String band;
    Integer classResult;
    List<Student> students;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public Integer getClassResult() {
        return classResult;
    }

    public void setClassResult(Integer classResult) {
        this.classResult = classResult;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
