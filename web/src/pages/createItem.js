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
     * Add the header to the page and load the MusicPlaylistClient.
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
        const category = document.getElementById('category').value;
        const regionOfOrigin = document.getElementById('region-of-origin').value;
        const regionalDemand = document.getElementById('regional-demand').value;
        salesForecast = document.getElementById('sales-forecast').value;
        perPallet = document.getElementById('per-pallet').value;
        weight = document.getElementById('weight').value;
        purchaseCost = document.getElementById('purchase-cost').value;
        baseMargin = document.getElementById('base-margin').value;
        rateOfReplenishment = document.getElementById('rate-of-replenishment').value;
        synergy = document.getElementById('synergy').value;

        const item = await this.client.createItem(name, category, regionOfOrigin, regionalDemand, salesForecast, perPallet,
         weight, purchaseCost, baseMargin, rateOfReplenishment, synergy (error) => {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });
        this.dataStore.set('item', item);
    }


    redirectToViewPlaylist() {
//        const playlist = this.dataStore.get('playlist');
//        if (playlist != null) {
//            window.location.href = `/playlist.html?id=${playlist.id}`;
//        }
    }
}

const main = async () => {
    const createItem = new CreateItem();
    createItem.mount();
};

window.addEventListener('DOMContentLoaded', main);