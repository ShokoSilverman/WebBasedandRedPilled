package com.websters.webbasedandredpilled.Repos;

import com.websters.webbasedandredpilled.UsersToAdd;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends MongoRepository<UsersToAdd, String> {
    List<UsersToAdd> findByUserNameEquals(String userName); //returns a list of usersToAdd with the username equal to this
}
