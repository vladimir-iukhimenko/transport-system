<template>
    <div>
        <h1 v-if="transport.id">
            Редактировать транспортное средство</h1>
        <h1 v-else>Добавить транспортное средство</h1>
        <div class="container">
            <b-form @submit="validateAndSubmit">
                <b-form-group id="input-group-1"
                        label="Номер а/м"
                        label-for="input-1">
                    <b-form-input id="input-1"
                                  v-model="transport.number"
                                  type="text"
                                  required
                                  placeholder="Государственный номер автомобиля">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-2"
                              label="VIN-номер"
                              label-for="input-2">
                    <b-form-input id="input-2"
                                  v-model="transport.vin"
                                  type="text"
                                  required
                                  placeholder="16-значный номер">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-3"
                              label="Год выпуска"
                              label-for="input-3">
                    <b-form-input id="input-3"
                                  v-model="transport.producedyear"
                                  type="text"
                                  required>
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-4"
                              label="Ввод в эксплуатацию"
                              label-for="input-4">
                    <b-form-input id="input-4"
                                  v-model="transport.startupdate"
                                  type="date"
                                  required>
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-5"
                              label="Дата вывода из эксплуатации"
                              label-for="input-5">
                    <b-form-input id="input-5"
                                  v-model="transport.writeoffdate"
                                  type="date">
                    </b-form-input>
                </b-form-group>
                <b-button variant="primary" type="submit" v-if="transport.id">Редактировать</b-button>
                <b-button variant="primary" type="submit" v-else>Добавить</b-button>
            </b-form>
        </div>
    </div>
</template>

<script>
    import RestAPIService from "../service/RestAPIService";
    export default {
        name: "Transport",
        data(){
            return{
                transport: [],
                errors: [],
            };
        },
        computed: {
            transportId() {
                return this.$route.params.transportId;
            },
            modelId() {
                return this.$route.params.modelId;
            }
        },
        methods: {
            getTransportDetails() {
                RestAPIService.retrieve(this.transportId, "transports").then(resource => {
                    this.transport = resource.data;
                });
            },
            validateAndSubmit(e) {
                e.preventDefault();
                this.errors=[];
                if (this.transport.id) {
                    RestAPIService.update("transports",{
                        id: this.transportId,
                        number: this.transport.number,
                        vin: this.transport.vin,
                        transportmodelid: this.modelId,
                        producedyear: this.transport.producedyear,
                        startupdate: this.transport.startupdate,
                        writeoffdate: this.transport.writeoffdate,
                    }).then(()=> {
                        this.$router.push(`/transports`);
                    });
                }
                else {
                    RestAPIService.create("transports",{
                        number: this.transport.number,
                        vin: this.transport.vin,
                        transportmodelid: this.modelId,
                        producedyear: this.transport.producedyear,
                        startupdate: this.transport.startupdate,
                        writeoffdate: this.transport.writeoffdate,
                    }).then(()=> {
                        this.$router.push(`/transports`);
                    });
                }
            }
        },
        created() {
            if(window.location.href.indexOf("edit")>-1) {this.getTransportDetails();}

        }
    }
</script>

<style scoped>

</style>