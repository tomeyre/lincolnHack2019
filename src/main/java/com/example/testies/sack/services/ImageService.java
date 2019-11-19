package com.example.testies.sack.services;

import com.example.testies.sack.entities.ClassEntity;
import com.example.testies.sack.pojos.*;
import com.example.testies.sack.respositories.SchoolClassRepository;
import com.example.testies.sack.util.HttpConnect;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    SchoolClassRepository schoolClassRepository;

    @Autowired
    LoginService loginService;

    private VisionResponse visionResponse;
    private Integer percentage = 0;

    public LoginResponse sendImage(ImageRequest image) {
        try {
            HttpConnect jParser = new HttpConnect();
            JSONObject body = new JSONObject();
            body.put("image", image.getImage());
            String json = jParser.getJSONFromUrl("https://3acc0e43.ngrok.io/document", body.toString());
            if (json == null) {
                return null;
            }
            JSONObject jsonObject = new JSONObject(json);
            visionResponse = new VisionResponse();
            visionResponse.setImageUri(jsonObject.getString("uri"));
            visionResponse.setAnswers(buildAnswers(jsonObject.getJSONObject("result").getJSONArray("questions")));
            for (int i = 0; i < visionResponse.getAnswers().size(); i++) {
                System.out.println(visionResponse.getAnswers().get(i).getQuestion() + " = " + visionResponse.getAnswers().get(i).getAnswer());
            }
            Integer score = jsonObject.getJSONObject("result").getInt("score");
            Integer count = jsonObject.getJSONObject("result").getInt("count");
            percentage = (100 / count) * score;
            System.out.println("Questions found :" + count + " , score " + percentage + "%");
            updateClass(image);
            return loginService.imageFullResponse(image.getUser_id());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void updateClass(ImageRequest image){
        ClassEntity classEntity = schoolClassRepository.findByUserIdAndClassId(image.getClass_id(), image.getUser_id());
        if (classEntity.getResult() != null) {
            classEntity = schoolClassRepository.findByUserIdAndClassNameId(classEntity.getClassNameId(), image.getUser_id());
        }
        classEntity.setResult(percentage);
        classEntity.setImageId(visionResponse.getImageUri());
        classEntity.setTestTime(new SimpleDateFormat("EEEEE dd MMM yy HH:mm").format(new Date()));
        schoolClassRepository.saveAndFlush(classEntity);
    }

    private List<Result> buildAnswers(JSONArray jsonArray){
        List<Result> results = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++){
            Result result = new Result();
            result.setAnswer(jsonArray.getJSONObject(i).getString("answer"));
            result.setQuestion(jsonArray.getJSONObject(i).getString("question"));
            result.setResult(jsonArray.getJSONObject(i).getBoolean("correct"));
            results.add(result);
        }
        return results;
    }
}
