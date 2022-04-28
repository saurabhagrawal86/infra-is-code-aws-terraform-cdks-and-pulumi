package software.amazon.awscdk.examples;

import software.amazon.awscdk.Stage;
import software.constructs.Construct;

public class ApplicationStage extends Stage {

    public ApplicationStage(final Construct scope, final String id) {
        super(scope, id);

        new LambdaCrudDynamodbStack(this, "app-stack");
    }
}
