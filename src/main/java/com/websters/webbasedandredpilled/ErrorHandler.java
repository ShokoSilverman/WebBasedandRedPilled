package com.websters.webbasedandredpilled;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintWriter;
import java.io.StringWriter;

//gets called when an error occurs on the java side
@RestController
public class ErrorHandler implements ErrorController {
    @Autowired
    private MongoDAL mongo;

    @RequestMapping(path="/error", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String handleError(HttpServletRequest req, HttpServletResponse res) {

        //gets the status code of the error
        Integer statusCode = (Integer) req.getAttribute("javax.servlet.error.status_code");

        //gets the exception itself which we can then save
        Exception exception = (Exception) req.getAttribute("javax.servlet.error.exception");

        //uses the custom message to display to the user what happened
        String smessage = "An error accrued: \nError message: " + exception.getMessage() + "\nError Status Code: " + statusCode;

        //turns the stack trace into a string which we store in the db
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);

        //makes the error log object and stores it into the db
        ErrorLog eLog = new ErrorLog(sw.toString(), exception.getMessage(), statusCode);
        mongo.writeError(eLog);

        //sets the forbidden status on the page
        res.setStatus(HttpServletResponse.SC_FORBIDDEN); //403

        //returns the message to the user which tells them what happened
        return smessage;
    }

    public String getErrorPath() {
        return "/error";
    }
}

