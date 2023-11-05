import PopStockClient from '../api/popStockClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

class GetWarehouses extends BindingClass {
    constructor() {
        super();

        this.bindClassMethods(['mount'], this);
        // Create a enw datastore with an initial "empty" state.
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    mount() {
        this.header.addHeaderToPage();
        this.client = new PopStockClient();
    }
}

const main = async () => {
    const getWarehouses = new GetWarehouses();
    getWarehouses.mount();
};


window.addEventListener('DOMContentLoaded', main);
