package com.totwo.chat.repository.base;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

import java.util.List;
import java.util.Optional;

public abstract class DynamoBaseRepository<T> {
    protected final DynamoDbTable<T> table;

    public DynamoBaseRepository(DynamoDbEnhancedClient enhancedClient, Class<T> clazz) {
        this.table = enhancedClient.table("To2", TableSchema.fromBean(clazz));
    }

    public void save(T item) {
        table.putItem(item);
    }

    public Optional<T> load(String pk, String sk) {
        return Optional.ofNullable(table.getItem(r -> r.key(k -> {
            k.partitionValue(pk).sortValue(sk);
        })));
    }

    public List<T> loadSkBeginsWith(String pk, String skPrefix) {
        return table.query(r -> r
                .queryConditional(
                        QueryConditional.sortBeginsWith(
                                k -> k.partitionValue(pk).sortValue(skPrefix)
                        )
                )
        ).items().stream().toList();
    }

    public void delete(String pk, String sk) {
        table.deleteItem(r -> r.key(k -> {
            k.partitionValue(pk).sortValue(sk);
        }));
    }
}

