import PopStockClient from '../api/popStockClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

class GetInventory extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'loadInventory', 'createSubmit', 'reportSubmit', 'clientLoaded', 'addHTMLRowsToTable'], this);
   
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    mount() {
        this.header.addHeaderToPage();
        this.client = new PopStockClient();
        this.clientLoaded();
        this.loadInventory();
        document.getElementById('create-item-button').addEventListener('click', this.createSubmit);
        document.getElementById('transaction-report-button').addEventListener('click', this.reportSubmit);
    }
    
    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const warehouse = urlParams.get('warehouse');
        const name = urlParams.get('name');
        document.getElementById('warehouse-name').innerText = "\x22" + name + "\x22 Inventory";
        this.dataStore.set('warehouse', warehouse);
        this.dataStore.set('warehouseName', name);
    }

    async createSubmit(evt) {
        evt.preventDefault();

        const warehouse = this.dataStore.get('warehouse');
        const warehouseName = this.dataStore.get('warehouseName');
        
        window.location.href = `/createItem.html?warehouse=${warehouse}&name=${warehouseName}`; 
    }

    async reportSubmit(evt) {
        evt.preventDefault();

        const warehouse = this.dataStore.get('warehouse');
        const warehouseName = this.dataStore.get('warehouseName');
        
        window.location.href = `/transactions.html?warehouse=${warehouse}&name=${warehouseName}`;
    }

    async loadInventory() {
        console.log("Inside loadInventory");
        const inventory = await this.client.getInventory(this.dataStore.get('warehouse'));
 
        this.dataStore.set('inventory', inventory);
        const inventoryTableHTML = document.getElementById('inventory-table');
        this.addHTMLRowsToTable(inventoryTableHTML);
    }

    async addHTMLRowsToTable(inventoryTableHTML) {

        const warehouseId = this.dataStore.get('warehouse');
        const warehouseName = this.dataStore.get('warehouseName');
        const inventory = this.dataStore.get('inventory');
       
        
        if (inventory.length == 0 || inventory == null) {
             const noInventoryCell = document.createElement('td');
             noInventoryCell.textContent = "There are currently no items in the inventory.  Please create one.";
             const row = document.createElement('tr');
             row.appendChild(noInventoryCell);
             inventoryTableHTML.appendChild(row);
         } else {

            for (const item of inventory) {
                const row = document.createElement('tr');
                
                const itemCountCell = document.createElement('td');
                itemCountCell.textContent = item.count;
                row.appendChild(itemCountCell)
    
                const itemNameCell = document.createElement('td');
                itemNameCell.textContent = item.name;
                row.appendChild(itemNameCell);
    
                const updateButtonCell = document.createElement('td');
                const updateButton = document.createElement('button');
                updateButton.textContent = 'Update';
                updateButton.className = 'button';
                updateButton.addEventListener('click', () => {
                    window.location.href = '/createTransaction.html?warehouse=' + warehouseId + "&warehouseName=" + warehouseName + "&item=" + item.itemId + "&itemName=" + item.name;
                });
                updateButtonCell.appendChild(updateButton);
                row.appendChild(updateButtonCell);
    
                inventoryTableHTML.appendChild(row);
            }

         }
 
    }

}

const main = async () => {
    const getInventory = new GetInventory();
    getInventory.mount();
};


window.addEventListener('DOMContentLoaded', main);
