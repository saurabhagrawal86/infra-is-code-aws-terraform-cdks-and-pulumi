package za.co.saurabh.cdktf.example.aws;

import com.hashicorp.cdktf.App;
import com.hashicorp.cdktf.TerraformOutput;
import com.hashicorp.cdktf.TerraformStack;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import imports.aws.AwsProvider;
import imports.aws.datasources.DataAwsRegion;
import imports.aws.dynamodb.DynamodbTable;
import imports.aws.dynamodb.DynamodbTableAttribute;
import imports.aws.sns.SnsTopic;
import software.constructs.Construct;

public class MyAwsStack extends TerraformStack {
    public MyAwsStack(final Construct scope, final String id) {
        super(scope, id);

        AwsProvider.Builder.create(this, "aws").region("eu-central-1").build();

        DataAwsRegion region = new DataAwsRegion(this, "region");

        DynamodbTable table = DynamodbTable.Builder
                .create(this, "Hello")
                .name("my-first-table-" + region.getName())
                .hashKey("temp").attribute(List.of(DynamodbTableAttribute.builder().name("id").type("S").build()))
                .billingMode("PAY_PER_REQUEST")
                .build();

        table.addOverride("hash_key", "id");
        table.addOverride("lifecycle", Collections.singletonMap("create_before_destroy", true));

        TerraformOutput.Builder.create(this, "table_name").value(table.getName()).build();

        final int topicCount = 1;
        List<SnsTopic> topics = IntStream.range(0, topicCount)
                .mapToObj(i -> SnsTopic.Builder.create(this, "Topic" + i).displayName("my-first-sns-topic" + i).build())
                .toList();

        for (int i = 0; i < topics.size(); i++) {
            TerraformOutput.Builder.create(this, "sns_topic" + i).value(topics.get(i).getName()).build();
        }
    }
}
