# Design Document

## PopStock Design

## 1. Problem Statement

A new warehouse can be difficult to spin up, requiring you to make difficult decisions about what items you are going to stock. PopStock will solve this problem by using data to choose the most profitable items to stock, based on metrics such as margin, time-to-replenish, sales volume, item synergy, and region adjusted shipping costs.  PopStock allows business owners to make logistics focused, data driven decisions about what products would be most lucrative.

## 2. Use Cases

U1. As a PopStock customer, I want to create new items with attributes and metrics that can be used to stock warehouses

U2. As a PopStock customer, I want to create a new warehouse with a name and a regional location

U3. As a PopStock customer, I want to populate a new warehouse with items selected automatically based on profitability

U4. As a PopStock customer, I want to select an existing warehouse and see information about its inventory

U5. As a PopStock customer, I want to generate a report showing warehouse inventory information and suggesting more profitable items available in the items list

U6. As a PopStock customer, I want to remove items from my warehouse

U7. As a PopStock customer, I want to add items to my warehouse when space is available

U8. As a PopStock customer, I want to be able to rename an existing warehouse

U9. As a PopStock customer, I want to be able to update item information and metrics

U10. As a PopStock customer, I want to be able to delete an existing warehouse, and its inventory


## 3. Project Scope

### 3.1. In Scope

* Creating and removing inventory items for each warehouses
* Creating, deleting, and renaming Warehouses
* Creating transactions representing incoming and outgoing item shipments, and adjusting on-hand inventory item numbers accordingly
* Generating HTML inventory shipping reports with custom time periods
* Using third-party API to generate and download PDF document of inventory report

### 3.2. Out of Scope

* Individual item reports
* Configurable warehouse sizes and layouts (the initial product will use the same basic warehouse size/floor plan for all warehouses)

### 3.3 Stretch Goals
* Automatic warehouse layout creation and display, using machine learning to generate efficient layouts
* Item recommendation service, which can autopopulate a warehouse based on data metrics
* Customer information table, with endpoints and front-end pages allowing the creation of customers and pulling of customer shipping reports.
* Supplier information table, with endpoints and front-end pages allowing the creation of suppliers and pulling of supplier stocking reports

# 4. Proposed Architecture Overview

The initial product will provide the minimum lovable product (MLP) including creating, updating, and deleting warehouses. 
It will also allow adding and removing items to warehouse inventories, tracking their quantities on hand, tracking incoming and outgoing shipments, and generating time-based shipping reports.

This will be accomplished using a RESTful API Gateway and AWS Lambda to  create endpoints.  The product stack will be deployed into an S3 bucket using CloudFormation

inventoryItem, transaction, and warehouse data will be stored in DynamoDB

PopStock will also provide a web interface for users to manage their warehouses, and inventories.  There will be a main page showing existing warehouses, with buttons to create a new one or to modify and existing one.
Clicking the button for an existing warehouse will lead to a page where you can update a warehouse's inventory, with buttons leading to pages for Inventory Item creation and shipping reports

# 5. API

## 5.1. Public Models
```
//WarehouseModel
String userId 
String wareHouseId
String name
```

```
//TransactionModel
String warehouseId
transactionId
String shipmentId
String itemId
int count
Date date
String partnerId
String transactionType
```

```
//InventoryItemModel
String warehouseId
String itemId
String name
String category
int count
```

## 5.2. Create Item Endpoint
* Accepts POST requests to /warehouses/:warehouseId/inventory
* Accepts input data and user info to create a new item and returns an itemModel for the logged-in user
![](CreateItemSequenceDiagram.png)

## 5.3 Get Items Endpoint
* Accepts Get requests to /warehouses/:warehouseId/inventory
* Accepts a warehouseId and user info and returns a lit of all inventoryItems for the warehouse
  *if the warehouseId is not found, will throw 'WarehouseNotFoundException'

## 5.4 Delete Items Endpoint
* Accepts Delete requests to /warehouses/:warehouseId/:itemId
* Accepts a warehouseId, a list of inventoryItemIds, and user info, and deletes the inventory items from the inventory_items table
* Returns a json the warehouse ID, name, and list containing the current warehouse inventory of itemModels
  * if the warehouseId is not found, will throw 'WarehouseNotFoundException'
  * if the inventoryItemId is not found, will throw 'inventoryItemNotFoundException'
  
## 5.5 Create Warehouse Endpoint
* Accepts POST requests to /warehouses
* Accepts input data and user info to create a new warehouse
* Returns the created warehouseModel

## 5.6 Get Warehouse Endpoint
* Accepts GET requests to /warehouses/:warehouseId
* Accepts a warehouseId and user info, and returns the relevant warehouseModel
  * if the warehouseId is not found, will throw 'WarehouseNotFoundException'

## 5.7 Get All Warehouses Endpoint
* Accepts GET requests to /warehouses
* Accepts user information and returns a list all warehouses associated with the account

## 5.8 Delete Warehouse Endpoint
* Accepts DELETE requests to /warehouses/:warehouseId
* Accepts a warehouseId and user info, and deletes the matching warehouse, inventory, and transactions
  *  if the warehouseId is not found, will throw 'WarehouseNotFoundException'

## 5.9 Update Warehouse Endpoint
* Accepts PUT requests to /warehouses/:warehouseId
* Accepts a warehouseId, allows the user to change the warehouse name, and returns a warehouseModel
  * if the warehouseId is not found, will throw 'WarehouseNotFoundException'`

## 5.10 Create Transaction Endpoint
* Accepts a post request to /warehouses/:warehouseId/transactions
* Accepts a map of InventoryItems to integers
* Creates a transaction table entry for each item and adjusts the InventoryItem quantity
* Returns an updated list of InventoryItems
  * if the warehouseId is not found, will throw 'WarehouseNotFoundException'
  * if the inventoryItemId is not found, will throw 'inventoryItemNotFoundException'

## 6.11 Get Shipping Report
* Accepts GET requests to /warehouses/:warehouseId/transactions?date=
* Accepts a warehouseID
* Returns a json formatted report of transactions
  * if the warehouseId is not found, will throw 'WarehouseNotFoundException'

## 6.12 Get Shipping Report PDF
* Accepts GET requests to /warehouses/:warehouseId/pdf/transactions?date=/
* Accepts a warehouseID
* Creates a request to a third-party API service and generates a pdf to be downloaded by the user's browser
  * if the warehouseId is not found, will throw 'WarehouseNotFoundException'
  
# 6. Tables

## 6.1. warehouses

```
user_id // partition key, string
warehouse_id // sort key, string
name // string
region // String
```

## 6.2 inventory_items
```
item_id // partition key, string
warehouse_id // sort key, string
name // string
category // string
```

## 6.3 transactions
```
transaction_id // partition key, string
warehouse_id // sort key, string
shipment_id // string
item_id // string
count // number
date // string
partner_id // string
transaction_type // string

# 7. Pages

![](images/PopStock_Web_Layout.jpg)
