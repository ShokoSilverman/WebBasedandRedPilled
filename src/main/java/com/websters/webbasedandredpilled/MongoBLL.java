package com.websters.webbasedandredpilled;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class MongoBLL {

    MongoClient mongoClient = new MongoClient("localhost", 27017);
    MongoDatabase database = mongoClient.getDatabase("ChadRoomDB");
    MongoCollection<Document> chatLogsCol = database.getCollection("ChatLogs");
    MongoCollection<Document> errorLogsCol = database.getCollection("ErrorLogs");
    MongoCollection<Document> usersCol = database.getCollection("Users");


    @PostConstruct
    public void connectionTest(){
        System.out.println("Database Started");
        System.out.println(database);
        System.out.println(usersCol);
        System.out.println(chatLogsCol);
        System.out.println(errorLogsCol);
    }

}
