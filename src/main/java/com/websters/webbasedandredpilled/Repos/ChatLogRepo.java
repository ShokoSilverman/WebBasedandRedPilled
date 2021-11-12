package com.websters.webbasedandredpilled.Repos;

import com.websters.webbasedandredpilled.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatLogRepo extends MongoRepository<Users, String> {
}
