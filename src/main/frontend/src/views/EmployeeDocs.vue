<template>
    <div>
        <NavigationBar></NavigationBar>
        <ListItems :is-selectable="true"
                   @selected="onSelectedRow"
                   header="Документы"
                   :table-fields="tableFields"
                   :table-items="tableItems"
                   :is-busy="false" message="Не найдены документы!"></ListItems>
        <div class="text-center">
        <b-button class="buttons col-sm-2" @click="addEmployeeDoc">Добавить</b-button>
        <b-button class="buttons col-sm-2" v-show="tableItems.length!=0" @click="editEmployeeDoc">Редактировать</b-button>
        <b-button class="buttons col-sm-2" v-show="tableItems.length!=0" @click="deleteEmployeeDoc">Удалить</b-button>
        </div>
        <b-modal id="modal-form" no-close-on-backdrop hide-footer :title="title + ' документ'">
            <b-form @submit="validateAndSubmit">
                <b-form-group id="input-group-1"
                              label="Тип документа"
                              label-for="input-1">
                    <b-form-input id="input-1"
                                  v-model="document[0].documenttype"
                                  type="text"
                                  required
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-2"
                              label="Серия"
                              label-for="input-2">
                    <b-form-input id="input-2"
                                  v-model="document[0].series"
                                  type="text"
                                  required
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-3"
                              label="Номер"
                              label-for="input-3">
                    <b-form-input id="input-3"
                                  v-model="document[0].number"
                                  type="text"
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-4"
                              label="Выпущен"
                              label-for="input-4">
                    <b-form-input id="input-4"
                                  v-model="document[0].issuedby"
                                  type="text"
                                  required
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-5"
                              label="Дата выпуска"
                              label-for="input-5">
                    <b-form-input id="input-5"
                                  v-model="document[0].issueddate"
                                  type="date"
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-button class="float-right" variant="primary" type="submit">{{title}}</b-button>
            </b-form>
        </b-modal>
    </div>
</template>

<script>
    import NavigationBar from "../components/NavigationBar";
    import RestAPIService from "../service/RestAPIService";
    import ListItems from "../components/ListItems";
    export default {
        name: "EmployeeDocs",
        components: {ListItems, NavigationBar},
        data() {
            return {
                document: [[]],
                title: '',
                tableItems: [],
                tableFields: [
                    {
                        key: 'documenttype',
                        label: 'Тип документа',
                        sortable: true
                    },
                    {
                        key: 'series',
                        label: 'Серия',
                    },
                    {
                        key: 'number',
                        label: 'Номер',
                    },
                    {
                        key: 'issuedby',
                        label: 'Выпущено',
                    },
                    {
                        key: 'issueddate',
                        label: 'Дата выпуска',
                    }
                ],

            }
        },
        computed: {
            id() {
                return this.$route.params.id;
            }
        },
        created() {
            this.refreshTableItems();
        },
        methods: {
            refreshTableItems() {
                RestAPIService.retrieve(this.id,"employees")
                    .then(response=>{
                        this.tableItems = response.data.employeedocuments;
                    });
            },
            validateAndSubmit(e) {
                e.preventDefault();
                if (this.title === "Добавить") {
                    RestAPIService.create("employeedocs",{
                        documenttype: this.document[0].documenttype,
                        series: this.document[0].series,
                        number: this.document[0].number,
                        issuedby: this.document[0].issuedby,
                        issueddate: this.document[0].issueddate,
                        employeeid: this.id
                    }).then(()=>{
                        this.$bvToast.toast('Документ добавлен!',{autoHideDelay:5000,title:'Транспортная система'});
                        this.$bvModal.hide('modal-form');
                        this.refreshTableItems();
                    })
                }
                else {
                    RestAPIService.update("employeedocs", {
                        id: this.document[0].id,
                        documenttype: this.document[0].documenttype,
                        series: this.document[0].series,
                        number: this.document[0].number,
                        issuedby: this.document[0].issuedby,
                        issueddate: this.document[0].issueddate,
                        employeeid: this.id
                    }).then(()=>{
                        this.$bvToast.toast('Информация о документе обновлена!',{autoHideDelay:5000,title:'Транспортная система'});
                        this.$bvModal.hide('modal-form');
                        this.refreshTableItems();
                    })
                }

            },
            onSelectedRow(row) {
                this.document = row;
            },
            editEmployeeDoc() {
                if (this.document[0].length !== 0) {
                    this.title = 'Редактировать';
                    this.$bvModal.show('modal-form');
                }
                else this.$bvToast.toast('Выберите документ!',{autoHideDelay:5000,title:'Транспортная система'});
            },
            deleteEmployeeDoc() {
                if (this.document[0].length !== 0) {
                    RestAPIService.delete(this.document[0].id,"employeedocs").then(() => {this.refreshTableItems();});
                    this.$bvToast.toast('Выбранный документ удален!',{autoHideDelay: 5000, title: 'Транспортная система'});
                }
                else this.$bvToast.toast('Выберите документ!',{autoHideDelay:5000, title: 'Транспортная система'})
            },
            addEmployeeDoc() {
                this.document = [[]];
                this.title = 'Добавить';
                this.$bvModal.show('modal-form');
            },

        }
    }
</script>

<style scoped>

    .buttons {
        margin: 25px;
        padding: 5px;
    }

</style>