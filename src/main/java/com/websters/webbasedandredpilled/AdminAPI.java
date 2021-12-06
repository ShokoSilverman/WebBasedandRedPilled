package com.websters.webbasedandredpilled;

import com.websters.webbasedandredpilled.Controllers.AdminBLL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminAPI {

    //auth doesnt work

    @Autowired
    private AdminBLL adminBLL;

    @Autowired
    private MongoDAL mongo;

    @GetMapping(path="/speak")
    public String speak(){
        return "admin api speaking";
    }

    @PatchMapping(path = "/makeAdmin", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code=HttpStatus.CREATED)
    public String updateUserToAdmin(@RequestBody UsernamePOJO username){
        return adminBLL.setAdmin(username.getUsername());
    }

    @PatchMapping(path = "/isActiveUser", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code= HttpStatus.CREATED)
    public String disableUser(@RequestBody userDisablePOJO userDisablePOJO){
        return mongo.setIsActive(userDisablePOJO.getUsername(), Boolean.parseBoolean(userDisablePOJO.getIsActive()));
    }


}
