package com.totwo.chat.repository;

import com.totwo.chat.entity.Friend;
import com.totwo.chat.repository.base.DynamoBaseRepository;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

@Repository
public class FriendRepository extends DynamoBaseRepository<Friend> {

    public FriendRepository(DynamoDbEnhancedClient enhancedClient) {
        super(enhancedClient, Friend.class);
    }
}
