package com.totwo.chat.entity;

import com.totwo.chat.entity.base.DynamoBaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDbBean
public class UserRoomParticipation extends DynamoBaseEntity {
    // PK: USER#u1@gmail..., SK: ROOM#roomId
}
