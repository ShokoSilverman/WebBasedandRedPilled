package com.websters.webbasedandredpilled;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MainControllerBLL {
    //List of all Messages sent by User between Login and Sign out
    //TODO Make this a Session Variable
    public ArrayList<MessagePOJO> messageList = new ArrayList<>();
    //List of all Errors that occur while User is on any page
    public ArrayList<ErrorLog> errorList = new ArrayList<>();

    public boolean passwordValidation(String password){
        /**verify if the password is secure by checking against a regex**/
        return true; //temporary, return true if passes regex, false if otherwise
    }





}
