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
    private String time;
    @Getter
    @Setter
    private String stackTrace;
    @Getter
    @Setter
    private String errorType;

    public ErrorLog(String stackTrace, String errorType) {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.time = LocalDateTime.now().format(myFormatObj);
        this.stackTrace = stackTrace;
        this.errorType = errorType;
    }

    public ErrorLog(){}

}
