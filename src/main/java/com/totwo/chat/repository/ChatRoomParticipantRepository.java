package com.totwo.chat.repository;

import com.totwo.chat.entity.ChatRoomParticipant;
import com.totwo.chat.repository.base.DynamoBaseRepository;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

@Repository
public class ChatRoomParticipantRepository extends DynamoBaseRepository<ChatRoomParticipant> {

    public ChatRoomParticipantRepository(DynamoDbEnhancedClient enhancedClient) {
        super(enhancedClient, ChatRoomParticipant.class);
    }
}
