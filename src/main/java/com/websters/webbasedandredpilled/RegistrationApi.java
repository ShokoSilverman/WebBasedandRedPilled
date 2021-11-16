package com.websters.webbasedandredpilled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration")
public class RegistrationApi {

    @Autowired
    private MainControllerBLL mainBll;

    @RequestMapping(path = "/createUser", method = RequestMethod.POST)
    @ResponseStatus(code= HttpStatus.CREATED)
    public String addUser(@RequestBody UsersToAdd newUser){
        return "didnt mean to be in main";
    }


}
