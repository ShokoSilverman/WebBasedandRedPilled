package com.websters.webbasedandredpilled.APIs;

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
