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
            this.fuelName = this.fuelOptions[data.fuel];
        }
    }
    fuelOptions = {
        PETROL: 'Бензиновый',
        DIESEL: 'Дизельный',
        GAS: 'Газовый',
        ELECTRIC: 'Электрический',
        HYBRID: 'Гибридный'
    }
}