package com.websters.webbasedandredpilled;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Document
@Data//creates getters and setters for everything in here
public class UsersToAdd implements UserDetails {
//    @Setter
//    @Getter
//    @Id
    //private String id;//do not include in any of the constuctors
    private String username;
    private String password;
    @Id
    private String email;
    private String[] securityRoles;
    private boolean isActive;

    public UsersToAdd(String userName, String passWord, String email, String[] securityRoles, boolean isActive) {
        this.username = userName;
        this.password = passWord;
        this.email = email;
        this.securityRoles = securityRoles;
        this.isActive = isActive;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        for (String securityRole : securityRoles){
            grantedAuthorityList.add(new SimpleGrantedAuthority(securityRole));
        }
        return grantedAuthorityList;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}
