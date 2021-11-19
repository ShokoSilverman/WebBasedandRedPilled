package com.websters.webbasedandredpilled;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@CrossOrigin
@RestController
@RequestMapping("/anyone")
public class LoginAPI {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        //System.out.println("Login API Hit");
        return "http://localhost:80/chatRoom.html";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        System.out.println("Logout Hit");
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            System.out.println("Session Invalidated");
        }
        else if (session == null){
            System.out.println("Session is Null");
        }
        return "http://localhost:80/registration.html";  //Where you go after logout here.
    }


}
