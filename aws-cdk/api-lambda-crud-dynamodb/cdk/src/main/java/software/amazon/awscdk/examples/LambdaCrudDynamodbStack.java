package software.amazon.awscdk.examples;

import software.amazon.awscdk.Duration;
import software.amazon.awscdk.RemovalPolicy;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.apigateway.LambdaIntegration;
import software.amazon.awscdk.services.apigateway.LambdaRestApi;
import software.amazon.awscdk.services.dynamodb.Attribute;
import software.amazon.awscdk.services.dynamodb.AttributeType;
import software.amazon.awscdk.services.dynamodb.Table;
import software.amazon.awscdk.services.dynamodb.TableProps;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.HttpMethod;
import software.amazon.awscdk.services.lambda.Runtime;
import software.constructs.Construct;

public class LambdaCrudDynamodbStack extends Stack {

    public LambdaCrudDynamodbStack(final Construct parent, final String id) {
        super(parent, id);

        var partitionKey = Attribute.builder()
            .name("itemId")
            .type(AttributeType.STRING)
            .build();
        var tableProps = TableProps.builder()
            .tableName("items")
            .partitionKey(partitionKey)
            .removalPolicy(RemovalPolicy.DESTROY)
            .build();
        var dynamodbTable = new Table(this, "items", tableProps);

        var createItemFunction = Function.Builder.create(this, "createItemFunction")
            .code(Code.fromAsset("./lambda/target/lambda-1.0.0.jar"))
            .handler("software.amazon.awscdk.examples.lambda.CreateItem")
            .runtime(Runtime.JAVA_11)
            .timeout(Duration.seconds(30))
            .memorySize(512)
            .build();

        dynamodbTable.grantReadWriteData(createItemFunction);

        var api = LambdaRestApi.Builder.create(this, "itemsApi")
            .restApiName("Items Service").handler(createItemFunction)
            .build();

        api.getRoot().addResource("items").addMethod(HttpMethod.POST.name(), new LambdaIntegration(createItemFunction));
    }
}
