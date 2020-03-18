<template>
    <div class="container">
        <h2>Парк транспортных средств</h2>
        <div v-if="message" class="alert alert-success">
            {{message}}
        </div>
        <div>
            <b-table striped hover :busy="transportmodels.length===0" :items="transportmodels" :fields="transportmodel_fields" responsive="sm">
                <template v-slot:table-busy>
                    <div class="text-center text-danger my-2">
                        <b-spinner class="align-middle"></b-spinner>
                        <strong>Загрузка...</strong>
                    </div>
                </template>
            <template v-slot:cell(show_details)="row">
                <b-button size="sm" @click="row.toggleDetails" class="mr-2">
                    {{ row.detailsShowing ? 'Скрыть' : 'Показать'}}
                </b-button>
            </template>
                <template v-slot:row-details="row">
                    <b-card>
                        <b-table :ref=String(row.item.id)
                                 striped
                                 :items="row.item.transports"
                                 :fields="transport_fields">
                        <template v-slot:cell(id)="data">
                                <b-button class="btn btn-warning" size="sm" v-on:click="editTransportClicked(data.item.id)">Редактировать</b-button>
                                <b-button class="btn btn-danger" size="sm" v-on:click="deleteTransportClicked(data.item.id)">Удалить</b-button>
                        </template>
                        </b-table>
                        <b-button size="sm" @click="row.toggleDetails">Скрыть</b-button>
                        <b-button class="btn btn-success" size="sm" @click="addTransportClicked(row.item.id)">Добавить транспортное средство</b-button>
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
                    {
                        key: 'show_details',
                        label: 'Экземпляры авто'
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
                transportmodels: null,
                message: null,
            };
        },
        methods: {
            refreshTransports(){
                let models = [];
                RestAPIService.readAll("transportmodels")
                    .then(response=>{
                        response.data.forEach(function (item) {
                            models.push(new TransportModel(item));
                        });
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
                        this.message = `Транспортное средство удалено!`;
                        this.refreshTransports();
                    });
            },
            editTransportClicked(transportId){
                this.$router.push({name: 'Edit Transport', params:{transportId:transportId}})
            },
            addTransportClicked(id){
                this.$router.push(`/transports/${id}`)
            },
        },
        created() {
            this.refreshTransports();
        }
    };

</script>

<style scoped>

</style>