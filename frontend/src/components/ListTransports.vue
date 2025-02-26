<template>
    <div class="container">
        <h2>Парк транспортных средств</h2>
        <div v-if="!isBusy && transportmodels.length===0" class="alert alert-danger">
            {{message}}
        </div>
        <div>
            <b-table striped selectable select-mode="multi" @row-selected="showDetails" hover :busy="isBusy" :items="transportmodels" :fields="transportmodel_fields" responsive="sm">
                <template v-slot:table-busy>
                    <div class="text-center text-danger my-2">
                        <b-spinner class="align-middle"></b-spinner>
                        <strong>Загрузка...</strong>
                    </div>
                </template>
                <template v-slot:row-details="row">
                    <b-card>
                        <b-table :ref=String(row.item.id)
                                 striped
                                 :items="row.item.transports"
                                 :fields="transport_fields">
                        <template v-slot:cell(id)="data">
                                <b-button class="btn btn-warning" size="sm" v-on:click="editTransportClicked(data.item.id)"><b-icon-pencil></b-icon-pencil></b-button>
                                <b-button class="btn btn-danger" size="sm" v-on:click="deleteTransportClicked(data.item.id)"><b-icon-x-circle-fill></b-icon-x-circle-fill></b-button>
                        </template>
                        </b-table>
                        <b-button id="addTransport" class="btn btn-success" size="sm" @click="addTransportClicked(row.item.id)">Добавить транспортное средство</b-button>
                    </b-card>
                </template>
            </b-table>
        </div>
    </div>
</template>

<script>
    import RestAPIService from "../service/RestAPIService";
    import TransportModel from "../models/transportmodel";
    export default {
        name: "ListTransports",
        data() {
            return {
                transportmodel_fields:[
                    {
                        key: 'name',
                        label: 'Название',
                        sortable: true
                    },
                    {
                        key: 'producer',
                        label: 'Производитель',
                        sortable: true
                    },
                    {
                        key: 'maxweight',
                        label: 'Максимальный вес',
                        sortable: true
                    },
                    {
                        key: 'length',
                        label: 'Длина'
                    },
                    {
                        key: 'width',
                        label: 'Ширина'
                    },
                    {
                        key: 'height',
                        label: 'Высота'
                    },
                ],
                transport_fields:[
                    {
                        key: 'number',
                        label: 'Гос.номер'
                    },
                    {
                        key: 'vin',
                        label: 'VIN-номер'
                    },
                    {
                        key: 'producedyear',
                        label: 'Год выпуска',
                    },
                    {
                        key: 'color',
                        label: 'Цвет',
                    },
                    {
                        key: 'enginepower',
                        label: 'Мощность',
                    },
                    {
                        key: 'startupdate',
                        label: 'Старт эксплуатации'
                    },
                    {
                        key: 'writeoffdate',
                        label: 'Дата списания'
                    },
                    {
                        key: 'id',
                        label: 'Действия'
                    },
                ],
                transportmodels: [],
                message: '',
                isBusy: false,
                selectedTransportModels: [],
            };
        },
        methods: {
            refreshTransports(){
                this.isBusy = !this.isBusy;
                let models = [];
                RestAPIService.readAll("transportmodels")
                    .then( response=> {
                        response.data.forEach(function (item) {
                            models.push(new TransportModel(item));
                        });
                        this.isBusy = !this.isBusy;
                        if (models.length === 0) this.message = 'Транспортные средства не найдены!';
                    }).catch(err => {
                    this.isBusy = !this.isBusy;
                    this.message = err.message;
                });
                this.transportmodels = models;
            },
            getTransports(transportsId) {
                let transports = [];
                transportsId.forEach(function (item) {
                    RestAPIService.retrieve(item,"transports").then(
                        response => {transports.push(response.data);}
                    )});
                console.log(transports);
                return transports;
            },
            deleteTransportClicked(id){
                RestAPIService.delete(id,"transports")
                    .then(()=> {
                        this.$bvToast.toast('Транспортное средство удалено!',{autoHideDelay:5000,title:'Транспортная система'});
                        this.refreshTransports();
                    });
            },
            editTransportClicked(transportId){
                this.$router.push(`/transports/edit/${transportId}`)
            },
            addTransportClicked(id){
                this.$router.push(`/transports/${id}`)
            },
            showDetails(rows) {
                this.selectedTransportModels = rows;
                this.transportmodels.forEach(item => {
                    if (rows.length === 0) item._showDetails = false;
                    else for (let selectedItem of this.selectedTransportModels) {
                        if (item.id === selectedItem.id) {
                            item._showDetails = true;
                            break;
                        }
                        item._showDetails = false;
                    }
                });
            },
        },
        created() {
            this.refreshTransports();
        }
    };

</script>

<style scoped>

</style>