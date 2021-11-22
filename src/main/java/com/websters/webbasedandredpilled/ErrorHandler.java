package com.websters.webbasedandredpilled;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ErrorHandler implements ErrorController {
    @RequestMapping(path="/error", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ExceptionMessage handleError(HttpServletRequest req, HttpServletResponse res) {

        Integer statusCode = (Integer) req.getAttribute("javax.servlet.error.status_code");
        System.out.println("******status code: " + statusCode);

        Exception exception = (Exception) req.getAttribute("javax.servlet.error.exception");
        System.out.println("*****exception: " + exception);

        ExceptionMessage message = new ExceptionMessage("My exception message: " + exception.getMessage()
                ,statusCode, exception);
        System.out.println("****the message: " + message);


//        res.setStatus(HttpServletResponse.SC_FORBIDDEN); //403
        return message;
    }

    public String getErrorPath() {
        return "/error";
    }
}

