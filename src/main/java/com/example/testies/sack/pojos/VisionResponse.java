package com.example.testies.sack.pojos;

import java.util.List;

public class VisionResponse {
    String imageUri;
    List<Result> answers;

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public List<Result> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Result> answers) {
        this.answers = answers;
    }
}
