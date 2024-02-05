import PopStockClient from '../api/popStockClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';


class CreateTransaction extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit','clientLoaded'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    mount() {
        document.getElementById('create').addEventListener('click', this.submit);
        this.header.addHeaderToPage();
        this.client = new PopStockClient();
        this.clientLoaded();
    }

    clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const warehouse = urlParams.get('warehouse');
        const item = urlParams.get('item');
        const warehouseName = urlParams.get('warehouseName');
        const itemName = urlParams.get('itemName');

        document.getElementById('warehouse-item-name').innerText = "Update Inventory for Item: \x22" + itemName + "\x22 in warehouse: \x22" + warehouseName + "\x22";

        this.dataStore.set('warehouse', warehouse);
        this.dataStore.set('item', item);
        this.dataStore.set('warehouseName', warehouseName);
        this.dataStore.set('itemName', itemName);
    }

    async submit(evt) {
        evt.preventDefault();

        const warehouse = this.dataStore.get('warehouse');
        const warehouseName = this.dataStore.get('warehouseName');
        const item = this.dataStore.get('item');
        const count = document.getElementById('count').value;
        const date = document.getElementById('date').value;
        const partnerId = document.getElementById('partner-id').value;
        const transactionType = document.getElementById('transaction-type').value;

        const createButton = document.getElementById('create');

        createButton.innerText = 'Loading...';


        await this.client.createTransaction(warehouse, item, count, date, partnerId, transactionType);

        window.location.href = `/inventory.html?warehouse=${warehouse}&name=${warehouseName}`;
    }

}

const main = async () => {
    const createTransaction = new CreateTransaction();
    createTransaction.mount();
};

window.addEventListener('DOMContentLoaded', main);