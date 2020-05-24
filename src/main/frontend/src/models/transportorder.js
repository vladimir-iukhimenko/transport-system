export default class TransportOrder {
    constructor(data) {
        if (data === undefined) {
            this.transportpresentingdate = '';
            this.transportid = '';
            this.loadingplace = '';
            this.unloadingplace = '';
            this.placemethod = '';
            this.employeeresponsibleid = '';
            this.telephonenumber = '';
            this.employeecustomerid = '';
            this.declinereason = '';
            this.comment = '';
            this.goodsIds = [];
        } else {
            this.id = data.id;
            this.ordernumber = data.ordernumber;
            this.orderdate = data.orderdate;
            this.transportpresentingdate = data.transportpresentingdate;
            this.transportid = data.transport;
            this.loadingplace = data.loadingplace;
            this.unloadingplace = data.unloadingplace;
            this.placemethod = data.placemethod;
            this.employeeresponsibleid = data.employeeresponsible;
            this.telephonenumber = data.telephonenumber;
            this.employeecustomerid = data.employeecustomer;
            this.declinereason = data.declinereason;
            this.comment = data.comments;
            this.goodsIds = data.goods;
        }
    }
}