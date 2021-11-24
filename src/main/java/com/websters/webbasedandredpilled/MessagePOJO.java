package com.websters.webbasedandredpilled;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MessagePOJO {
    @Getter
    @Setter
    private String messageContent;
    @Getter
    @Setter
    private String sentBy;
    @Getter
    @Setter
    private String timeSent;

    public MessagePOJO(String messageContent, String sentBy) {
        this.messageContent = messageContent;
        this.sentBy = sentBy;
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.timeSent = LocalDateTime.now().format(myFormatObj);
    }

    public MessagePOJO(){}

    public void setTimeSent() {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.timeSent = LocalDateTime.now().format(myFormatObj);
    }
}
