package com.websters.webbasedandredpilled;


import com.websters.webbasedandredpilled.Repos.ChatLogRepo;
import com.websters.webbasedandredpilled.Repos.ErrorLogRepo;
import com.websters.webbasedandredpilled.Repos.MessageRepo;
import com.websters.webbasedandredpilled.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.websters.webbasedandredpilled.UsersToAdd;

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

    @PostConstruct
    private void connectionTest(){
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String startTime = LocalDateTime.now().format(myFormatObj);
        System.out.println("start time " + startTime);
        UsersToAdd testUser = new UsersToAdd("admin", "securePass", "email@gmail.com", new String[]{"USER", "ADMIN"}, true);
        //writeUser(testUser);//use this to test and turn on db
        ChatLog newChatLog = new ChatLog("newUser", new MessagePOJO[]{});
        //writeChatLog(newChatLog);
        ErrorLog newErrorLog = new ErrorLog( "spooky stack trace", "hella broken");
        //writeError(newErrorLog);
        //System.out.println(setIsActive("email@email.com", false));
        //System.out.println(newUserName("email@email.com", "testPass", "newName"));
        //System.out.println(newPassWord("email@email.com", "testPass", "newPass"));
        //userNameList();
        MessagePOJO newMessage = new MessagePOJO("this is a message", "josh");
        //writeMessage(newMessage);
        //System.out.println(userExists("newName", "newPass"));

    }

    private void writeUser(UsersToAdd newUser){
        //test values for db connection
        if (userRepo.findById(newUser.getEmail()).isPresent()){
            throw new KeyAlreadyExistsException("User already exists");//if the email exists already, get mad
        }
        if (userRepo.findByUsernameEquals(newUser.getUsername()).size() > 0){
            throw new KeyAlreadyExistsException("User already exists");//if the name exists already get mad
        }
        userRepo.save(newUser);
        System.out.println("user saved");
    }

    private void writeError(ErrorLog newError){
        errorLogRepo.save(newError);
        System.out.println("Error saved");
    }
    private void writeChatLog(ChatLog newChatLog){
        chatLogRepo.save(newChatLog);
        System.out.println("ChatLog saved");

    }

    private void writeMessage(MessagePOJO message){
        messageRepo.save(message);
        System.out.println("message saved");
    }

    //read in all users
    //read to security
    //check the security class

    //active on off
    public String setIsActive(String email, boolean setActive){ //true = is active | false = not active //takes in the email because it is the ID
        Optional<UsersToAdd> userToAddOpt = userRepo.findById(email);
        if (userToAddOpt.isPresent()){
            UsersToAdd userToAdd = userToAddOpt.get(); //only grabs if it exists
            userToAdd.setActive(setActive);
            userRepo.save(userToAdd);
            return userToAdd.getUsername() + " activity set to: " + userToAdd.isActive();
        }else{
            return email + " not found";
        }
    }

    public String newUserName(String email, String passWord, String newUserName){
        Optional<UsersToAdd> userToAddOpt = userRepo.findById(email);
        if (userRepo.findByUsernameEquals(newUserName).size() > 0){
            throw new KeyAlreadyExistsException("User already exists");//if the name exists already get mad
        }
        if (userToAddOpt.isPresent()){ //check if user exists
            UsersToAdd userToAdd = userToAddOpt.get();
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
        Optional<UsersToAdd> userToAddOpt = userRepo.findById(email);
        if (userToAddOpt.isPresent()){ //check if user exists
            UsersToAdd userToAdd = userToAddOpt.get();
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
        List<UsersToAdd> userNameList = userRepo.findByUsernameEquals("newName");
        for(UsersToAdd usersToAdd: userNameList){
            System.out.println(usersToAdd.getUsername());
        }
    }

    public boolean userExists(String userName, String password){
        List<UsersToAdd> usersToAddList = userRepo.findByUsernameEquals(userName);
        if (usersToAddList.get(0) != null){
            //throw an custom error if password incorrect
            return password.equals(usersToAddList.get(0).getPassword());//returns true if password matches //false if incorrect password
        }else{
            return false;//false if user does not exist
            //throw custom error if the user does not exist
        }

    }



}
