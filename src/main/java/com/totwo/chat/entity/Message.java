package com.totwo.chat.entity;

import com.totwo.chat.entity.base.DynamoBaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDbBean
public class Message extends DynamoBaseEntity {
    private String content;
    private String senderEmail;
    private String timestamp;
    // PK: ROOM#roomId, SK: MSG#timestamp_uuid
}
