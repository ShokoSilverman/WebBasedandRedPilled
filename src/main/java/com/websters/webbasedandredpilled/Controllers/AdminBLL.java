package com.websters.webbasedandredpilled.Controllers;

import com.websters.webbasedandredpilled.Repos.UserRepo;
import com.websters.webbasedandredpilled.UserPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AdminBLL {

    @Autowired
    private UserRepo userRepo;

    public String setAdmin(String username){
        Optional<UserPOJO> optUser = userRepo.findFirstByUsernameIs(username);
        if (optUser.isPresent()){
            UserPOJO userPOJO = optUser.get();
            List<String> userRoles = Arrays.asList(userPOJO.getSecurityRoles()); //grabs the user's role list, casts it to list
            if (userRoles.contains("ADMIN")) return "User is already admin!"; //checks if the user is already an admin
            userRoles.add("ADMIN");
            userPOJO.setSecurityRoles((String[]) userRoles.toArray()); //sets the user roles to be the new list
            return String.format("%s has been set to an admin", username);
        }else{
            return "User not found";
        }
    }

}
