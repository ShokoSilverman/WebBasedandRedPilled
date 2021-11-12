package com.websters.webbasedandredpilled;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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

    public ErrorLog(String time, String stackTrace, String errorType) {
        this.time = time;
        this.stackTrace = stackTrace;
        this.errorType = errorType;
    }

    public ErrorLog(){}
}
