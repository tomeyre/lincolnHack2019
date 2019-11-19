package com.example.testies.sack.controllers;

import com.example.testies.sack.pojos.Login;
import com.example.testies.sack.pojos.LoginResponse;
import com.example.testies.sack.respositories.UserRepository;
import com.example.testies.sack.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping(value = "/login")
    public LoginResponse login(@RequestBody Login login){
        return loginService.userLogin(login);
    }
}
