<template>
    <div>
        <NavigationBar></NavigationBar>
        <div v-if="message" class="alert alert-success">
            {{message}}
        </div>
        <ListItems :is-selectable="true" @selected="onSelectedRow" :table-items="tableItems" :table-fields="tableFields" header="Документы транспортных средств">
        </ListItems>
        <b-button @click="addDoc">Добавить</b-button>
        <b-button @click="editDoc">Редактировать</b-button>
        <b-modal id="modal-form" no-close-on-backdrop hide-footer :title="title + ' документ'">
            <b-form @submit="validateAndSubmit">
                <b-form-group id="input-group-1"
                              label="Тип документа"
                              label-for="input-1">
                    <b-form-input id="input-1"
                                  v-model="transportdoc[0].documenttype"
                                  type="text"
                                  required
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-2"
                              label="Серия"
                              label-for="input-2">
                    <b-form-input id="input-2"
                                  v-model="transportdoc[0].series"
                                  type="text"
                                  required
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-3"
                              label="Номер"
                              label-for="input-3">
                    <b-form-input id="input-3"
                                  v-model="transportdoc[0].number"
                                  type="text"
                                  required
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-4"
                              label="Выдано"
                              label-for="input-4">
                    <b-form-input id="input-4"
                                  v-model="transportdoc[0].issuedby"
                                  type="text"
                                  required
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-5"
                              label="Дата выдачи"
                              label-for="input-5">
                    <b-form-input id="input-5"
                                  v-model="transportdoc[0].issueddate"
                                  type="date"
                                  required
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-6"
                              label="Дата истечения срока действия"
                              label-for="input-6">
                    <b-form-input id="input-6"
                                  v-model="transportdoc[0].expiredate"
                                  type="date"
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-7"
                              label="Транспортное средство"
                              label-for="input-7">
                    <b-form-select id="input-7"
                                  v-model="transportdoc[0].transportid"
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
    export default {
        name: "TransportDocs",
        components: {ListItems, NavigationBar},
        data() {
            return {
                transportdoc: [[]],
                message: null,
                title:'',
                transports: RestAPIService.readAll('transports')
                    .then(response=>{this.transports = response.data}),
                tableItems: RestAPIService.readAll('transportdocs')
                    .then(response=>{this.tableItems = response.data}),
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
                        key: 'transport.number',
                        label: 'ТС',
                        sortable: true
                    },
                ]
            }
        },
        methods: {
            validateAndSubmit(e) {
                e.preventDefault();
                if (this.title === "Добавить") {
                    RestAPIService.create("transportdocs",{
                        documenttype: this.transportdoc[0].documenttype,
                        series: this.transportdoc[0].series,
                        number: this.transportdoc[0].number,
                        issuedby: this.transportdoc[0].issuedby,
                        issueddate: this.transportdoc[0].issueddate,
                        expiredate: this.transportdoc[0].expiredate,
                        transportid: this.transportdoc[0].transportid
                    }).then(()=>{
                        this.message = 'Информация о документе добавлена!';
                        this.$bvModal.hide('modal-form')})
                }
                else {
                    RestAPIService.update("transportdocs", {
                        id: this.transportdoc[0].id,
                        documenttype: this.transportdoc[0].documenttype,
                        series: this.transportdoc[0].series,
                        number: this.transportdoc[0].number,
                        issuedby: this.transportdoc[0].issuedby,
                        issueddate: this.transportdoc[0].issueddate,
                        expiredate: this.transportdoc[0].expiredate,
                        transportid: this.transportdoc[0].transportid
                    }).then(()=>{
                        this.message = 'Информация о документе обновлена!';
                        this.$bvModal.hide('modal-form')
                    })
                }

            },
            onSelectedRow(row) {
                this.transportdoc = row;
            },
            editDoc() {
                if (this.transportdoc[0].length !== 0) {
                    this.title = 'Редактировать';
                    this.$bvModal.show('modal-form');
                }
                else this.message = 'Выберите документ!';
            },
            addDoc() {
                this.transportdoc = [[]];
                this.title = 'Добавить';
                this.$bvModal.show('modal-form');
            }
        },
    }
</script>

<style scoped>

</style>