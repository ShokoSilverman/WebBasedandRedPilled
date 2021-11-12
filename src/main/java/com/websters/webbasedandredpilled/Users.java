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
    @Getter
    @Setter
    private String[] securityRoles;
    @Getter
    @Setter
    private boolean isActive;

    public Users(String userName, String passWord, String email, String[] securityRoles, boolean isActive) {
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.securityRoles = securityRoles;
        this.isActive = isActive;
    }

    public Users(){}
}
