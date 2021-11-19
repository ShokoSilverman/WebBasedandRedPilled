package com.websters.webbasedandredpilled;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminAPI {

    @PatchMapping(path = "/addAdmin")
    @ResponseStatus(code=HttpStatus.CREATED)
    public String updateUserToAdmin(){
        //Initialize Message to Return to User
        //Find User to make admin
        //Add ADMIN to their security
        //Return Message for User.
        return "";
    }

    @PatchMapping(path = "/disableUser")
    @ResponseStatus(code= HttpStatus.CREATED)
    public String disableUser(){
        //Find User
        //Change User isActive value to False
        //Return a message for the user

        return"";
    }


}
