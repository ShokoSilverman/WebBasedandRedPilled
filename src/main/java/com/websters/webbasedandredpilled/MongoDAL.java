package com.websters.webbasedandredpilled;


import com.websters.webbasedandredpilled.Repos.ChatLogRepo;
import com.websters.webbasedandredpilled.Repos.ErrorLogRepo;
import com.websters.webbasedandredpilled.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.websters.webbasedandredpilled.UsersToAdd;

import javax.annotation.PostConstruct;
import javax.management.openmbean.KeyAlreadyExistsException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class MongoDAL {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ChatLogRepo chatLogRepo;
    @Autowired
    private ErrorLogRepo errorLogRepo;

    @PostConstruct
    public void connectionTest(){
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String startTime = LocalDateTime.now().format(myFormatObj);
        System.out.println("start time " + startTime);
        UsersToAdd testUser = new UsersToAdd("testUser", "testPass", "email@email.com", new String[]{"USER", "ADMIN"}, true);
        //writeUser(testUser);//use this to test and turn on db
        ChatLog newChatLog = new ChatLog("newUser", new MessagePOJO[]{});
        writeChatLog(newChatLog);
        ErrorLog newErrorLog = new ErrorLog("now", "spooky stack trace", "hella broken");
        writeError(newErrorLog);

    }

    public void writeUser(UsersToAdd newUser){
        //test values for db connection
        if (userRepo.findById(newUser.getUserName()).isPresent()){
            throw new KeyAlreadyExistsException("User already exists");
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

    //read in all users
    //read to security
    //active on off



}
