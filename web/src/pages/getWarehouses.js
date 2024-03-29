import PopStockClient from '../api/popStockClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";
import Authenticator from "../api/authenticator";

const WAREHOUSE_LIST = 'warehouseList';
const EMPTY_DATASTORE_STATE = {
    [WAREHOUSE_LIST]: [],
};

class GetWarehouses extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'loadWarehouses','addHTMLRowsToTable'], this);
        this.authenticator = new Authenticator();
        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
        this.header = new Header(this.dataStore);
    }

    mount() {
        this.header.addHeaderToPage();
        this.client = new PopStockClient();
        this.loadWarehouses();
    }


    async loadWarehouses() {
        console.log("Inside loadWarehouses");
        const warehouses = await this.client.getWarehouses();
         this.dataStore.setState({
                 [WAREHOUSE_LIST]: warehouses,
             });

        const warehousesTableHTML = document.getElementById('warehouses-table');
        const isLoggedIn = await this.authenticator.isUserLoggedIn();

        if(isLoggedIn) {
            this.addHTMLRowsToTable(warehousesTableHTML);
        } else {
            const createButton = document.getElementById('create');
            createButton.style.display = "none";
            const noWarehousesCell = document.createElement('td');
            noWarehousesCell.textContent = "Please log in to see your warehouses.";
            const row = document.createElement('tr');
            row.appendChild(noWarehousesCell);
            warehousesTableHTML.appendChild(row);
        }
    }

    async addHTMLRowsToTable(warehousesTableHTML) {
        const warehouseList = this.dataStore.get(WAREHOUSE_LIST);
        var warehouses = warehouseList.warehouses;
       
        
       if (warehouses.length == 0 || warehouses == null) {
            const noWarehousesCell = document.createElement('td');
            noWarehousesCell.textContent = "There are currently no warehouses to display.  Please create one.";
            const row = document.createElement('tr');
            row.appendChild(noWarehousesCell);
            warehousesTableHTML.appendChild(row);
        }
        for (const warehouse of warehouses) {
            const row = document.createElement('tr');

            const warehouseNameCell = document.createElement('td');
            warehouseNameCell.textContent = warehouse.name;
            row.appendChild(warehouseNameCell);

            const selectButtonCell = document.createElement('td');
            const selectButton = document.createElement('button');
            selectButton.textContent = 'Select';
            selectButton.className = 'button';
            selectButton.addEventListener('click', () => {
                window.location.href = '/inventory.html?warehouse=' + warehouse.warehouseId + "&name=" + warehouse.name;
            });
            selectButtonCell.appendChild(selectButton);
            row.appendChild(selectButtonCell);

            const updateButtonCell = document.createElement('td');
            const updateButton = document.createElement('button');
            updateButton.textContent = 'Update';
            updateButton.className = 'button';
            updateButton.addEventListener('click', () => {
                window.location.href = '/updateWarehouse.html?warehouse=' + warehouse.warehouseId + "&name=" + warehouse.name;
            });
            updateButtonCell.appendChild(updateButton);
            row.appendChild(updateButtonCell);

            const deleteButtonCell = document.createElement('td');
            const deleteButton = document.createElement('button');
            deleteButton.textContent = 'Delete';
            deleteButton.className = 'button';
            deleteButton.addEventListener('click', async () => {
                let deleteYN = confirm("Are you sure? This will also delete the warehouse's inventory and transactions.");
                if (deleteYN === true) {
                    await this.client.deleteWarehouse(warehouse.warehouseId);
                    window.location.href = '/index.html';
                }
            });
            deleteButtonCell.appendChild(deleteButton);
            row.appendChild(deleteButtonCell);

            warehousesTableHTML.appendChild(row);
        }
    }

}

const main = async () => {
    const getWarehouses = new GetWarehouses();
    getWarehouses.mount();
};


window.addEventListener('DOMContentLoaded', main);
