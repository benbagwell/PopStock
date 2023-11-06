import PopStockClient from '../api/popStockClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';


class CreateWarehouse extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit', 'redirectToViewWarehouses'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.redirectToViewWarehouses);
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

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const createButton = document.getElementById('create');
        const origButtonText = createButton.innerText;
        createButton.innerText = 'Loading...';

        const name = document.getElementById('name').value;
        const region = document.getElementById('region').value;

        const warehouse = await this.client.createWarehouse(name, region, (error) => {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        }
        );
        this.dataStore.set('warehouse', warehouse);
    }


    redirectToViewWarehouses() {
        const warehouse = this.dataStore.get('warehouse');
        if (warehouse != null) {
            window.location.href = `/createWarehouse.html`;
        }
    }
}

const main = async () => {
    const createWarehouse = new CreateWarehouse();
    createWarehouse.mount();
};

window.addEventListener('DOMContentLoaded', main);