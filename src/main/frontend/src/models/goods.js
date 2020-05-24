export default class Goods {
    constructor(data) {
        if (data === undefined) {
            this.name = '';
            this.amount = '';
            this.length = '';
            this.height = '';
            this.weight = '';
        } else {
            this.id = data.id;
            this.name = data.name;
            this.amount = data.amount;
            this.length = data.length;
            this.height = data.height;
            this.weight = data.weight;
            this.transportorder = data.transportorder;
        }
    }
}