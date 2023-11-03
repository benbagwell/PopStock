# Design Document

## PopStock Design

## 1. Problem Statement

A new warehouse can be difficult to spin up, requiring you to make difficult decisions about what items you are going to stock. PopStock will solve this problem by using data to choose the most profitable items to stock, based on metrics such as margin, time-to-replenish, sales volume, item synergy, and region adjusted shipping costs.  Once a new warehouse has been spun up, users will be able to manage the inventory and generate reports.


## 2. Top Questions to Resolve in Review

1. Where would caching be most useful

## 3. Use Cases

_This is where we work backwards from the customer and define what our customers would like to do (and why). You may also include use cases for yourselves (as developers), or for the organization providing the product to customers._

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

# 5. Proposed Architecture Overview

The initial product will provide the minimum lovable product (MLP) including creating, updating, and deleting inventory items, updating their metrics, spinning up new warehouses, selecting their products and arranging the items on the available shelf-space.

This will be accomplished using a RESTful API and AWS Lambda to create endpoints.

Inventory item and warehouse data will be stored in DynamoDB

PopStock will also provide a web interface for users to manage their warehouses and inventory.  There will be a main page showing existing warehouses, with buttons to create a new one or modify existing inventory items. The inventory item page will have buttons linking to pages to create, update, or delete existing inventory items. Clicking the button for an existing warehouse will lead to a page where you can update a warehouse's inventory, run reports, or generate a new layout.  
# 6. API

## 6.1. Public Models
```
//WarehouseModel 
String id;
String name;
int region;
Map<ItemModel, Integer> inventory
int size
```

```
//ItemModel
String id;
String name;
int regionalDemand;
double salesForecast
double perPallet;
double weight;
double purchaseCost;
double baseMargin;
double rateOfReplenishment;
String category
String synergy
boolean active
```

```
//shippedModel
String itemId // partition key, string
String warehouseId, sort key, string
Date date
int count
```

## 6.2. Create Item Endpoint

* Accepts POST requests to /items
* Accepts input data and user info to create a new item and returns an itemModel for the logged-in user
* 
## 6.3 Get Item Endpoint
* Accepts GET requests to /items/id
* Accepts an itemId and user info and returns the relevant itemModel

## 6.4 Remove Item Endpoint
* Accepts PUT requests to /items/id/remove
* Accepts an itemID, sets active to false for the given item, and returns the updated itemModel for the 

## 6.5 Update Item Endpoint
* Accepts PUT requests to /items/id
* Accepts an itemId, input data, and user info, updates the item data, and returns an itemModel

## 6.6 Create Warehouse Endpoint
* Accepts POST requests to /warehouses
* Accepts input data and user info to create a new warehouse
* Retrieves a list of all items and calculates the most profitable selection for the given warehouse metrics
* Returns the created warehouseModel

## 6.7 Get Warehouse Endpoint
* Accepts GET requests to /warehouses/id
* Accepts a warehouseId and user info, and returns the relevant warehouseModel

## 6.8 Delete Warehouse Endpoint
* Accepts DELETE requests to /warehouses/id
* Accepts a warehouseId and user info, and deletes the matching warehouse, and shipping data

## 6.9 Update Warehouse Inventory Endpoint
* Accepts PUT requests to /warehouses/id
* Accepts a warehouseId, itemId and user info
* Updates warehouse inventory count and returns warehouseModel

## 6.10 Get WareHouse Inventory Report
* Accepts GET requests to warehouses/id/inventory
* Accepts a warehouseId and returns a warehouseModel
* Calculates current inventory status for current warehouse inventory items, and pulls item data
* Calculates item data and recommends new items to be added to the warehouse, and items to be removed
* Returns a JSON formatted report to be formatted in the front end

## 6.11 Get WareHouse Shipping Report
 * Accepts GET requests to shipped/warehouseId/{date-range}
 * Accepts a warehouseID and returns a warehouseModel
 * Generates a JSON formatted report of shipping activity within the date-range to be formatted by the front end

## 6.12 Get WareHouse Item Shipping Report
* Accepts GET requests to shipped/warehouseID/itemId
* Accepts a warehouseId and itemID and returns a list of shippedModel objects



# 7. Tables

## 7.1. warehouses

```
userId // partition key, string
warehouseId // sort key, string
name // string
region // number
inventory // map
size // number
active // boolean
```

## 7.2 items
```
userId // partition key, string
itemId //  key, string
name // string
regionalDemand // number
size // number
weight // number
purchaseCost // number
baseMargin // number
rateOfReplenishment // number
category // string
synergy // string

```

## 7.3 shipped
```
itemId // partition key, string
warehouseId, sort key, string
date // string
count // number

```


# 8. Pages

![](images/PopStock_Web_Layout.jpg)
