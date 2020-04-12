<template>
    <div>
        <NavigationBar></NavigationBar>
        <ListItems :is-selectable="true" :is-busy="isBusy" @selected="onSelectedRow" :table-items="tableItems" :table-fields="tableFields" :message="message" header="Документы транспортных средств">
        </ListItems>
        <div class="text-center">
            <b-button class="buttons col-sm-2" @click="addDoc">Добавить</b-button>
            <b-button class="buttons col-sm-2" v-show="tableItems.length!=0" @click="editDoc">Редактировать</b-button>
        </div>
        <b-modal id="modal-form" no-close-on-backdrop hide-footer :title="title + ' документ'">
            <b-form @submit="validateAndSubmit">
                <b-form-group id="input-group-1"
                              label="Тип документа"
                              label-for="input-1">
                    <b-form-input id="input-1"
                                  v-model="transportdoc.documenttype"
                                  type="text"
                                  required
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-2"
                              label="Серия"
                              label-for="input-2">
                    <b-form-input id="input-2"
                                  v-model="transportdoc.series"
                                  type="text"
                                  required
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-3"
                              label="Номер"
                              label-for="input-3">
                    <b-form-input id="input-3"
                                  v-model="transportdoc.number"
                                  type="text"
                                  required
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-4"
                              label="Выдано"
                              label-for="input-4">
                    <b-form-input id="input-4"
                                  v-model="transportdoc.issuedby"
                                  type="text"
                                  required
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-5"
                              label="Дата выдачи"
                              label-for="input-5">
                    <b-form-input id="input-5"
                                  v-model="transportdoc.issueddate"
                                  type="date"
                                  required
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-6"
                              label="Дата истечения срока действия"
                              label-for="input-6">
                    <b-form-input id="input-6"
                                  v-model="transportdoc.expiredate"
                                  type="date"
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-7"
                              label="Транспортное средство"
                              label-for="input-7">
                    <b-form-select id="input-7"
                                  v-model="transportdoc.transportid"
                                  :options="transports"
                                  value-field="id"
                                  text-field="number"
                                  required
                                  placeholder="">
                    </b-form-select>
                </b-form-group>
                <b-button class="float-right" variant="primary" type="submit">{{title}}</b-button>
            </b-form>
        </b-modal>
    </div>
</template>

<script>
    import NavigationBar from "../components/NavigationBar";
    import ListItems from "../components/ListItems";
    import RestAPIService from "../service/RestAPIService";
    import DocumentTransport from "../models/documenttransport";
    export default {
        name: "TransportDocs",
        components: {ListItems, NavigationBar},
        data() {
            return {
                transportdoc: '',
                title: '',
                isBusy: false,
                message: '',
                transports: RestAPIService.readAll('transports')
                    .then(response=>{this.transports = response.data}),
                tableItems: [],
                tableFields:[
                    {
                        key: 'documenttype',
                        label: 'Тип документа',
                        sortable: true
                    },
                    {
                        key: 'series',
                        label: 'Серия'
                    },
                    {
                        key: 'number',
                        label: 'Номер'
                    },
                    {
                        key: 'issuedby',
                        label: 'Выпущено'
                    },
                    {
                        key: 'issueddate',
                        label: 'Дата выпуска'
                    },
                    {
                        key: 'transportdetails.number',
                        label: 'ТС',
                        sortable: true
                    },
                ]
            }
        },
        methods: {
            refreshTableItems() {
                this.isBusy = !this.isBusy;
                RestAPIService.readAll("transportdocs")
                    .then( response => {
                        for (let item of response.data) {this.tableItems.push(new DocumentTransport(item));}
                        this.isBusy = !this.isBusy;
                        if (this.tableItems.length === 0) this.message = 'Документы транспортных средств не найдены!';
                    }).catch(err => {
                    this.isBusy = !this.isBusy;
                    this.message = err.message;
                })
            },
            validateAndSubmit(e) {
                e.preventDefault();
                if (this.title === "Добавить") {
                    RestAPIService.create("transportdocs",this.transportdoc).then(()=>{
                        this.$bvToast.toast('Информация о документе добавлена!',{autoHideDelay:5000,title:'Транспортная система'});
                        this.$bvModal.hide('modal-form');
                        this.refreshTableItems();
                    })
                }
                else {
                    RestAPIService.update("transportdocs", this.transportdoc).then(()=>{
                        this.$bvToast.toast('Информация о документе обновлена!',{autoHideDelay:5000,title:'Транспортная система'});
                        this.$bvModal.hide('modal-form');
                        this.refreshTableItems();
                    })
                }
            },
            onSelectedRow(row) {
                this.transportdoc = row[0] ? row[0] : row;
            },
            editDoc() {
                if (this.transportdoc) {
                    this.title = 'Редактировать';
                    this.$bvModal.show('modal-form');
                }
                else this.$bvToast.toast('Выберите документ!',{autoHideDelay:5000,title:'Транспортная система'});
            },
            addDoc() {
                this.transportdoc = new DocumentTransport();
                this.title = 'Добавить';
                this.$bvModal.show('modal-form');
            }
        },
        created() {
            this.refreshTableItems();
        }
    }
</script>

<style scoped>

    .buttons {
        margin: 25px;
        padding: 5px;
    }

</style>