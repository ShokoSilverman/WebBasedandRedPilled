package com.websters.webbasedandredpilled.Controllers;

import com.websters.webbasedandredpilled.UsernamePOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserAPI {

    @GetMapping(path="/speak")
    public String speak(){
        return "user api speaking";
    }




}
