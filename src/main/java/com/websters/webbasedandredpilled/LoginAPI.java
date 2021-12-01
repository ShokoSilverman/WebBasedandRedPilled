package com.websters.webbasedandredpilled;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.lang.Maps;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/anyone")
public class LoginAPI {
    @Autowired
    MainControllerBLL bll;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.CREATED)
    public Map<String, Object> login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Login API Hit");
        Map returnMap = new HashMap<>();
        if (bll.verifyUserCredentials(username, password)) {
            // Make JWT Token
            String jwt = JWTPOJO.from(username, Map.of()).getToken();
            returnMap.put("JWT", jwt);
            returnMap.put("Success", true);
        } else {
            returnMap.put("Success", false);
        }
        return returnMap;
    }

    //Logout handled by JavaScript

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public boolean validateJWT(@RequestBody String jwtToken) {
        String username = null;
        JWTPOJO jwt = null;
        //Compare Token and IF correct return True
        try {
            //Token to Compare
            JWTPOJO.parseToken(jwtToken);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Unable to get JWT Token.");
        } catch (ExpiredJwtException e) {
            System.out.println("JWT Token has expired.");
        } catch (SignatureException e) {
            System.out.println("JWT has been compromised!");
        }
        return false;
    }
}
