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
    private LocalDateTime timeSent;

    public MessagePOJO(String messageContent, String sentBy, LocalDateTime timeSent) {
        this.messageContent = messageContent;
        this.sentBy = sentBy;
        this.timeSent = timeSent;
    }

    public MessagePOJO(){}

    public String getTimeAsString(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
        String timeAsString = timeSent.format(dtf);
        return timeAsString;
    }
}
