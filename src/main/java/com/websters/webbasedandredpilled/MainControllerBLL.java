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
import java.util.HashMap;
import java.util.Map;

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

    public Map<String, Object> verifyUserCredentials(String username, String password) {
        Map returnMap = new HashMap<>();
        UserPOJO currentUser = userRepo.findFirstByUsername(username);
        if (!currentUser.isActive()){
            //checks if the user is active, if not, do not sign them on
            returnMap.put("isActive", false);
        }else{
            returnMap.put("isActive", true);
        }
        if (BCrypt.checkpw(password, currentUser.getPassword())) {
            System.out.println("BLL Credentials match");
            returnMap.put("correctCredentials", true);
            System.out.println("Validate Creds: " + returnMap.get("correctCredentials"));
        }else{
            //TODO throw custom error
            returnMap.put("correctCredentials", false);
        }
        return returnMap;
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
