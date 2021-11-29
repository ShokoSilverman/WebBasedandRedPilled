package com.websters.webbasedandredpilled;

import com.websters.webbasedandredpilled.Controllers.AdminBLL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminAPI {

    @Autowired
    private AdminBLL adminBLL;

    @PatchMapping(path = "/addAdmin")
    @ResponseStatus(code=HttpStatus.CREATED)
    public String updateUserToAdmin(@RequestParam(value = "username") String username){
        return adminBLL.setAdmin(username);
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
