<template>
    <div>
        <NavigationBar></NavigationBar>
        <ListItems :is-selectable="true" @selected="onSelectedRow" :table-items="tableItems" :table-fields="tableFields" header="Список сотрудников">
        </ListItems>
        <b-button @click="addEmployee">Добавить</b-button>
        <b-button @click="editEmployee">Редактировать</b-button>
        <b-modal id="modal-form" no-close-on-backdrop hide-footer :title="title + ' запись о сотруднике'">
            <b-form @submit="validateAndSubmit">
                <b-form-group id="input-group-1"
                              label="Фамилия"
                              label-for="input-1">
                    <b-form-input id="input-1"
                                  v-model="employee[0].surname"
                                  type="text"
                                  required
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-2"
                              label="Имя"
                              label-for="input-2">
                    <b-form-input id="input-2"
                                  v-model="employee[0].name"
                                  type="text"
                                  required
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-3"
                              label="Отчество"
                              label-for="input-3">
                    <b-form-input id="input-3"
                                  v-model="employee[0].patronymic"
                                  type="text"
                                  required
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-4"
                              label="Подразделение"
                              label-for="input-4">
                    <b-form-input id="input-4"
                                  v-model="employee[0].department"
                                  type="text"
                                  required
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-5"
                              label="Должность"
                              label-for="input-5">
                    <b-form-input id="input-5"
                                  v-model="employee[0].position"
                                  type="text"
                                  required
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-6"
                              label="Адрес"
                              label-for="input-6">
                    <b-form-input id="input-6"
                                  v-model="employee[0].address"
                                  type="text"
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-7"
                              label="Номер телефона"
                              label-for="input-7">
                    <b-form-input id="input-7"
                                  v-model="employee[0].telephonenumber"
                                  type="text"
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-8"
                              label="Дата приёма"
                              label-for="input-8">
                    <b-form-input id="input-8"
                                  v-model="employee[0].dateofreceipt"
                                  type="date"
                                  required
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-9"
                              label="Дата увольнения"
                              label-for="input-9">
                    <b-form-input id="input-9"
                                  v-model="employee[0].dateofdismissal"
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
    import ListItems from "../components/ListItems";
    import RestAPIService from "../service/RestAPIService";
    export default {
        name: "Employees",
        components: {ListItems, NavigationBar},
        data() {
            return {
                employee: [[]],
                title: '',
                tableItems: [],
                tableFields: [
                    {
                        key: 'surname',
                        label: 'Фамилия',
                        sortable: true
                    },
                    {
                        key: 'name',
                        label: 'Имя',
                        sortable: true
                    },
                    {
                        key: 'patronymic',
                        label: 'Отчество',
                        sortable: true
                    },
                    {
                        key: 'department',
                        label: 'Подразделение',
                        sortable: true
                    },
                    {
                        key: 'position',
                        label: 'Должность',
                        sortable: true
                    },
                    {
                        key: 'address',
                        label: 'Место проживания'

                    },
                    {
                        key: 'telephonenumber',
                        label: 'Номер телефона'
                    },
                    {
                        key: 'dateofreceipt',
                        label: 'Дата приема'
                    },
                    {
                        key: 'dateofdismissal',
                        label: 'Дата увольнения'
                    },
                ],
            }
        },
        methods: {
            refreshTableItems() {
                RestAPIService.readAll("employees")
                    .then(response=>{this.tableItems = response.data})
            },
            validateAndSubmit(e) {
                e.preventDefault();
                if (this.title === "Добавить") {
                    RestAPIService.create("employees",{
                        surname: this.employee[0].surname,
                        name: this.employee[0].name,
                        patronymic: this.employee[0].patronymic,
                        department: this.employee[0].department,
                        position: this.employee[0].position,
                        address: this.employee[0].address,
                        telephonenumber: this.employee[0].telephonenumber,
                        dateofreceipt: this.employee[0].dateofreceipt,
                        dateofdismissal: this.employee[0].dateofdismissal
                    }).then(()=>{
                        this.$bvToast.toast('Информация о сотруднике добавлена!',{autoHideDelay:5000,title:'Транспортная система'});
                        this.$bvModal.hide('modal-form');
                        this.refreshTableItems();
                    })
                }
                else {
                    RestAPIService.update("employees", {
                        id: this.employee[0].id,
                        surname: this.employee[0].surname,
                        name: this.employee[0].name,
                        patronymic: this.employee[0].patronymic,
                        department: this.employee[0].department,
                        position: this.employee[0].position,
                        address: this.employee[0].address,
                        telephonenumber: this.employee[0].telephonenumber,
                        dateofreceipt: this.employee[0].dateofreceipt,
                        dateofdismissal: this.employee[0].dateofdismissal
                    }).then(()=>{
                        this.$bvToast.toast('Информация о сотруднике обновлена!',{autoHideDelay:5000,title:'Транспортная система'});
                        this.$bvModal.hide('modal-form');
                        this.refreshTableItems();
                    })
                }

            },
            onSelectedRow(row) {
                this.employee = row;
            },
            editEmployee() {
                if (this.employee[0].length !== 0) {
                    this.title = 'Редактировать';
                    this.$bvModal.show('modal-form');
                }
                else this.$bvToast.toast('Выберите сотрудника!',{autoHideDelay:5000,title:'Транспортная система'});
            },
            addEmployee() {
                this.employee = [[]];
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

</style>