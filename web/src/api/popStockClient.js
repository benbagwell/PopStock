import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";

export default class PopStockClient extends BindingClass {
    constructor(props = {}) {
        super();

        const methodsToBind = ['clientLoaded', 'getIdentity', 'login', 'logout', 'createItem'];
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

    async createItem(category,name,regionOfOrigin,regionalDemand,salesForecast,perPallet,weight,purchaseCost,baseMargin,rateOfReplenishment,synergy, errorCallback){
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can create Items.");
            const response = await this.axiosClient.post(`items`, {
                category:category,
                name:name,
                regionOfOrigin:regionOfOrigin,
                regionalDemand:regionalDemand,
                salesForecast:salesForecast,
                perPallet:perPallet,
                weight:weight,
                purchaseCost:purchaseCost,
                baseMargin:baseMargin,
                rateOfReplenishment:rateOfReplenishment,
                synergy:synergy
            }, {
                headers: {
                   Authorization: `Bearer ${token}`
                }
            });
            return response.data.event;
        } catch (error) {
            this.handleError(error)
        }
    }

    async createWarehouse(name,region, errorCallback){
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
            return response.data.event;
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