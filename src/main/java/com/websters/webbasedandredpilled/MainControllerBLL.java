package com.websters.webbasedandredpilled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.ArrayList;

@Service
public class MainControllerBLL {

    @Autowired
    private MongoDAL mongo;

    //List of all Messages sent by User between Login and Sign out
    //TODO Make this a Session Variable
    public ArrayList<MessagePOJO> messageList = new ArrayList<>();
    //List of all Errors that occur while User is on any page
    public ArrayList<ErrorLog> errorList = new ArrayList<>();


    public String encryptPass(String password){
        String pw_hash = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println("salted and hashpilled");
        return pw_hash;

    }

    public void writeError(String messageContent, String sentBy){
        MessagePOJO message = new MessagePOJO(messageContent, sentBy);
        mongo.writeMessage(message);
    }

    //if (BCrypt.checkpw(candidate_password, stored_hash))
    //            System.out.println("It matches");
    //        else
    //            System.out.println("It does not match");
    //        System.out.println(pw_hash);
    //checks the password





}