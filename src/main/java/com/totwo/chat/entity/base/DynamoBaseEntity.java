package com.totwo.chat.entity.base;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamoDbBean
public abstract class DynamoBaseEntity {
    protected String pk;
    protected String sk;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("PK")
    public String getPk() {
        return pk;
    }

    @DynamoDbSortKey
    @DynamoDbAttribute("SK")
    public String getSk() {
        return sk;
    }
}
