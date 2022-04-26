## What is Pulumi?
Pulumi is an Infractructure as Code tool written in Go. To integrate to Providers such as AWS, Azure or GCP you can implement it using the following programming languages:

- Typescript
- Python
- Javascript
- Go
- C#

## Install Pulumi and Configure AWS Credentials
1) Install Pulumi CLI: 
   - Linux: $ curl -fsSL https://get.pulumi.com | sh
   - macOS: $ brew install pulumi
   - Windows: > choco install pulumi

2) Configure Pulumi to access your AWS Account:
   - $ aws configure<br />
   OR
   - export AWS_ACCESS_KEY_ID=<YOUR_ACCESS_KEY_ID> 
   - export AWS_SECRET_ACCESS_KEY=<YOUR_SECRET_ACCESS_KEY>

3) Create your Pulumi Project 
   - $ pulumi new aws-typescript 
   - command syntax: (provider-language)

4) Deploy your Pulumi project
   $ pulumi up (spawns up the infastructure)
    - login to pulumi
    - enter project name
    - enter project description
    - enter stack name
    - enter region name: e.g af-south-1

5) Destroy Infrastructure:
   $ pulumi destroy

6) Documentation: 
   - https://www.pulumi.com/docs/
