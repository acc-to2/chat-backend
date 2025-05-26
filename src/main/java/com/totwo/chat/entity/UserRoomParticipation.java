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
public class UserRoomParticipation extends DynamoBaseEntity {
    private String lastReadMessage;  // MSG#123456789_ab2cd3efg...
    // PK: USER#u1@gmail..., SK: ROOM#roomId
}
