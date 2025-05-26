package com.totwo.chat.repository;

import com.totwo.chat.entity.Message;
import com.totwo.chat.repository.base.DynamoBaseRepository;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

import java.util.Optional;

@Repository
public class MessageRepository extends DynamoBaseRepository<Message> {

    public MessageRepository(DynamoDbEnhancedClient enhancedClient) {
        super(enhancedClient, Message.class);
    }

    public Optional<Message> getLastMessage(String pk) {

        QueryConditional queryConditional = QueryConditional.keyEqualTo(
            Key.builder().partitionValue(pk).build()
        );

        return table.query(r -> r.queryConditional(queryConditional)
                         .scanIndexForward(false) // 역순 정렬
                         .limit(1)) // 첫 번째 항목만 가져옴
                    .stream()
                    .flatMap(page -> page.items().stream())
                    .findFirst();
    }

    public int countMessagesAfter(String pk, String sk) {
        QueryConditional queryConditional = QueryConditional.sortGreaterThan(
            Key.builder().partitionValue(pk).sortValue(sk).build()
        );

        return (int) table.query(r -> r.queryConditional(queryConditional))
                          .stream()
                          .mapToLong(page -> page.items().size())
                          .sum();
    }
}
