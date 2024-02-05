import PopStockClient from '../api/popStockClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

class GetTransactions extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'loadTransactions', 'backSubmit', 'clientLoaded', 'addHTMLRowsToTable', 'Submit'], this);

        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    mount() {
        this.header.addHeaderToPage();
        this.client = new PopStockClient();
        this.clientLoaded();
        document.getElementById('start-report-button').addEventListener('click', this.Submit);
        document.getElementById('back-button').addEventListener('click', this.backSubmit);
    }
    
    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const warehouse = urlParams.get('warehouse');
        const warehouseName = urlParams.get('name');
        document.getElementById('warehouse-name').innerText = "\x22" + warehouseName + "\x22 Transaction Report";
        this.dataStore.set('warehouse', warehouse);
        this.dataStore.set('warehouseName', warehouseName);
    }

    async Submit(evt) {
        this.loadTransactions();
    }

    async backSubmit(evt) {
        evt.preventDefault();

        const warehouse = this.dataStore.get('warehouse');
        const warehouseName = this.dataStore.get('warehouseName')

        window.location.href = `/inventory.html?warehouse=${warehouse}&name=${warehouseName}`; 
    }


    async loadTransactions() {
        const startDate = document.getElementById('start-date').value;
        const endDate = document.getElementById('end-date').value;
        const transactions = await this.client.getTransactions(this.dataStore.get('warehouse'),startDate,endDate);
 
        this.dataStore.set('transactions', transactions);
        const reportTableHTML = document.getElementById('report-table');
        this.addHTMLRowsToTable(reportTableHTML);
    }

    async addHTMLRowsToTable(reportTableHTML) {

        const transactions = this.dataStore.get('transactions');
       
        
        if (transactions.length == 0 || transactions == null) {
             const noTransactionsCell = document.createElement('td');
             noTransactionsCell.textContent = "There are currently no transactions to report.";
             const row = document.createElement('tr');
             row.appendChild(noTransactionsCell);
             reportTableHTML.appendChild(row);
         } else {

            for (const transaction of transactions) {
                const row = document.createElement('tr');
                
                const transactionIdCell = document.createElement('td');
                transactionIdCell.textContent = transaction.transactionsId;
                row.appendChild(transactionIdCell)
    
                const itemIdCell = document.createElement('td');
                itemIdCell.textContent = transaction.itemId;
                row.appendChild(itemIdCell);

                const countCell = document.createElement('td');
                countCell.textContent = transaction.count;
                row.appendChild(countCell);

                const typeCell = document.createElement('td');
                typeCell.textContent = transaction.transactionType;
                row.appendChild(typeCell);
    
                const dateCell = document.createElement('td');
                dateCell.textContent = transaction.transactionDate;
                row.appendChild(dateCell);

                const partnerCell = document.createElement('td');
                partnerCell.textContent = transaction.partnerId;
                row.appendChild(partnerCell);

                reportTableHTML.appendChild(row);
            }

         }
 
    }

}

const main = async () => {
    const getTransactions = new GetTransactions();
    getTransactions.mount();
};


window.addEventListener('DOMContentLoaded', main);
