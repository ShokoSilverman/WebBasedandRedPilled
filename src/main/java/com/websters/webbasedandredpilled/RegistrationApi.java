package com.websters.webbasedandredpilled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.management.openmbean.KeyAlreadyExistsException;

@CrossOrigin
@RestController
@RequestMapping("/registration")
public class RegistrationApi {

    @Autowired
    private MainControllerBLL mainBll;
    @Autowired
    private MongoDAL mongoDAL;

    @RequestMapping(path = "/createUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code= HttpStatus.CREATED)
    public String addUser(@RequestBody UsersToAdd newUser){
        try{
            mongoDAL.writeUser(newUser);
        }catch (KeyAlreadyExistsException keyE){
            keyE.printStackTrace();
        }
        return newUser.getUsername() + " added!";
    }



}
