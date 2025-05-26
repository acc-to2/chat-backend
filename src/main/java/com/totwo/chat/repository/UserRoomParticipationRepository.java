package com.totwo.chat.repository;

import com.totwo.chat.entity.UserRoomParticipation;
import com.totwo.chat.repository.base.DynamoBaseRepository;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

@Repository
public class UserRoomParticipationRepository extends DynamoBaseRepository<UserRoomParticipation> {

    public UserRoomParticipationRepository(DynamoDbEnhancedClient enhancedClient) {
        super(enhancedClient, UserRoomParticipation.class);
    }
}
