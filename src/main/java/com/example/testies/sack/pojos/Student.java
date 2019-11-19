package com.example.testies.sack.pojos;

import java.util.List;

public class Student {

    Integer userId;
    String username;
    List<ImageResult> results;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ImageResult> getResults() {
        return results;
    }

    public void setResults(List<ImageResult> results) {
        this.results = results;
    }
}
