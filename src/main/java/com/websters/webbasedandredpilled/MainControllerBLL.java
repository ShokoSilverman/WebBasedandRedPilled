package com.websters.webbasedandredpilled;

import com.websters.webbasedandredpilled.Repos.UserRepo;
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
    @Autowired
    private UserRepo userRepo;
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

    public boolean verifyUserCredentials(String username, String password) {
        UserPOJO currentUser = userRepo.findFirstByUsername(username);
        boolean correctCreds = false;
        if (BCrypt.checkpw(password, currentUser.getPassword())) {
            System.out.println("BLL Credentials match");
            correctCreds = true;
            System.out.println("Validate Creds: " + correctCreds);
        }else{
            //TODO throw custom error
        }
        return correctCreds;
    }


    public void writeError(MessagePOJO message){
        mongo.writeMessage(message);
    }

    //if (BCrypt.checkpw(candidate_password, stored_hash))
    //            System.out.println("It matches");
    //        else
    //            System.out.println("It does not match");
    //        System.out.println(pw_hash);
    //checks the password





}
