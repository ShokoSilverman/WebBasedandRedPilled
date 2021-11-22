package com.websters.webbasedandredpilled.Repos;

import com.websters.webbasedandredpilled.ErrorLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorLogRepo extends MongoRepository<ErrorLog, String> {
}
