package software.amazon.awscdk.examples.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.Map;
import java.util.UUID;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

public class CreateItem implements RequestHandler<Map<String, Object>, GatewayResponse> {

    @Override
    public GatewayResponse handleRequest(Map<String, Object> input, Context context) {
        var body = (String) input.get("body");
        var output = createItem(body);
        var headers = Map.of("Content-Type", "application/json");
        return new GatewayResponse(output, headers, 200);
    }

    private String createItem(String body) {
        var dbClient = DynamoDbClient.create();

        var item = Map.of(
            "itemId", AttributeValue.builder().s(UUID.randomUUID().toString()).build(),
            "hello", AttributeValue.builder().s("world").build()
        );

        var putItemRequest = PutItemRequest.builder()
            .tableName("items")
            .item(item)
            .build();

        dbClient.putItem(putItemRequest);

        return "Successfully Put Items in DB";
    }
}
