package com.websters.webbasedandredpilled;

import java.util.ArrayList;

public class MainControllerBLL {
    //List of all Messages sent by User between Login and Sign out
    //TODO Make this a Session Variable
    public ArrayList<MessagePOJO> messageList = new ArrayList<>();
    //List of all Errors that occur while User is on any page
    public ArrayList<ErrorLog> errorList = new ArrayList<>();



}
