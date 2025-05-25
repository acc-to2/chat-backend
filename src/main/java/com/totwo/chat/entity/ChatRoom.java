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
public class ChatRoom extends DynamoBaseEntity {
    private String roomName;
    private Boolean isGroup;
    // PK: ROOM#roomId, SK: METADATA
}
