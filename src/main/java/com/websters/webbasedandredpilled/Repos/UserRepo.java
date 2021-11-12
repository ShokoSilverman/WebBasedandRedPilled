package com.websters.webbasedandredpilled.Repos;

import com.websters.webbasedandredpilled.UsersToAdd;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<UsersToAdd, String> {
}
