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
public class Message extends DynamoBaseEntity {
    private String content;
    private String senderId;
    private Long timestamp;
    // PK: ROOM#roomId, SK: MSG#timestamp_uuid
}
