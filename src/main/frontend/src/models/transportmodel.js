import RestAPIService from "../service/RestAPIService";

export default class TransportModel {
    constructor(data) {
        if (data === undefined) {
            this.name = '';
            this.producer = '';
            this.maxweight = '';
            this.length = '';
            this.width = '';
            this.height = '';
            this._showDetails = false;
        } else
        {   this.id = data.id;
            this.name = data.name;
            this.producer = data.producer;
            this.maxweight = data.maxweight;
            this.length = data.length;
            this.width = data.width;
            this.height = data.height;
            this.transports = this.getTransports(data.transports);
            this._showDetails = false;
        }
    }
    getTransports(transportsId) {
        let transports = [];
        transportsId.forEach(function (item) {
            RestAPIService.retrieve(item,"transports").then(
                response => {transports.push(response.data);}
            )});
        return transports;
    }
}