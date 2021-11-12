package com.websters.webbasedandredpilled;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Users {
    @Setter
    @Getter
    @Id
    private String id;
    @Getter
    @Setter
    private String userName;
    @Getter
    @Setter
    private String passWord;
    @Getter
    @Setter
    private String email;

    public Users(String userName, String passWord, String email) {
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
    }

    public Users(){}
}
