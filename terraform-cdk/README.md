## Welcome to CDK for Terraform (CDKTF) project ##

It demonstrates a CDKTF app exclusively for AWS, Azure and GCP.

The `cdktf.json` file tells the CDKTF Toolkit how to execute your app.

It is a [Maven](https://maven.apache.org/) based project, so you can open this project with any Maven compatible Java IDE to
build and run tests.

## Useful commands

* `mvn compile`     compile
* `mvn package`     compile and run tests
* `cdktf ls`        list all stacks in the app
* `cdktf get`       Gets the required provider dependency as defined in cdktf.json
* `cdktf synth`     emits the synthesized templates
* `cdktf deploy`    deploy this stack to your default configured cloud provider's account/region
* `cdk docs`        open CDK documentation

Happy Learning!

## Known Issues

- [An empty .gen folder gets created for a Java build during the `cdktf get` command run](https://github.com/hashicorp/terraform-cdk/issues/519)
- Would like to move all the generated provider dependencies to be moved into the .gen folder

## Pending Tasks

- Add unit tests  
