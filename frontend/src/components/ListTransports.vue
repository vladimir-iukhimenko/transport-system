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
                                 selectable select-mode="single" striped
                                 @row-clicked="onRowSelected($event,row.item.id)"
                                 :items="row.item.transports"
                                 :fields="transport_fields">
                        <template v-slot:cell(actions)="{rowSelected}">
                            <template v-if="rowSelected">
                                <b-button class="btn btn-warning" size="sm" v-on:click="editTransportClicked(transportSelected.id,transportModelIdSelected)">Редактировать</b-button>
                                <b-button class="btn btn-danger" size="sm" v-on:click="deleteTransportClicked(transportSelected.id)">Удалить</b-button>
                            </template>
                            <template v-else>
                                <b-button size="sm" disabled>Редактировать</b-button>
                                <b-button size="sm" disabled>Удалить</b-button>
                            </template>
                        </template>
                        </b-table>
                        <b-button size="sm" @click="row.toggleDetails">Скрыть</b-button>
                        <b-button class="btn btn-success" size="sm" @click="addTransportClicked(row.item.id)">Добавить транспортное средство</b-button>
                    </b-card>
                </template>
            </b-table>
            <p>
                Selected Rows:<br>

                {{transportSelected}}

                Selected Element:<br>

                {{transportModelIdSelected}}
            </p>
        </div>
    </div>
</template>

<script>
    import RestAPIService from "../service/RestAPIService";
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
                        key:'actions',
                        label: 'Действия'
                    },
                ],
                transportmodels: [],
                message: null,
                transportSelected: null,
                transportModelIdSelected: null,
            };
        },
        methods: {
            refreshTransports(){
                RestAPIService.readAll("transportmodels")
                    .then(response=>{
                        this.transportmodels = response.data;
                    });
            },
            deleteTransportClicked(id){
                RestAPIService.delete(id,"transports")
                    .then(()=> {
                        this.message = `Транспортное средство удалено!`;
                        this.refreshTransports();
                    });
            },
            editTransportClicked(transportId,transportModelId){
                this.$router.push({name: 'Edit Transport', params:{transportId:transportId, modelId:transportModelId}})
            },
            addTransportClicked(id){
                this.$router.push(`/transports/${id}`)
            },
            onRowSelected(items,id) {
                if (items === this.transportSelected) this.transportSelected = null;
                else this.transportSelected = items;
                if(id !== this.transportModelIdSelected && this.transportModelIdSelected !== '' && this.$refs[`${this.transportModelIdSelected}`] !== undefined) {
                    this.$refs[`${this.transportModelIdSelected}`].clearSelected();
                }
                this.transportModelIdSelected = id;
            },
        },
        created() {
            this.refreshTransports();
        }
    };

</script>

<style scoped>

</style>