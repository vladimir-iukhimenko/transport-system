<template>
    <div>
        <NavigationBar></NavigationBar>
        <b-form @submit="validateAndSubmit">
            <b-form-group id="input-group-1"
                          label="Дата подачи транспортного средства"
                          label-for="input-1">
                <b-form-input id="input-1"
                              v-model="transportorder.transportpresentingdate"
                              type="date"
                              required
                              placeholder="">
                </b-form-input>
            </b-form-group>
            <b-form-group id="input-group-2"
                          label="Транспорт"
                          label-for="input-2">
                <b-form-select id="input-2" v-model="transportorder.transportid">
                    <b-form-select-option v-for="transport in transports" :key="transport.id" :value="transport.id">{{transport.number}}</b-form-select-option>
                </b-form-select>
            </b-form-group>
            <b-button @click="$bvModal.show('select-goods')">Выбрать грузы</b-button>
            <p>{{selectedGoods}}</p>
            <b-form-group id="input-group-3"
                          label="Место погрузки"
                          label-for="input-3">
                <b-form-input id="input-3"
                              v-model="transportorder.loadingplace"
                              type="text"
                              required>
                </b-form-input>
            </b-form-group>
            <b-form-group id="input-group-4"
                          label="Место разгрузки"
                          label-for="input-4">
                <b-form-input id="input-4"
                              v-model="transportorder.unloadingplace"
                              type="text"
                              required>
                </b-form-input>
            </b-form-group>
            <b-form-group id="input-group-5"
                          label="Тип погрузки"
                          label-for="input-5">
                <b-form-input id="input-5"
                              v-model="transportorder.placemethod"
                              type="text"
                              required>
                </b-form-input>
            </b-form-group>
            <b-form-group id="input-group-6"
                          label="Ответственный сотрудник"
                          label-for="input-6">
                <b-form-select id="input-6" v-model="transportorder.responsibleemployeeid">
                    <b-form-select-option v-for="employee in employees" :key="employee.id" :value="employee.id">{{employee.surname + ' ' + employee.name + ' - ' + employee.department}}</b-form-select-option>
                </b-form-select>
            </b-form-group>
            <b-form-group id="input-group-7"
                          label="Контактный номер телефона"
                          label-for="input-7">
                <b-form-input id="input-7"
                              v-model="transportorder.telephonenumber"
                              type="text"
                              required>
                </b-form-input>
            </b-form-group>
            <b-form-group id="input-group-8"
                          label="Заказчик"
                          label-for="input-8">
                <b-form-select id="input-8" v-model="transportorder.customeremployeeid">
                    <b-form-select-option v-for="employee in employees" :key="employee.id" :value="employee.id">{{employee.surname + ' ' + employee.name}}</b-form-select-option>
                </b-form-select>
            </b-form-group>
            <b-form-group id="input-group-9"
                          label="Причина отклонения заказа"
                          label-for="input-9">
                <b-form-input id="input-9"
                              v-model="transportorder.declinereason"
                              type="text">
                </b-form-input>
            </b-form-group>
            <b-form-group id="input-group-10"
                          label="Комментарий"
                          label-for="input-10">
                <b-form-input id="input-10"
                              v-model="transportorder.comment"
                              type="text">
                </b-form-input>
            </b-form-group>
            <b-button variant="primary" type="submit">Добавить</b-button>
        </b-form>
        <b-modal id="select-goods" title="Выберите грузы" hide-footer>
            <b-form-checkbox-group v-model="selectedGoods" :options="goods" value-field="id" text-field="name"></b-form-checkbox-group>
        </b-modal>
    </div>
</template>

<script>
    import NavigationBar from "../components/NavigationBar";
    import RestAPIService from "../service/RestAPIService";
    export default {
        name: "CreateTransportOrder",
        components: {NavigationBar},
        data() {
            return {
                transportorder: [],
                selectedGoods: [],
                goods: RestAPIService.readAll("goods").then(response => {this.goods = response.data}),
                transports: RestAPIService.readAll("transports").then(response => {this.transports = response.data}),
                employees: RestAPIService.readAll("employees").then(response => {this.employees = response.data}),
            }
        },
        methods: {
            validateAndSubmit(e) {
                e.preventDefault();
                RestAPIService.create("transportorders",{
                    orderdate: this.transportorder.orderdate,
                    transportpresentingdate: this.transportorder.transportpresentingdate,
                    transportid: this.transportorder.transportid,
                    loadingplace: this.transportorder.loadingplace,
                    unloadingplace: this.transportorder.unloadingplace,
                    placemethod: this.transportorder.placemethod,
                    responsibleemployeeid: this.transportorder.responsibleemployeeid,
                    telephonenumber: this.transportorder.telephonenumber,
                    customeremployeeid: this.transportorder.customeremployeeid,
                    declinereason: this.transportorder.declinereason,
                    comment: this.transportorder.comment,
                    goodsIds: this.selectedGoods
                }).then(() => {
                    this.$router.push(`/transportorders`)
                    this.$bvToast.toast('Транспортный заказ размещен!',{autoHideDelay:5000,title:'Транспортная система'});
                })
            }
        }
    }
</script>

<style scoped>

</style>