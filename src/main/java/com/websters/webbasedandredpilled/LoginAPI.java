package com.websters.webbasedandredpilled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@CrossOrigin
@RestController
@RequestMapping("/anyone")
public class LoginAPI {
    @Autowired
    MainControllerBLL bll;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseStatus(code= HttpStatus.CREATED)
    public String login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password, HttpServletRequest request, HttpServletResponse response){
        System.out.println("Login API Hit");
        if(bll.verifyUserCredentials(username, password)){
            //TODO Start Session and make Cookie
            System.out.println("User: " + username + " is valid");
            HttpSession session = request.getSession();
            System.out.println("Session = " + session);
            Cookie login = new Cookie("login", "true");
            Cookie currentUser = new Cookie("username", username);
            //1 Hour Expiration
            login.setMaxAge(60*60);
            response.addCookie(login);
            response.addCookie(currentUser);
        }else{
            //TODO throw some error
        }
        //System.out.println("Login API Hit");
        return "http://localhost:81/";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Logout Hit");
        HttpSession session = request.getSession(false);
        if (session != null) {
            Cookie removeLogin = new Cookie("login", "");
            removeLogin.setMaxAge(0);
            response.addCookie(removeLogin);
            session.invalidate();
            System.out.println("Session Invalidated");
        }
        else if (session == null){
            System.out.println("Session is Null");
        }
        return "http://localhost:80/registration.html";  //Where you go after logout here.
    }


}
