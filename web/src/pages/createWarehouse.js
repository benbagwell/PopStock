import PopStockClient from '../api/popStockClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';


class CreateWarehouse extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    /**
     * Add the header to the page and load the PopStockClient.
     */
    mount() {
        document.getElementById('create').addEventListener('click', this.submit);

        this.header.addHeaderToPage();

        this.client = new PopStockClient();
    }

    async submit(evt) {
        evt.preventDefault();

        const createButton = document.getElementById('create');
        createButton.innerText = 'Loading...';

        const name = document.getElementById('name').value;
        await this.client.createWarehouse(name);
        window.location.href = '/index.html';
    }

}

const main = async () => {
    const createWarehouse = new CreateWarehouse();
    createWarehouse.mount();
};

window.addEventListener('DOMContentLoaded', main);