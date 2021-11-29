package com.websters.webbasedandredpilled;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//gets called when an error occurs on the java side
@RestController
public class ErrorHandler implements ErrorController {
    @RequestMapping(path="/error", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String handleError(HttpServletRequest req, HttpServletResponse res) {

        //gets the status code of the error
        Integer statusCode = (Integer) req.getAttribute("javax.servlet.error.status_code");

        //gets the exception itself which we can then save
        Exception exception = (Exception) req.getAttribute("javax.servlet.error.exception");

        //makes the custom exception message
        ExceptionMessage message = new ExceptionMessage(exception.getMessage()
                ,statusCode, exception);

        //uses the custom message to display to the user what happened
        String smessage = "An error accrued: \nError message: " + message.getExceptionMessage() + "\nError Status Code: " + message.getExceptionHttpStatus();

        //the stack trace can be logged here
        System.out.println("the message TO SAVE: ");
        exception.printStackTrace();

        res.setStatus(HttpServletResponse.SC_FORBIDDEN); //403
        return smessage;
    }

    public String getErrorPath() {
        return "/error";
    }
}

