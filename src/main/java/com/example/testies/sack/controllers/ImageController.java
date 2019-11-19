package com.example.testies.sack.controllers;

import com.example.testies.sack.pojos.ImageRequest;
import com.example.testies.sack.pojos.LoginResponse;
import com.example.testies.sack.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {

    @Autowired
    ImageService imageService;

    @PostMapping(value = "/upload")
    public LoginResponse uploadImage(ImageRequest image){
        return imageService.sendImage(image);
    }
}
