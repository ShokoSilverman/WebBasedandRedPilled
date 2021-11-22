package com.websters.webbasedandredpilled.Repos;

import com.websters.webbasedandredpilled.UserPOJO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends MongoRepository<UserPOJO, String> {
    List<UserPOJO> findByUsernameEquals(String userName); //returns a list of usersToAdd with the username equal to this
    UserPOJO findFirstByUsername(String userName);
}
