<template>
    <div>
        <NavigationBar></NavigationBar>
        <ListItems :is-selectable="true" :is-busy="isBusy" @selected="onSelectedRow" :table-items="tableItems" :table-fields="tableFields" :message="message" header="Двигатели">
        </ListItems>
        <div class="text-center">
        <b-button class="buttons col-sm-2" @click="addEngine">Добавить</b-button>
        <b-button class="buttons col-sm-2" v-show="tableItems.length!=0" @click="editEngine">Редактировать</b-button>
        </div>
        <b-modal id="modal-form" no-close-on-backdrop hide-footer :title="title + ' двигатель'">
            <b-form @submit.prevent="validateAndSubmit">
                <b-form-group id="input-group-1"
                              label="Наименование"
                              label-for="input-1">
                    <b-form-input id="input-1"
                                  v-model="$v.engine.name.$model"
                                  :state="validateState('name')"
                                  type="text"
                                  aria-describedby="input-1-feedback">
                    </b-form-input>
                    <b-form-invalid-feedback id="input-1-feedback">Обязательное поле</b-form-invalid-feedback>
                </b-form-group>
                <b-form-group id="input-group-2"
                              label="Объем, куб.см."
                              label-for="input-2">
                    <b-form-input id="input-2"
                                  v-model="$v.engine.volume.$model"
                                  :state="validateState('volume')"
                                  type="number"
                                  aria-describedby="input-2-feedback">
                    </b-form-input>
                    <b-form-invalid-feedback id="input-2-feedback">Введите значение от 1 до 10000</b-form-invalid-feedback>
                </b-form-group>
                <b-form-group id="input-group-3"
                              label="Тип двигателя"
                              label-for="input-3">
                    <b-form-select id="input-3"
                                  v-model="$v.engine.fuel.$model"
                                  :state="validateState('fuel')"
                                  :options="fuelOptions"
                                  aria-describedby="input-3-feedback">
                    </b-form-select>
                    <b-form-invalid-feedback id="input-3-feedback">Обязательное поле</b-form-invalid-feedback>
                </b-form-group>
                <b-button class="float-right" variant="primary" type="submit" :disabled="$v.$anyError">{{title}}</b-button>
            </b-form>
        </b-modal>
    </div>
</template>

<script>
    import NavigationBar from "../components/NavigationBar";
    import ListItems from "../components/ListItems";
    import RestAPIService from "../service/RestAPIService";
    import Engine from "../models/engine";
    import {integer, maxValue, minValue, required} from "vuelidate/lib/validators";
    export default {
        name: "Engines",
        components: {ListItems, NavigationBar},
        data() {
            return {
                engine: new Engine(),
                title: '',
                tableItems: [],
                isBusy: false,
                message: '',
                tableFields: [
                    {
                        key: 'name',
                        label: 'Наименование'
                    },
                    {
                        key: 'volume',
                        label: 'Объем двигателя'
                    },
                    {
                        key: 'fuelName',
                        label: 'Тип двигателя'
                    },
                ],
                fuelOptions: [
                    {
                        value: 'PETROL',
                        text: 'Бензиновый'
                    },
                    {
                        value: 'DIESEL',
                        text: 'Дизельный'
                    },
                    {
                        value: 'GAS',
                        text: 'Газовый'
                    },
                    {
                        value: 'ELECTRIC',
                        text: 'Электрический'
                    },
                    {
                        value: 'HYBRID',
                        text: 'Гибридный'
                    },
                ]
            }
        },
        validations: {
            engine: {
                name: {required},
                volume: {
                    required,
                    integer,
                    minValue: minValue(1),
                    maxValue: maxValue(10000)
                },
                fuel: {required}
            }
        },
        methods: {
            refreshTableItems() {
                this.tableItems = [];
                this.isBusy = !this.isBusy;
                RestAPIService.readAll("engines")
                    .then( response => {
                        for (let item of response.data) {this.tableItems.push(new Engine(item));}
                        this.isBusy = !this.isBusy;
                        if (this.tableItems.length === 0) this.message = 'Двигатели не найдены!';
                    }).catch(err => {
                    this.isBusy = !this.isBusy;
                    this.message = err.message;
                })
            },
            validateAndSubmit() {
                this.$v.engine.$touch();
                if (this.$v.engine.$anyError) return;
                if (this.title === "Добавить") {
                    RestAPIService.create("engines", this.engine).then(()=>{
                        this.$bvToast.toast('Информация о двигателе добавлена!', {autoHideDelay:5000, title: 'Транспортная система'});
                        this.$bvModal.hide('modal-form')});
                        this.refreshTableItems();
                }
                else {
                    RestAPIService.update("engines", this.engine).then(()=>{
                        this.$bvToast.toast('Информация о двигателе обновлена!', {autoHideDelay:5000, title: 'Транспортная система'});
                        this.$bvModal.hide('modal-form');
                        this.refreshTableItems();
                    })
                }
            },
            validateState(name) {
                const { $dirty, $error} = this.$v.engine[name];
                return $dirty ? !$error : null;
            },
            onSelectedRow(row) {
                this.engine = row[0] ? new Engine(row[0]) : row;
            },
            editEngine() {
                if (this.engine.id) {
                    this.title = 'Редактировать';
                    this.$bvModal.show('modal-form');
                    setTimeout(()=>{this.prohibitNonNumeric()},0);
                }
                else this.$bvToast.toast('Выберите двигатель!', {autoHideDelay:5000, title: 'Транспортная система'});
            },
            addEngine() {
                this.engine = new Engine();
                this.title = 'Добавить';
                this.$bvModal.show('modal-form');
                setTimeout(()=>{this.prohibitNonNumeric()},0);
            },
            prohibitNonNumeric() {
                document.querySelector("#input-2").addEventListener("keypress", (evt) => {
                    if (isNaN(Number(evt.key))) {
                        evt.preventDefault();
                    }
                });
            },
        },
        created() {
            this.refreshTableItems();
        },
    }
</script>

<style scoped>

    .buttons {
        margin: 25px;
        padding: 5px;
    }

</style>