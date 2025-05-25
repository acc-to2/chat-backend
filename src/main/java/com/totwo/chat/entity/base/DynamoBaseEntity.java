package com.totwo.chat.entity.base;

import lombok.*;
import lombok.experimental.SuperBuilder;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
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
