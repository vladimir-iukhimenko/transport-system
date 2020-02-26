<template>
    <div>
        <NavigationBar></NavigationBar>
        <div v-if="message" class="alert alert-success">
            {{message}}
        </div>
        <ListItems :is-selectable="true" @selected="onSelectedRow" :table-items="tableItems" :table-fields="tableFields" header="Документы транспортных средств">
        </ListItems>
        <div>
            <b-button-group v-if="selectedRow.length > 0">
                <b-button @click="openGoods">Товары</b-button>
                <b-button @click="openAdditionalInfo">Доп. информация</b-button>
            </b-button-group>
        </div>
        <b-modal id="modal-form-1" hide-footer title="Дополнительная информация">
            <b-card>
                <b-row>
                    <b-col>Номер автомобиля:</b-col>
                    <b-col>{{transportFromSelectedRow.number}}</b-col>
                </b-row>
                <b-row>
                    <b-col>VIN номер:</b-col>
                    <b-col>{{transportFromSelectedRow.vin}}</b-col>
                </b-row>
                <b-row>
                    <b-col>Заказчик:</b-col>
                    <b-col>{{employeeResponsibleFromSelectedRow.surname + ' ' + employeeResponsibleFromSelectedRow.name}}</b-col>
                </b-row>
            </b-card>
        </b-modal>
        <b-modal id="modal-form-2" hide-footer title="Товары">
            <b-card>

            </b-card>
        </b-modal>
    </div>
</template>

<script>
    import NavigationBar from "../components/NavigationBar";
    import ListItems from "../components/ListItems";
    import RestAPIService from "../service/RestAPIService";
    export default {
        name: "TransportOrders",
        components: {NavigationBar, ListItems},
        data() {
            return {
                selectedRow: [],
                transportFromSelectedRow: [],
                employeeResponsibleFromSelectedRow: [],
                tableItems: RestAPIService.readAll("transportorders")
                    .then(response=>{this.tableItems = response.data}),
                tableFields: [
                    {
                        key: 'ordernumber',
                        label: 'Номер заказа'
                    },
                    {
                        key: 'orderdate',
                        label: 'Дата заказа'
                    },
                    {
                        key: 'transportpresentingdate',
                        label: 'Дата выполнения заказа'
                    },
                    {
                        key: 'loadingplace',
                        label: 'Место погрузки'
                    },
                    {
                        key: 'unloadingplace',
                        label: 'Место разгрузки'
                    },
                    {
                        key: 'placemethod',
                        label: 'Метод погрузки'
                    },
                    {
                        key: 'telephonenumber',
                        label: 'Контактный номер'
                    },
                ]
            }
        },
        methods:{
            onSelectedRow(row) {
                this.selectedRow = row;
                const transportid = typeof row[0].transport === "number" ? row[0].transport : row[0].transport.id;
                const responsibleEmployeeid = typeof row[0].employeeresponsible === "number" ? row[0].employeeresponsible : row[0].employeeresponsible.id;
                this.transportFromSelectedRow = RestAPIService.retrieve(transportid,"transports")
                    .then(response=>{this.transportFromSelectedRow = response.data});
                this.employeeResponsibleFromSelectedRow = RestAPIService.retrieve(responsibleEmployeeid,"employees")
                    .then(response=>{this.employeeResponsibleFromSelectedRow = response.data});
            },
            openAdditionalInfo() {
                this.$bvModal.show('modal-form-1');
            },
            openGoods() {
                this.$bvModal.show('modal-form-2')
            }
        }
    }
</script>

<style scoped>

</style>