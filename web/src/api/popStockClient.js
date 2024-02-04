import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";

export default class PopStockClient extends BindingClass {
    constructor(props = {}) {
        super();

        const methodsToBind = ['clientLoaded', 'getIdentity', 'login', 'logout', 'createItem', 'createWarehouse', 'getWarehouses'];
        this.bindClassMethods(methodsToBind, this);

        this.authenticator = new Authenticator();;
        this.props = props;

        axios.defaults.baseURL = process.env.API_BASE_URL;
        this.axiosClient = axios;
        this.clientLoaded();
    }

    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     */
    clientLoaded() {
        if (this.props.hasOwnProperty("onReady")) {
            this.props.onReady(this);
        }
    }

    /**
     * Get the identity of the current user
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The user information for the current user.
     */
    async getIdentity(errorCallback) {
        try {
            const isLoggedIn = await this.authenticator.isUserLoggedIn();

            if (!isLoggedIn) {
                 return undefined;
            }

             return await this.authenticator.getCurrentUserInfo();
        } catch (error) {
            this.handleError(error)
        }
    }

    async login() {
        this.authenticator.login();
     }

    async logout() {
        this.authenticator.logout();
    }

    async getTokenOrThrow(unauthenticatedErrorMessage) {
        const isLoggedIn = await this.authenticator.isUserLoggedIn();
        if (!isLoggedIn) {
          throw new Error(unauthenticatedErrorMessage);
        }

        return await this.authenticator.getUserToken();
    }

    async createItem(warehouse, name){
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can create Items.");
            const response = await this.axiosClient.post(`warehouses/${warehouse}/inventory`, {
                warehouseId:warehouse,
                name:name,
            }, {
                headers: {
                   Authorization: `Bearer ${token}`
                }
            });
            return response.data.item;
        } catch (error) {
            this.handleError(error)
        }
    }

    async createWarehouse(name,region, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can create warehouses.");
            const response = await this.axiosClient.post(`warehouses`, {
                name:name,
                region:region,
            }, {
                headers: {
                   Authorization: `Bearer ${token}`
                }
            });
            return response.data.warehouse;
        } catch (error) {
            this.handleError(error)
        }
    }

    async getWarehouses() {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can get Warehouses.");
            const response = await this.axiosClient.get(`warehouses`, {
                headers: {
                   Authorization: `Bearer ${token}`
                }
            });
            return response.data.warehousesModel;
        } catch (error) {
            this.handleError(error)
        }
    }

    async updateWarehouse(warehouse,name) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can udpate Warehouses.");
            const response = await this.axiosClient.put(`warehouses`,  {
                name:name,
                warehouseId:warehouse,
            }, {
                headers: {
                   Authorization: `Bearer ${token}`
                }}
                );
            return response.data.warehouse;
        } catch (error) {
            this.handleError(error)
        }
    }

    async deleteWarehouse(warehouse) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can delete Warehouses.");
            const response = await this.axiosClient.delete(`warehouses/${warehouse}`, {
                headers: {
                   Authorization: `Bearer ${token}`
                }
            });
            return response.data.warehouseModel;
        } catch (error) {
            this.handleError(error)
        }
    }

    async getInventory(warehouse) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can get Items.");
            const response = await this.axiosClient.get(`/warehouses/${warehouse}/inventory`, {
                headers: {
                   Authorization: `Bearer ${token}`
                }
            });
            console.log("In client getInventory: " + response.data.items);
            return response.data.items;
        } catch (error) {
            this.handleError(error)
        }
    }

    handleError(error, errorCallback) {
        console.error(error);

        const errorFromApi = error?.response?.data?.error_message;
        if (errorFromApi) {
            console.error(errorFromApi)
            error.message = errorFromApi;
        }

        if (errorCallback) {
            errorCallback(error);
        }
    }
}