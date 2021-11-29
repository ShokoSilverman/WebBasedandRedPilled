package com.websters.webbasedandredpilled;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/anyone")
public class AnyoneAPI {
    @RequestMapping(path = "/index", method= RequestMethod.GET)
    public String speak() {
        return "this reached anyone";
    }

    @GetMapping(path = "/{action}") // Different than before
    public String findById(@PathVariable String action) {

        if (action != null)    {
            switch(action) {
                case "0":
                    System.out.println("Enter action is 0");
                    return "Hello World";
                case "1":
                    System.out.println("Enter action = 1: IllegalArgumentException");
                    throw  new IllegalArgumentException("My Error IllegalArgumentException. Action is 1");
                case "2":
//                    System.out.println("Enter action = 2: RuntimeException");
                    throw new RuntimeException("My error RuntimeException. Action is 2");
                case "3":
                    System.out.println("Enter action = 3: IllegalCallerException");
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
