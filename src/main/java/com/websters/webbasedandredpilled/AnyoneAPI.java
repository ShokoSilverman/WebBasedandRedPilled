package com.websters.webbasedandredpilled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/anyone")
public class AnyoneAPI {
    @Autowired
    private MainControllerBLL mainControllerBLL;

    @RequestMapping(path = "/index", method= RequestMethod.GET)
    public String speak() {
        return "this reached anyone";
    }

    @RequestMapping(path="/logMessage", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code= HttpStatus.CREATED)
    public void logMessage(@RequestBody MessagePOJO message){
        message.setTimeSent();
        mainControllerBLL.writeError(message);
    }
}
