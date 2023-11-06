# Design Document

## PopStock Design

## 1. Problem Statement

A new warehouse can be difficult to spin up, requiring you to make difficult decisions about what items you are going to stock. PopStock will solve this problem by using data to choose the most profitable items to stock, based on metrics such as margin, time-to-replenish, sales volume, item synergy, and region adjusted shipping costs.  Once a new warehouse has been spun up, users will be able to manage the inventory and generate reports.


## 2. Top Questions to Resolve in Review

1. Where would caching be most useful


## 3. Use Cases

U1. As a PopStock customer, I want to create new inventory items with attributes and metrics that can be used to stock warehouses

U2. As a PopStock customer, I want to create a new warehouse with a name and a regional location

U3. As a PopStock customer, I want to populate a new warehouse with items selected automatically based on profitablility

U4. As a PopStock customer, I want to generate a report showing inventory statistics and suggesting more profitable items available in the inventory items list

U5. As a PopStock customer, I want to update the amount of available stock for each item.

U6. As a PopStock customer, I want to remove items from my warehouse if no stock is currently available

U7. As a PopStock customer, I want to add items to my warehouse when space is available

U8. As a PopStock customer, I want to see reports on item shipments over a given time-period

U9. As a PopStock customer, I want to select an existing warehouse and see information about its inventory

## 4. Project Scope

### 4.1. In Scope

* Creating inventory items, updating their metrics, and removing them when not in any warehouse
* Creating, populating, and arranging new warehouses
* Updating inventory, generating reports, adding and removing items from existing warehouses, and rearranging existing warehouses

### 4.2. Out of Scope

* Automatic updating of inventory item metrics based on stock changes
* Individual item reports
* Configurable warehouse spaces and layouts (the initial product will use the same basic floor plan for all warehouses)

### 4.3 Stretch Goals
* Customer information table, with endpoints and front-end pages allowing the creation of customers and pulling of customer shipping reports.
* Automatic warehouse layout creation and display
* Supplier information table, with endpoints and front-end pages allowing the creation of suppliers and pulling of supplier stocking reports

# 5. Proposed Architecture Overview

The initial product will provide the minimum lovable product (MLP) including creating, updating, and deleting inventory items, updating their metrics, spinning up new warehouses, selecting their products and arranging the items on the available shelf-space.

This will be accomplished using a RESTful API and AWS Lambda to create endpoints.

Inventory item and warehouse data will be stored in DynamoDB

PopStock will also provide a web interface for users to manage their warehouses and inventory.  There will be a main page showing existing warehouses, with buttons to create a new one or modify existing inventory items. The inventory item page will have buttons linking to pages to create, update, or delete existing inventory items. Clicking the button for an existing warehouse will lead to a page where you can update a warehouse's inventory, run reports, or generate a new layout.  
# 6. API

## 6.1. Public Models
```
//WarehouseModel
String userId 
String wareHouseId
String name
int region
Map<ItemModel, Integer> inventory
int size
```

```
//ItemModel
String itemId
String name
String category
String regionOfOrigin
int regionalDemand
double salesForecast
double perPallet
double weight
double purchaseCost
double baseMargin
double rateOfReplenishment
String synergy
boolean active
```

```
//TransactionModel
transactionId
String warehouseId
String shipmentId
String itemId
int count
Date date
String partnerId
String transactionType

```

## 6.2. Create Item Endpoint
* Accepts POST requests to /items
* Accepts input data and user info to create a new item and returns an itemModel for the logged-in user

## 6.3 Get Item Endpoint
* Accepts GET requests to /items/:itemId
* Accepts an itemId and user info and returns the relevant itemModel

## 6.4 Get All Items Endpoint
* Accepts GET requests to /items
* Accepts user info and return a list of all Items

## 6.5 Update Item Endpoint
* Accepts PUT requests to /items/:itemId
* Accepts an itemId, input data, and user info, updates the item data, and returns an itemModel

## 6.6 Create Warehouse Endpoint
* Accepts POST requests to /warehouses
* Accepts input data and user info to create a new warehouse
* Returns the created warehouseModel

## 6.7 Get Warehouse Endpoint
* Accepts GET requests to /warehouses/:warehouseId
* Accepts a warehouseId and user info, and returns the relevant warehouseModel

## 6.8 Get All Warehouses Endpoint
* Accepts GET requests to /warehouses
* Accepts user information and returns all warehouses associated with the account

## 6.9 Delete Warehouse Endpoint
* Accepts DELETE requests to /warehouses/warehouseId
* Accepts a warehouseId and user info, and deletes the matching warehouse, inventory, and transaction data

## 6.10 Update Warehouse Endpoint
* Accepts PUT requests to /warehouses/:warehouseId
* Accepts a warehouseId, allows the user to change the warehouse name, and returns the warehouseModel
* 
## 6.11 Update Inventory Endpoint
* Accepts POST requests to /warehouses/:warehouseId/inventory
* Accepts a warehouseId, itemIds in a map of stock change amounts for each item, a partnerID, an optional date field, and user info
* Generates a transactionModel object and adds it to the transactions table for reporting
* Updates inventory quantities and returns warehouseId and updated map of inventory items

## 6.12 Fill Inventory Endpoint
* Accepts PUT requests to /warehouses/fill/:warehouseId
* Accepts a warehouseId
* Retrieves a list of all items and calculates the most profitable selection for the given warehouse region
* returns an updated warehouseModel

## 6.13 Get WareHouse Inventory Report Endpoint
* Accepts GET requests to warehouses/:warehouseId/inventory
* Accepts a warehouseId and returns a warehouseModel
* Calculates current inventory status for current warehouse inventory items, and pulls item data
* Calculates item data and recommends new items to be added to the warehouse, and items to be removed
* Returns a JSON formatted report to be formatted in the front end

## 6.14 Get WareHouse Shipping Report Endpoint
 * Accepts GET requests to transactions/warehouseId/:date-range
 * Accepts a warehouseID and date range.  Gets all transactions from the transactions table within the date range.
 * Generates a JSON formatted report of shipping activity within the date-range to be formatted by the front end

## 6.15 Get WareHouse Item Shipping Report Endpoint
* Accepts GET requests to transactions/:warehouseID/:itemId
* Accepts a warehouseId and gets all transactions from the 
* Generates a json formatted report of shipping activity for a particular item



# 7. Tables

## 7.1. warehouses

```
user_id // partition key, string
warehouse_id // sort key, string
name // string
region // number

```

## 7.2 items
```
item_id //  partition key, string
category // sort key, string
name // string
region_of_origin // string
regional_demand // number
sales_forecast // number
per_pallet // number
weight // number
purchase_cost // number
base_margin // number
rate_of_replenishment // number
active // boolean
```

## 7.3 inventory_items
```
item_id // partition key, string
warehouse_id // sort key, string
name // string
category // string
profitability // number
quantity // string
```

## 7.4 transactions
```
transaction_id // partition key, string
warehouse_id // sort key, string
shipment_id // string
item_id // string
count // number
date // string
partner_id // string
transaction_type // string
```


# 8. Pages

![](images/PopStock_Web_Layout.jpg)
