package com.totwo.chat.repository;

import com.totwo.chat.entity.ChatRoom;
import com.totwo.chat.repository.base.DynamoBaseRepository;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

@Repository
public class ChatRoomRepository extends DynamoBaseRepository<ChatRoom> {

    public ChatRoomRepository(DynamoDbEnhancedClient enhancedClient) {
        super(enhancedClient, ChatRoom.class);
    }
}
