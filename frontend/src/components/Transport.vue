<template>
    <div>
        <h3 v-if="isEdit">
            Редактировать транспортное средство</h3>
        <h3 v-else>Добавить транспортное средство</h3>
        <div class="container">
            <b-form>
                <b-form-group id="input-group-1"
                        label="Номер а/м"
                        label-for="input-1">
                    <b-form-input id="input-1"
                                  v-model="transport.number"
                                  type="text"
                                  required
                                  placeholder="Государственный номер автомобиля">
                    </b-form-input>
                    <b-form-text id="input-group-1">Обязательное поле</b-form-text>
                </b-form-group>
                <b-form-group id="input-group-2"
                              label="VIN-номер"
                              label-for="input-2">
                    <b-form-input id="input-2"
                                  v-model="transport.vin"
                                  :state="transport.vin.length===16"
                                  type="text"
                                  required
                                  placeholder="16-значный номер">
                    </b-form-input>
                    <b-form-invalid-feedback id="input-group-2">
                        Номер должен содержать 16 символов!
                    </b-form-invalid-feedback>
                </b-form-group>
                <b-form-group id="input-group-3"
                              label="Год выпуска"
                              label-for="input-3">
                    <b-form-input id="input-3"
                                  v-model="transport.producedyear"
                                  type="text"
                                  required>
                    </b-form-input>
                    <b-form-text id="input-group-3">Обязательное поле</b-form-text>
                </b-form-group>
                <b-form-group id="input-group-4"
                              label="Цвет"
                              label-for="input-4">
                    <b-form-input id="input-4"
                                  v-model="transport.color"
                                  type="text"
                                  required>
                    </b-form-input>
                    <b-form-text id="input-group-4">Обязательное поле</b-form-text>
                </b-form-group>
                <b-form-group id="input-group-5"
                              label="Мощность двигателя"
                              label-for="input-5">
                    <b-form-input id="input-5"
                                  v-model="transport.enginepower"
                                  type="text"
                                  required>
                    </b-form-input>
                    <b-form-text id="input-group-5">Обязательное поле</b-form-text>
                </b-form-group>
                <b-form-group id="input-group-6"
                              label="Ввод в эксплуатацию"
                              label-for="input-6">
                    <b-form-input id="input-6"
                                  v-model="transport.startupdate"
                                  type="date"
                                  required>
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
                                   v-model="transport.engineid"
                                   :options="engines"
                                   value-field="id"
                                   text-field="name"
                                   required
                                   placeholder="">
                    </b-form-select>
                    <b-form-text id="input-group-8">Обязательное поле</b-form-text>
                </b-form-group>
                <b-button variant="primary" class="buttons col-sm-2" @click.prevent="validateAndSubmit" v-if="isEdit">Сохранить</b-button>
                <b-button variant="primary" class="buttons col-sm-2" @click.prevent="validateAndSubmit" type="submit" v-else>Добавить</b-button>
                <b-button class="buttons col-sm-2" @click="$router.back()">Отмена</b-button>
            </b-form>
        </div>
    </div>
</template>

<script>
    import RestAPIService from "../service/RestAPIService";
    import Transport from "../models/transport";
    export default {
        name: "Transport",
        data() {
            return {
                transport: new Transport(),
                errors: [],
                engines: []
            };
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
                this.errors=[];
                if (this.isEdit) {
                    RestAPIService.update("transports", this.transport).then(()=> {
                        this.$router.push(`/transports`);
                    });
                }
                else {
                    RestAPIService.create("transports", this.transport).then(()=> {
                        this.$router.push(`/transports`);
                    });
                }
            }
        },
        created() {
            RestAPIService.readAll("engines")
                .then(response=>{this.engines = response.data});
            if(this.isEdit) {this.getTransportDetails();}
            else this.transport.transportmodelid = this.transportId;

        },
    }
</script>

<style scoped>

    .buttons {
        margin: 25px;
        padding: 5px;
    }

</style>