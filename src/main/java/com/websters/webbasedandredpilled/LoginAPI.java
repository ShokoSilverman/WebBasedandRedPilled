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
    public Map<String, Object> login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {

        Map returnMap = new HashMap<>();
        Map verifyMap = bll.verifyUserCredentials(username, password);
        if(verifyMap.get("isActive").equals(true)) {
            returnMap.put("isBanned", true);
            if (verifyMap.get("correctCredentials").equals(true)) {
                // Make JWT Token
                String jwt = JWTPOJO.from(username, Map.of()).getToken();
                //Pass back the JWT and Login Success
                returnMap.put("JWT", jwt);
                returnMap.put("Success", true);
            } else {
                returnMap.put("Success", false);
            }
        }else{
            returnMap.put("isBanned", false);
        }
        return returnMap;
    }

    //Logout handled by JavaScript

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public boolean validateJWT(@RequestBody String jwtToken) {
        //Compare Token and IF correct return True
        try {
            //Compare Token and catch exception if incorrect.
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
