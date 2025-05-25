package com.totwo.chat.entity;

import com.totwo.chat.entity.base.DynamoBaseEntity;
import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDbBean
public class ChatRoom extends DynamoBaseEntity {
    private String roomName;
    private Boolean isGroup;
    // PK: ROOM#roomId, SK: METADATA
}
