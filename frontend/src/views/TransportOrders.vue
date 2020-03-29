<template>
    <div>
        <NavigationBar></NavigationBar>
        <div v-if="!isBusy && tableItems.length===0" class="alert alert-danger">
            {{message}}
        </div>
        <ListItems :is-selectable="true" :is-busy="isBusy" @selected="onSelectedRow" :table-items="tableItems" :table-fields="tableFields" header="Транспортные заказы">
        </ListItems>
        <div>
            <b-button-group v-if="selectedRow.id">
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
                <h6>Комментарии:</h6>
                <b-list-group>
                    <b-list-group-item v-for="comment in comments" :key="comment.id">
                        {{comment}}
                    </b-list-group-item>
                </b-list-group>
            </b-card>
        </b-modal>
        <b-modal id="modal-form-2" hide-footer title="Товары">
            <b-card>
                <ListItems :table-items="goodsFromSelectedRow"
                           :is-busy="isBusy"
                           :table-fields="goodsTableFields"
                           message="Нет грузов"></ListItems>
            </b-card>
        </b-modal>
    </div>
</template>

<script>
    import NavigationBar from "../components/NavigationBar";
    import ListItems from "../components/ListItems";
    import RestAPIService from "../service/RestAPIService";
    import TransportOrder from "../models/transportorder";
    export default {
        name: "TransportOrders",
        components: {NavigationBar, ListItems},
        data() {
            return {
                selectedRow: [],
                transportFromSelectedRow: [],
                employeeResponsibleFromSelectedRow: [],
                goodsFromSelectedRow: [],
                comments: [],
                isBusy: false,
                message: '',
                tableItems: [],
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
                ],
                goodsTableFields: [
                    {
                        key: 'name',
                        label: 'Наименование'
                    },
                    {
                        key: 'amount',
                        label: 'Количество'
                    }
                ]
            }
        },
        methods: {
            refreshTableItems() {
                this.isBusy = !this.isBusy;
                RestAPIService.readAll("transportorders")
                    .then( response => {
                        this.tableItems = response.data;
                        this.isBusy = !this.isBusy;
                        if (this.tableItems.length === 0) this.message = 'Транспортные заказы не найдены!';
                    }).catch(err => {
                    this.isBusy = !this.isBusy;
                    this.message = err.message;
                })
            },
            onSelectedRow(row) {
                if (row) {
                    this.selectedRow = new TransportOrder(row[0]);
                    this.transportFromSelectedRow = RestAPIService.retrieve(this.selectedRow.transportid,"transports")
                        .then(response=>{this.transportFromSelectedRow = response.data});
                    this.employeeResponsibleFromSelectedRow = RestAPIService.retrieve(this.selectedRow.employeeresponsibleid,"employees")
                        .then(response=>{this.employeeResponsibleFromSelectedRow = response.data});
                    this.comments = this.selectedRow.comment.allComments;
                    this.goodsFromSelectedRow = [];
                    for (let good of this.selectedRow.goodsIds) {
                        RestAPIService.retrieve(good,"goods").then(
                            response => {this.goodsFromSelectedRow.push(response.data);}
                        );
                    }}
            },
            openAdditionalInfo() {
                this.$bvModal.show('modal-form-1');
            },
            openGoods() {
                this.$bvModal.show('modal-form-2')
            }
        },
        created() {
            this.refreshTableItems();
        },
    }
</script>

<style scoped>

</style>