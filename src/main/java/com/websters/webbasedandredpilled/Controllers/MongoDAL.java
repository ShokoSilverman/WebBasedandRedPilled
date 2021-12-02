package com.websters.webbasedandredpilled.Controllers;


import com.websters.webbasedandredpilled.Models.ChatLog;
import com.websters.webbasedandredpilled.Models.ErrorLog;
import com.websters.webbasedandredpilled.Models.MessagePOJO;
import com.websters.webbasedandredpilled.Repos.ChatLogRepo;
import com.websters.webbasedandredpilled.Repos.ErrorLogRepo;
import com.websters.webbasedandredpilled.Repos.MessageRepo;
import com.websters.webbasedandredpilled.Repos.UserRepo;
import com.websters.webbasedandredpilled.Models.UserPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.management.openmbean.KeyAlreadyExistsException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class MongoDAL {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ChatLogRepo chatLogRepo;
    @Autowired
    private ErrorLogRepo errorLogRepo;
    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private MainControllerBLL mainControllerBLL;

    @PostConstruct
    public void connectionTest(){
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String startTime = LocalDateTime.now().format(myFormatObj);
        System.out.println("start time " + startTime);
        UserPOJO testUser = new UserPOJO("admin", "securePass", "email@gmail.com", new String[]{"USER", "ADMIN"}, true);
        //writeUser(testUser);//use this to test and turn on db
        ChatLog newChatLog = new ChatLog("newUser", new MessagePOJO[]{});
        //writeChatLog(newChatLog);
        //ErrorLog newErrorLog = new ErrorLog( "spooky stack trace", "hella broken");
        //writeError(newErrorLog);
        //System.out.println(setIsActive("email@email.com", false));
        //System.out.println(newUserName("email@email.com", "testPass", "newName"));
        //System.out.println(newPassWord("email@email.com", "testPass", "newPass"));
        //userNameList();
        MessagePOJO newMessage = new MessagePOJO("this is a message", "josh");
        //writeMessage(newMessage);
        //System.out.println(userExists("newName", "newPass"));
        //mainControllerBLL.encryptPass("testPass");

    }

    public void writeUser(UserPOJO newUser){
        //test values for db connection
        if (userRepo.findById(newUser.getEmail()).isPresent()){
            throw new KeyAlreadyExistsException("email already exists!");//if the email exists already, get mad
        }
        if (userRepo.findByUsernameEquals(newUser.getUsername()).size() > 0){
            throw new KeyAlreadyExistsException("Username already exists");//if the name exists already get mad
        }
        userRepo.save(newUser);
        System.out.println("user saved");
    }

    public void writeError(ErrorLog newError){
        errorLogRepo.save(newError);
        System.out.println("Error saved");
    }

    public void writeChatLog(ChatLog newChatLog){
        chatLogRepo.save(newChatLog);
        System.out.println("ChatLog saved");

    }

    public void writeMessage(MessagePOJO message){
        messageRepo.save(message);
        System.out.println("message saved");
    }

    //read in all users
    //read to security
    //check the security class

    //active on off
    public String setIsActive(String username, boolean setActive){ //true = is active | false = not active //takes in the email because it is the ID
        //Optional<UserPOJO> userToAddOpt = userRepo.findById(email);
        Optional<UserPOJO> userToAddOpt = userRepo.findFirstByUsernameIs(username);
        if (userToAddOpt.isPresent()){
            UserPOJO userToAdd = userToAddOpt.get(); //only grabs if it exists
            userToAdd.setActive(setActive);
            userRepo.save(userToAdd);
            return userToAdd.getUsername() + " activity set to: " + userToAdd.isActive();
        }else{
            return String.format("%s not found", username);
        }
    }

    public String newUserName(String email, String passWord, String newUserName){
        Optional<UserPOJO> userToAddOpt = userRepo.findById(email);
        if (userRepo.findByUsernameEquals(newUserName).size() > 0){
            throw new KeyAlreadyExistsException("User already exists");//if the name exists already get mad
        }
        if (userToAddOpt.isPresent()){ //check if user exists
            UserPOJO userToAdd = userToAddOpt.get();
            if (passWord.equals(userToAdd.getPassword())){//check if incorrect password
                userToAdd.setUsername(newUserName);
                userRepo.save(userToAdd);
                return newUserName +" saved!";
            }else{
                return "Incorrect password";
            }
        }else{
            return "User not found";
        }
    }

    public String newPassWord(String email, String oldPassWord, String newPassWord){
        Optional<UserPOJO> userToAddOpt = userRepo.findById(email);
        if (userToAddOpt.isPresent()){ //check if user exists
            UserPOJO userToAdd = userToAddOpt.get();
            if (oldPassWord.equals(userToAdd.getPassword())){//check if incorrect password
                userToAdd.setPassword(newPassWord);
                userRepo.save(userToAdd);
                return "New password saved!";
            }else{
                return "Incorrect password";
            }
        }else{
            return "User not found";
        }
    }

    public void userNameList(){
        System.out.println("getting lists of names");
        List<UserPOJO> userNameList = userRepo.findByUsernameEquals("newName");
        for(UserPOJO userPOJO : userNameList){
            System.out.println(userPOJO.getUsername());
        }
    }

    public boolean userExists(String userName, String password){
        List<UserPOJO> userPOJOList = userRepo.findByUsernameEquals(userName);
        if (userPOJOList.get(0) != null){
            //throw an custom error if password incorrect
            return password.equals(userPOJOList.get(0).getPassword());//returns true if password matches //false if incorrect password
        }else{
            return false;//false if user does not exist
            //throw custom error if the user does not exist
        }

    }

//    public boolean userIsValid(UserPOJO userPOJO){
//        return userPOJO.isActive();
//    }



}
