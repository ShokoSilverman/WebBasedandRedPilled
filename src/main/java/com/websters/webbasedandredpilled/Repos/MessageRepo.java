package com.websters.webbasedandredpilled.Repos;

import com.websters.webbasedandredpilled.MessagePOJO;
import com.websters.webbasedandredpilled.UsersToAdd;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepo extends MongoRepository<MessagePOJO, String> {
}
