package com.websters.webbasedandredpilled.APIs;

import com.websters.webbasedandredpilled.Controllers.MainControllerBLL;
import com.websters.webbasedandredpilled.Models.MessagePOJO;
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


    //throws different errors to test the error logging and error handler
    @GetMapping(path = "/{action}") // Different than before
    public String findById(@PathVariable String action) {

        if (action != null)    {
            switch(action) {
                case "0":
                    System.out.println("Enter action is 0");
                    return "Hello World";
                case "1":
                    throw  new IllegalArgumentException("My Error IllegalArgumentException. Action is 1");
                case "2":
                    throw new RuntimeException("My error RuntimeException. Action is 2");
                case "3":
                    throw new IllegalCallerException("My error Illegal Caller Exception. Action is 3");
                default:
                    System.out.println("Enter no action");
                    return "action value not in the range of 0 through 3";
            }
        }
        else
            return "Please include action=0-3 (example /1)";
    }
}
