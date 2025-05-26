package com.totwo.chat.entity;

import com.totwo.chat.entity.base.DynamoBaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@DynamoDbBean
public class ChatRoomParticipant extends DynamoBaseEntity {
    // PK: ROOM#roomId, SK: USER#u1@gmail...
}
