export default class Employee {
    constructor(data) {
        if (data === undefined) {
            this.surname = '';
            this.name = '';
            this.patronymic = '';
            this.department = '';
            this.position = '';
            this.address = '';
            this.telephonenumber = '';
            this.dateofreceipt = '';
            this.dateofdismissal = '';
        } else
        {
            this.id = data.id;
            this.surname = data.surname;
            this.name = data.name;
            this.patronymic = data.patronymic;
            this.department = data.department;
            this.position = data.position;
            this.address = data.address;
            this.telephonenumber = data.telephonenumber;
            this.dateofreceipt = data.dateofreceipt;
            this.dateofdismissal = data.dateofdismissal;
        }
    }
}