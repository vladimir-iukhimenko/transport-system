export default class Engine {
    constructor(data) {
        if (data === undefined) {
            this.name = '';
            this.volume = '';
            this.fuel = ''
        }
        else {
            this.id = data.id;
            this.name = data.name;
            this.volume = data.volume;
            this.fuel = data.fuel;
        }
    }
}