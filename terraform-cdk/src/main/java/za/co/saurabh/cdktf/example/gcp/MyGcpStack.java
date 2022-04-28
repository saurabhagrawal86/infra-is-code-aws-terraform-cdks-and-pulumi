package za.co.saurabh.cdktf.example.gcp;

import com.hashicorp.cdktf.App;
import com.hashicorp.cdktf.TerraformStack;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import imports.google.ComputeInstance;
import imports.google.ComputeInstanceBootDisk;
import imports.google.ComputeInstanceBootDiskInitializeParams;
import imports.google.ComputeInstanceNetworkInterface;
import imports.google.ComputeNetwork;
import imports.google.GoogleProvider;
import software.constructs.Construct;

public class MyGcpStack extends TerraformStack {
    public MyGcpStack(final Construct scope, final String id) throws IOException {
        super(scope, id);

        Path credentialPath = Paths.get("").resolve("google.json");
        String credentials = credentialPath.toFile().exists() ? new String(Files.readAllBytes(credentialPath), StandardCharsets.UTF_8) : "{}";

        GoogleProvider.Builder.create(this, "Google")
                .region("us-central1")
                .zone("us-central1-c")
                .project("terraform-cdk")
                .credentials(credentials).build();

        ComputeNetwork network = ComputeNetwork.Builder.create(this, "Network").name("cdktf-network").build();

        ComputeInstance.Builder.create(this, "ComputeInstance")
                .name("cdktf-instance")
                .machineType("f1-micro")
                .bootDisk(ComputeInstanceBootDisk.builder()
                        .initializeParams(
                                ComputeInstanceBootDiskInitializeParams.builder()
                                        .image("debian-cloud/debian-9")
                                        .build())
                        .build())
                .networkInterface(
                        List.of(ComputeInstanceNetworkInterface.builder()
                                .network(network.getName()).build()))
                .tags(Arrays.asList("web", "dev"))
                .dependsOn(List.of(network)).build();
    }
}
