package com.totwo.chat.repository;

import com.totwo.chat.entity.User;
import com.totwo.chat.repository.base.DynamoBaseRepository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends DynamoBaseRepository<User> {

    public UserRepository(DynamoDbEnhancedClient enhancedClient) {
        super(enhancedClient, User.class);
    }
}
