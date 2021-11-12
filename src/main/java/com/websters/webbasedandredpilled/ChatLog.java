package com.websters.webbasedandredpilled;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document
public class ChatLog {
    @Getter
    @Setter
    @Id
    private String id;
    @Getter
    @Setter
    private String userName;
    @Getter
    @Setter
    private MessagePOJO[] messages;

    public ChatLog(String userName, MessagePOJO[] messages) {
        this.userName = userName;
        this.messages = messages;
    }
    public ChatLog(){}
}
