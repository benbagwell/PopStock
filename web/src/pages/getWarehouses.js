import PopStockClient from '../api/popStockClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

const WAREHOUSE_LIST = 'warehouseList';
const EMPTY_DATASTORE_STATE = {
    [WAREHOUSE_LIST]: [],
};

class GetWarehouses extends BindingClass {
    constructor() {
        super();

        this.bindClassMethods(['mount', 'loadWarehouses', 'displayWarehouses', 'addHTMLRowsToTable'], this);
        // Create a enw datastore with an initial "empty" state.
        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
        this.header = new Header(this.dataStore);
        this.displayWarehouses = this.displayWarehouses.bind(this);
    }

    mount() {
       //this.dataStore.addChangeListener(this.displayWarehouses);
       this.dataStore.addChangeListener(this.displayWarehouses);
        this.header.addHeaderToPage();
        this.client = new PopStockClient();
       // this.loadWarehouses();
        this.displayWarehouses();
    }


    async loadWarehouses() {
        console.log("Inside loadWarehouses");
        const warehouses = await this.client.getWarehouses();
         this.dataStore.setState({
                 [WAREHOUSE_LIST]: warehouses,
             });
    }

    displayWarehouses() {
       // const warehouseList = this.dataStore.get(WAREHOUSE_LIST);

        //const warehousesContainer = document.getElementById('warehouses-container');
       // const warehousesDisplay = document.getElementById('warehouses-display')
        const warehousesTableHTML = document.getElementById('warehouses-table');
        //warehousesTableHTML.innerHTML = '';
        //const warehouseList = warehouses.warehouses
        this.addHTMLRowsToTable(warehousesTableHTML);
    }

    async addHTMLRowsToTable(warehousesTableHTML) {
        const warehouseList = await this.client.getWarehouses();
        for (const warehouse of warehouseList.warehouses) {
            const row = document.createElement('tr');

            const warehouseNameCell = document.createElement('td');
            //const warehouseId = '${warehouse.warehouseId}';
            warehouseNameCell.textContent = warehouse.name;
            row.appendChild(warehouseNameCell);

            const selectButtonCell = document.createElement('td');
            const selectButton = document.createElement('button');
            selectButton.textContent = 'Select';
            selectButton.className = 'button';
            selectButton.addEventListener('click', () => {
                window.location.href = '/inventory.html?warehouse=' + warehouse.warehouseId;
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
                    await this.client.deleteWarehouse(warehouse.id);
                    this.loadWarehouses();
                }
            });
            deleteButtonCell.appendChild(deleteButton);
            row.appendChild(deleteButtonCell);

            warehousesTableHTML.appendChild(row);
        }
    }

    // async createWarehouse() {

    //     const createWarehouseButton = document.getElementById('createWarehouse');
    //     createWarehouseButton.innerText = 'Create New Warehouse';
    //     createWarehouseButton.className = 'button';
    //     createWarehouseButton.addEventListener('click', () => {
    //         window.location.href = '/updateWarehouse.html?warehouse=' + warehouseId;
    //     });
    // }
}

const main = async () => {
    const getWarehouses = new GetWarehouses();
    getWarehouses.mount();
};


window.addEventListener('DOMContentLoaded', main);
