package com.totwo.chat.repository;

import com.totwo.chat.entity.Message;
import com.totwo.chat.repository.base.DynamoBaseRepository;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

@Repository
public class MessageRepository extends DynamoBaseRepository<Message> {

    public MessageRepository(DynamoDbEnhancedClient enhancedClient) {
        super(enhancedClient, Message.class);
    }
}
