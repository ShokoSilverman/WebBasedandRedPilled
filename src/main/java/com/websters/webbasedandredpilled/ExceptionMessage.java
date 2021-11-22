package com.websters.webbasedandredpilled;

public class ExceptionMessage {
    private String exceptionMessage;
    private Integer exceptionHttpStatus;
    private Throwable exceptionThrowable = null;

    public ExceptionMessage(String message, Integer httpStatus, Throwable th) {
        this.exceptionThrowable = th;
        this.exceptionMessage = message;
        this.exceptionHttpStatus = httpStatus;
    }

    public ExceptionMessage(String message, Integer httpStatus) {
        this.exceptionMessage = message;
        this.exceptionHttpStatus = httpStatus;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public Integer getExceptionHttpStatus() {
        return exceptionHttpStatus;
    }

    public void setExceptionHttpStatus(Integer exceptionHttpStatus) {
        this.exceptionHttpStatus = exceptionHttpStatus;
    }

    public Throwable getExceptionThrowable() {
        return exceptionThrowable;
    }

    public void setExceptionThrowable(Throwable exceptionThrowable) {
        this.exceptionThrowable = exceptionThrowable;
    }


}

