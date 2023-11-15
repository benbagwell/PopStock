import PopStockClient from '../api/popStockClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

class UpdateWarehouse extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit', 'clientLoaded'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    mount() {
        document.getElementById('update').addEventListener('click', this.submit)

        this.header.addHeaderToPage();

        this.client = new PopStockClient();
        this.clientLoaded();
    }

    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const warehouse = urlParams.get('warehouse');
        const name = urlParams.get('name');
        document.getElementById('warehouse-name').textContent = "Current name: " + name;
        this.dataStore.set('warehouse', warehouse);
        document.getElementById('name').placeholder = name;
    }

    async submit(evt) {
        evt.preventDefault();
        const warehouse = this.dataStore.get('warehouse');
        const name = document.getElementById('name').value;

       await this.client.updateWarehouse(warehouse,name);
        window.location.href = "/index.html";

    }

}

const main = async () => {
    const updateWarehouse = new UpdateWarehouse();
    updateWarehouse.mount();
}

window.addEventListener('DOMContentLoaded', main);