import PopStockClient from '../api/popStockClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';


class CreateItem extends BindingClass {
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
        const name = urlParams.get('name');
        this.dataStore.set('warehouse', warehouse);
        this.dataStore.set('warehouseName', name);
    }

    async submit(evt) {
        evt.preventDefault();

        const warehouse = this.dataStore.get('warehouse');
        const warehouseName = this.dataStore.get('warehouseName');

        const createButton = document.getElementById('create');

        createButton.innerText = 'Loading...';

        const name = document.getElementById('name').value;

        await this.client.createItem(warehouse,name);

        window.location.href = `/inventory.html?warehouse=${warehouse}&name=${warehouseName}`;
    }

}

const main = async () => {
    const createItem = new CreateItem();
    createItem.mount();
};

window.addEventListener('DOMContentLoaded', main);