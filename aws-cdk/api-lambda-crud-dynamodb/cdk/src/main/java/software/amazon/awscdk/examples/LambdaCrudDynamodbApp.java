package software.amazon.awscdk.examples;

import software.amazon.awscdk.App;

public class LambdaCrudDynamodbApp {
    public static void main(final String[] args) {
        App app = new App();

        new CDKPipelineStack(app, "cdk-lambda-crud-dynamodb");

        app.synth();
    }
}
