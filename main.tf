provider "azurerm" {
    # The "feature" block is required for AzureRM provider 2.x.
    # If you're using version 1.x, the "features" block is not allowed.
    version = "2.46"
    features {}
    skip_provider_registration = true
}


terraform {
    backend "azurerm" {
        resource_group_name = "csprg-Terraform"    
        storage_account_name = "terraformaccounteindtest"
        container_name = "terraformstoragecontainer.tfstate"
    }
}

data "azurerm_client_config" "current" {}



resource "azurerm_app_service" "snel" {
  name                = "SnelTransport"
  location            = "westeurope"
  resource_group_name = "csprsg-Traineeship"
  #Please have a look at the "serverfarms" part of the app_service_plan_id. The standard id that azure generates actually contains "serverFarms" but that breaks the terraform
  app_service_plan_id = "/subscriptions/ac58b4ec-0810-44c4-9167-58a33cd159cd/resourceGroups/csprsg-Traineeship/providers/Microsoft.Web/serverfarms/Traineeship-Linux-Appserviceplan"

  site_config {
    java_version           = "11"
    java_container         = "TOMCAT"
    java_container_version = "9.0"
	
  }
}  


#Create a  testslot to which applications can be deployed. It is also possible to swap builds between slots. i.e. deploying to the blue-deployment and when succesful swapping it to the production slot
resource "azurerm_app_service_slot" "appslot" {
  name                = "blue-deployment"
  app_service_name    = azurerm_app_service.fdd.name
  location            = azurerm_app_service.fdd.location
  resource_group_name = azurerm_app_service.fdd.resource_group_name
  app_service_plan_id = azurerm_app_service.fdd.app_service_plan_id

  site_config {
    java_version           = "11"
    java_container         = "TOMCAT"
    java_container_version = "9.0"
  }
}
