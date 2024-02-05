# Design Document

## PopStock Design

## 1. Problem Statement

A Warehouse manager needs to be able to track their incoming and outgoing inventory.  If they have multiple warehouse sites they need to be able to track the inventory of each site independently. They also need an ongoing record of all transactions that have taken place for tracking purposes.  PopStock allows the user to accomplish these requirements.

## 2. Use Cases

U1. As a PopStock customer, I want to create a new warehouse and name it.

U2. As a PopStock customer, I want to be able to rename an existing warehouse.

U3. As a PopStock customer, I want to be able to see all of my existing warehouses.

U4. As a PopStock customer, I want to be able to log in and keep my warehouse data private.

U5. As a PopStock customer, I want to select an existing warehouse and see information about its inventory.

U6. As a PopStock customer, I want to populate a warehouse with items.

U7. As A PopStock customer, I want to create transactions to track incoming and outgoing item shipments and update the inventory accordingly.

U8. As a PopStock customer, I want to generate a report showing warehouse transactions.

U9. As a PopStock customer, I want to be able to delete an existing warehouse, its inventory, and its associated transactions.


## 3. Project Scope

### 3.1. In Scope

* Creating, deleting, and renaming Warehouses
* Creating and tracking inventory items for each warehouses
* Creating transactions representing incoming and outgoing item shipments, and adjusting on-hand inventory item numbers accordingly
* Generating HTML inventory shipping reports with custom time periods

### 3.2. Out of Scope

* Individual item reports

### 3.3 Stretch Goals
* Item recommendation service, which can autopopulate a warehouse based on data metrics
* Customer information table, with endpoints and front-end pages allowing the creation of customers and pulling of customer shipping reports.
* Supplier information table, with endpoints and front-end pages allowing the creation of suppliers and pulling of supplier stocking reports

# 4. Proposed Architecture Overview

The initial product will provide the minimum lovable product (MLP) including creating, updating, and deleting warehouses. 
It will also allow adding and removing items to warehouse inventories, tracking their quantities on hand, tracking incoming and outgoing shipments, and generating time-based shipping reports.

This will be accomplished using a RESTful API Gateway and AWS Lambda to  create endpoints.  The product stack will be deployed into an S3 bucket using CloudFormation.

inventoryItem, transaction, and warehouse data will be stored in DynamoDB.

PopStock will also provide a web interface for users to manage their warehouses, and inventories.  There will be a main page showing existing warehouses, with buttons to create a new one or to modify and existing one.
Clicking the button for an existing warehouse will lead to a page where you can update a warehouse's inventory, with buttons leading to pages for Inventory Item creation and shipping reports.

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
LocalDate transactionDate
String partnerId
String transactionType
```

```
//ItemModel
String warehouseId
String itemId
int count
String name
```

## 5.2. Create Item Endpoint
* Accepts POST requests to /warehouses/:warehouseId/inventory
* Accepts input data and user info to create a new item and returns an itemModel for the logged-in user
![](CreateItemSequenceDiagram.png)

## 5.3 Get Items Endpoint
* Accepts Get requests to /warehouses/:warehouseId/inventory
* Accepts a warehouseId and user info and returns a list of items associated with the warehouse
  
## 5.5 Create Warehouse Endpoint
* Accepts POST requests to /warehouses
* Accepts name data and user info to create a new warehouse
* Returns the created warehouseModel

## 5.7 Get All Warehouses Endpoint
* Accepts GET requests to /warehouses
* Accepts user information and returns a list all warehouses associated with the account

## 5.8 Delete Warehouse Endpoint
* Accepts DELETE requests to /warehouses/:warehouseId
* Accepts a warehouseId and user info, and deletes the matching warehouse, items, and transactions

## 5.9 Update Warehouse Endpoint
* Accepts PUT requests to /warehouses/:warehouseId
* Accepts a warehouseId, allows the user to change the warehouse name, and returns a warehouseModel

## 5.10 Create Transaction Endpoint
* Accepts a post request to /warehouses/:warehouseId/transactions
* Creates a transaction entry for the item and adjusts the item's inventory quantity
* Returns the create transaction

## 6.11 Get Shipping Report
* Accepts GET requests to /warehouses/:warehouseId/:startDate/:endDate
* Accepts a warehouseID
* Returns a json formatted report of transactions
  
# 6. Tables

## 6.1. warehouses

```
user_id // partition key, string
warehouse_id // sort key, string
name // string
```

## 6.2 items
```
warehouse_id //partition key, string
item_id // sort key, string
count // number
name // string
```

## 6.3 transactions
```
warehouse_id // sort key, string
transaction_id // partition key, string
shipment_id // string
item_id // string
count // number
date // string
partner_id // string
transaction_type // string

# 7. Pages

![](images/PopStock_Web_Layout.jpg)
