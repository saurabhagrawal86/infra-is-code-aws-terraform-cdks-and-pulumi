package software.amazon.awscdk.examples;

import java.util.List;

import software.amazon.awscdk.Stack;
import software.amazon.awscdk.pipelines.CodePipeline;
import software.amazon.awscdk.pipelines.CodePipelineSource;
import software.amazon.awscdk.pipelines.ShellStep;
import software.amazon.awscdk.services.codecommit.Repository;
import software.constructs.Construct;

public class CDKPipelineStack extends Stack {

    public CDKPipelineStack(final Construct scope, final String id) {
        super(scope, id + "-pipeline-stack");

        final var repo = Repository.Builder.create(this, id + "-repo")
            .repositoryName(id + "-repo")
            .build();

        final var pipelineSource = CodePipelineSource.codeCommit(repo, "master");

        final var pipeline = CodePipeline.Builder.create(this, id + "-pipeline")
            .pipelineName(id + "-pipeline")
            .synth(
                ShellStep.Builder.create("SynthStep").input(pipelineSource)
                    .installCommands(List.of("npm install -g aws-cdk"))
                    .commands(List.of("mvn package", "npx cdk synth")).build()
            ).build();

        pipeline.addStage(new ApplicationStage(this, "pipeline-app-stage"));
    }

}
