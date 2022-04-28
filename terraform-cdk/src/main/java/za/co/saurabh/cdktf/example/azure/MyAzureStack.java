package za.co.saurabh.cdktf.example.azure;

import com.hashicorp.cdktf.TerraformStack;

import java.util.List;

import imports.azurerm.AzurermProvider;
import imports.azurerm.AzurermProviderFeatures;
import imports.azurerm.VirtualNetwork;
import software.constructs.Construct;

public class MyAzureStack extends TerraformStack {
    public MyAzureStack(final Construct scope, final String id) {
        super(scope, id);

        AzurermProvider.Builder.create(this, "AzureResourceManager")
                .features(AzurermProviderFeatures.builder().build())
                .build();

        VirtualNetwork.Builder.create(this, "TfVnet")
                .location("southafricanorth")
                .addressSpace(List.of("10.0.0.0/24"))
                .name("CdkTfVNet")
                .resourceGroupName("saurabh_cdktf_resource_group_name")
                .build();
    }
}
