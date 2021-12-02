package com.websters.webbasedandredpilled.Repos;

import com.websters.webbasedandredpilled.Models.MessagePOJO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepo extends MongoRepository<MessagePOJO, String> {
}
