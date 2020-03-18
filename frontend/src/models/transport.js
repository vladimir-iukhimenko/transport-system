export default class Transport {
    constructor(data) {
        if (data === undefined) {
            this.number = '';
            this.vin = '';
            this.transportmodelid = '';
            this.producedyear = '';
            this.startupdate = '';
            this.writeoffdate = '';
            this.color = '';
            this.enginepower = '';
            this.engineid = ''
        } else
            {this.id = data.transportId;
            this.number = data.number;
            this.vin = data.vin;
            this.transportmodelid = data.transportmodel;
            this.producedyear = data.producedyear;
            this.startupdate = data.startupdate;
            this.writeoffdate = data.writeoffdate;
            this.color = data.color;
            this.enginepower = data.enginepower;
            this.engineid = data.engine;
            }
    }
}