package za.co.saurabh.cdktf.example;

import com.hashicorp.cdktf.App;

import java.io.IOException;

import za.co.saurabh.cdktf.example.aws.MyAwsStack;
import za.co.saurabh.cdktf.example.azure.MyAzureStack;
import za.co.saurabh.cdktf.example.gcp.MyGcpStack;

public class MyCdkTerrformApp {

    public static void main(String[] args) throws IOException {

        App app = new App();

        new MyAwsStack(app, "cdktf-aws");
        new MyAzureStack(app, "cdktf-azure");
        new MyGcpStack(app, "cdktf-google");

        app.synth();
    }
}
