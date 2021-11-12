package com.websters.webbasedandredpilled;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Document
public class ErrorLog {
    @Getter
    @Setter
    @Id
    private String id;
    @Getter
    @Setter
    private LocalDateTime time;
    @Getter
    @Setter
    private String stackTrace;
    @Getter
    @Setter
    private String errorType;

    public ErrorLog(LocalDateTime time, String stackTrace, String errorType) {
        this.time = time;
        this.stackTrace = stackTrace;
        this.errorType = errorType;
    }

    public ErrorLog(){}

    public String getTimeAsString(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
        String timeAsString = time.format(dtf);
        return timeAsString;
    }
}
