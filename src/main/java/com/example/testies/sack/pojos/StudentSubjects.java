package com.example.testies.sack.pojos;

import java.util.List;

public class StudentSubjects {

    Integer classId;
    List<ImageResult> results;
    String subjectName;
    String band;
    String teacherName;
    Integer overallResult;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public List<ImageResult> getResults() {
        return results;
    }

    public void setResults(List<ImageResult> results) {
        this.results = results;
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

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getOverallResult() {
        return overallResult;
    }

    public void setOverallResult(Integer overallResult) {
        this.overallResult = overallResult;
    }
}
