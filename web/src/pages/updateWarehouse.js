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
        document.getElementById('update').addEventListener('click',this.submit)

        this.header.addHeaderToPage();

        this.client = new PopStockClient();
        this.clientLoaded();
    }

    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const warehouseId = urlParams.get('warehouse');
        const warehouse = await this.client.getWarehouse(warehouseId);
        this.dataStore.setState('warehouse', warehouse);
        document.getElementById('warehouse-name').ariaPlaceholder = warehouse.name;
    }

    async submit(evt) {
        evt.preventDefault();

        const updateButton = document.getElementById('update');

        const warehouseName = document.getElementById('name').value;

    }

}

const main = async () => {
    const updateWarehouse = new UpdateWarehouse();
    updateWarehouse.mount();
}