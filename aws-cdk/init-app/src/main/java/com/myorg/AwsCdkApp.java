package com.myorg;

import software.amazon.awscdk.App;

public final class AwsCdkApp {
    public static void main(final String[] args) {
        App app = new App();

        new AwsCdkStack(app, "AwsCdkStack");

        app.synth();
    }
}

