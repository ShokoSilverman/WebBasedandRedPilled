package com.websters.webbasedandredpilled.Repos;

import com.websters.webbasedandredpilled.Models.ChatLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatLogRepo extends MongoRepository<ChatLog, String> {
}
