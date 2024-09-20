provider "azurerm" {
  features {}
}

resource "azurerm_resource_group" "polyglot" {
  name     = "polyglot-resources"
  location = "West Europe"
}

resource "azurerm_storage_account" "polyglot" {
  name                     = "polyglotsa"
  resource_group_name      = azurerm_resource_group.polyglot.name
  location                 = azurerm_resource_group.polyglot.location
  account_tier             = "Standard"
  account_replication_type = "LRS"
}

resource "azurerm_mssql_server" "polyglot" {
  name                         = "polyglot-sqlserver"
  resource_group_name          = azurerm_resource_group.polyglot.name
  location                     = azurerm_resource_group.polyglot.location
  version                      = "12.0"
  administrator_login          = "4dm1n157r470r"
  administrator_login_password = "4-v3ry-53cr37-p455w0rd"
}

resource "azurerm_mssql_database" "polyglot" {
  name           = "polyglot-db"
  server_id      = azurerm_mssql_server.polyglot.id
  collation      = "SQL_Latin1_General_CP1_CI_AS"
  license_type   = "LicenseIncluded"
  max_size_gb    = 4
  read_scale     = true
  sku_name       = "S0"
  zone_redundant = true
  enclave_type   = "VBS"

  tags = {
    foo = "bar"
  }

  # prevent the possibility of accidental data loss
  lifecycle {
    prevent_destroy = true
  }
}
