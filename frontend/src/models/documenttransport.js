import RestAPIService from "../service/RestAPIService";

export default class DocumentTransport {
    constructor(data) {
        if (data === undefined) {
            this.documenttype = '',
            this.series = '',
            this.number = '',
            this.issuedby = '',
            this.issueddate = '',
            this.expiredate = '',
            this.transportid = ''
        } else
        {
            this.id = data.id,
            this.documenttype = data.documenttype,
            this.series = data.series,
            this.number = data.number,
            this.issuedby = data.issuedby,
            this.issueddate = data.issueddate,
            this.expiredate = data.expiredate,
            this.transportid = data.transport,
            this.transportdetails = RestAPIService.retrieve(this.transportid,"transports").then(
                                                            response => {this.transportdetails = response.data;})
        }
    }
}