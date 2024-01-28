import PopStockClient from '../api/popStockClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';


class CreateItem extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit', 'redirectToViewItems'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.redirectToViewItems);
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

        const item = await this.client.createItem(name, (error) => {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        }
        );
        this.dataStore.set('item', item);
    }

    redirectToViewItems() {
        const item = this.dataStore.get('item');
        if (item != null) {
            window.location.href = `/createItem.html`;
        }
    }
}

const main = async () => {
    const createItem = new CreateItem();
    createItem.mount();
};

window.addEventListener('DOMContentLoaded', main);