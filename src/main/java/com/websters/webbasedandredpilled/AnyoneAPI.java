package com.websters.webbasedandredpilled;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/anyone")
public class AnyoneAPI {
    @RequestMapping(path = "/index", method= RequestMethod.GET)
    public String speak() {
        return "this reached anyone";
    }
}
