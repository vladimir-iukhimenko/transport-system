<template>
    <div>
        <h3 v-if="isEdit">
            Редактировать транспортное средство</h3>
        <h3 v-else>Добавить транспортное средство</h3>
        <div class="container">
            <b-form @submit.prevent="validateAndSubmit">
                <b-form-group id="input-group-1"
                        label="Номер а/м"
                        label-for="input-1">
                    <b-form-input id="input-1"
                                  v-model="$v.transport.number.$model"
                                  :state="validateState('number')"
                                  type="text"
                                  placeholder="A000AA777">
                    </b-form-input>
                    <b-form-text id="input-group-1">Обязательное поле</b-form-text>
                    <b-form-invalid-feedback id="input-group-1">
                        Введённое значение не соответствует формату
                    </b-form-invalid-feedback>
                </b-form-group>
                <b-form-group id="input-group-2"
                              label="VIN-номер"
                              label-for="input-2">
                    <b-form-input id="input-2"
                                  v-model="$v.transport.vin.$model"
                                  :state="validateState('vin')"
                                  type="text">
                    </b-form-input>
                    <b-form-text id="input-group-2">Обязательное поле | 16-значный номер</b-form-text>
                    <b-form-invalid-feedback id="input-group-2">
                        Введённое значение не соответствует формату
                    </b-form-invalid-feedback>
                </b-form-group>
                <b-form-group id="input-group-3"
                              label="Год выпуска"
                              label-for="input-3">
                    <b-form-input id="input-3"
                                  v-model="$v.transport.producedyear.$model"
                                  :state="validateState('producedyear')"
                                  type="number">
                    </b-form-input>
                    <b-form-text id="input-group-3">Обязательное поле</b-form-text>
                </b-form-group>
                <b-form-group id="input-group-4"
                              label="Цвет"
                              label-for="input-4">
                    <b-form-input id="input-4"
                                  v-model="$v.transport.color.$model"
                                  :state="validateState('color')"
                                  type="text">
                    </b-form-input>
                    <b-form-text id="input-group-4">Обязательное поле</b-form-text>
                </b-form-group>
                <b-form-group id="input-group-5"
                              label="Мощность двигателя"
                              label-for="input-5">
                    <b-form-input id="input-5"
                                  v-model="$v.transport.enginepower.$model"
                                  :state="validateState('enginepower')"
                                  type="number">
                    </b-form-input>
                    <b-form-text id="input-group-5">Обязательное поле</b-form-text>
                </b-form-group>
                <b-form-group id="input-group-6"
                              label="Ввод в эксплуатацию"
                              label-for="input-6">
                    <b-form-input id="input-6"
                                  v-model="$v.transport.startupdate.$model"
                                  :state="validateState('startupdate')"
                                  type="date">
                    </b-form-input>
                    <b-form-text id="input-group-6">Обязательное поле</b-form-text>
                </b-form-group>
                <b-form-group id="input-group-7"
                              label="Дата вывода из эксплуатации"
                              label-for="input-7">
                    <b-form-input id="input-7"
                                  v-model="transport.writeoffdate"
                                  type="date">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-8"
                              label="Двигатель"
                              label-for="input-8">
                    <b-form-select id="input-8"
                                   v-model="$v.transport.engineid.$model"
                                   :state="validateState('engineid')"
                                   :options="engines"
                                   value-field="id"
                                   text-field="name">
                    </b-form-select>
                    <b-form-text id="input-group-8">Обязательное поле</b-form-text>
                </b-form-group>
                <b-button variant="primary" class="buttons col-sm-2" type="submit" v-if="isEdit">Сохранить</b-button>
                <b-button variant="primary" class="buttons col-sm-2" type="submit" v-else>Добавить</b-button>
                <b-button class="buttons col-sm-2" @click="$router.back()">Отмена</b-button>
            </b-form>
        </div>
    </div>
</template>

<script>
    import RestAPIService from "../service/RestAPIService";
    import Transport from "../models/transport";
    import {required, minLength, maxLength, integer, minValue} from "vuelidate/lib/validators";
    import {isCorrectAutonumber} from "../models/validators/validators";
    export default {
        name: "Transport",
        data() {
            return {
                transport: new Transport(),
                errors: [],
                engines: []
            };
        },
        validations: {
            transport: {
                number: {
                    required,
                    isCorrectAutonumber
                },
                vin: {
                    required,
                    minLength: minLength(16),
                    maxLength: maxLength(16)
                },
                producedyear: {
                    required,
                    integer
                },
                color: {required},
                enginepower: {
                    required,
                    integer,
                    minValue: minValue(1)
                },
                startupdate: {required},
                engineid: {required}
            }
        },
        computed: {
            transportId() {
                return this.$route.params.modelId;
            },
            isEdit() {
                return window.location.href.indexOf("edit")>-1;
            }
        },
        methods: {
            getTransportDetails() {
                RestAPIService.retrieve(this.transportId, "transports").then(resource => {
                    this.transport = new Transport(resource.data);
                });
            },
            validateAndSubmit() {
                this.$v.transport.$touch();
                if (this.$v.transport.$anyError) return;
                this.transport.number = this.transport.number.toUpperCase();
                this.transport.vin = this.transport.vin.toUpperCase();
                if (this.isEdit) {
                    this.$router.push(`/transports`);
                    RestAPIService.update("transports", this.transport).then(response => {
                        this.$bvToast.toast(`Информация о транспортном средстве ${response.data.number} обновлена!`, {autoHideDelay:5000, title: 'Транспортная система'})
                    });
                }
                else {
                    this.$router.push(`/transports`);
                    RestAPIService.create("transports", this.transport).then(response => {
                        this.$bvToast.toast(`Транспортное средство ${response.data.number} добавлено!`, {autoHideDelay:5000, title: 'Транспортная система'})
                    });
                }
            },
            validateState(name) {
                const { $dirty, $error } = this.$v.transport[name];
                return $dirty ? !$error : null;
            },
        },
        created() {
            RestAPIService.readAll("engines")
                .then(response => {this.engines = response.data});
            if (this.isEdit) {this.getTransportDetails();}
            else this.transport.transportmodelid = this.transportId;
        },
        mounted() {
            document.querySelector("#input-1").addEventListener("keypress", (evt) => {
                if ((evt.key).search('[0-9авекмнорстухАВЕКМНОРСТУХ]') === -1) {
                    evt.preventDefault();
                }
            });
            document.querySelector("#input-2").addEventListener("keypress", (evt) => {
                if ((evt.key).search('[0-9a-zA-Z]') === -1) {
                    evt.preventDefault();
                }
            });
        }
    }
</script>

<style scoped>

    .buttons {
        margin: 25px;
        padding: 5px;
    }

    #input-1,#input-2 {
        text-transform: uppercase;
    }

</style>