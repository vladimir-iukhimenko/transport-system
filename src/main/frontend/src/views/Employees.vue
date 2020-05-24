<template>
    <div>
        <NavigationBar></NavigationBar>
        <ListItems :is-selectable="true" :is-busy="isBusy" @selected="onSelectedRow" :table-items="tableItems" :table-fields="tableFields" :message="message" header="Список сотрудников">
        </ListItems>
        <div>
            <b-button class="buttons col-sm-2" @click="addEmployee">Добавить</b-button>
            <b-button v-show="tableItems.length !== 0" class="buttons col-sm-2" @click="editEmployee">Редактировать</b-button>
            <b-button v-show="tableItems.length !== 0" class="buttons col-sm-2" @click="showEmployeeDocs">Документы</b-button>
            <b-button v-show="tableItems.length !== 0" class="buttons col-sm-2" @click="deleteEmployee">Удалить</b-button>
        </div>
        <b-modal id="modal-form" no-close-on-backdrop hide-footer :title="title + ' запись о сотруднике'">
            <b-form @submit="validateAndSubmit">
                <b-form-group id="input-group-1"
                              label="Фамилия"
                              label-for="input-1">
                    <b-form-input id="input-1"
                                  v-model="employee.surname"
                                  type="text"
                                  required
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-2"
                              label="Имя"
                              label-for="input-2">
                    <b-form-input id="input-2"
                                  v-model="employee.name"
                                  type="text"
                                  required
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-3"
                              label="Отчество"
                              label-for="input-3">
                    <b-form-input id="input-3"
                                  v-model="employee.patronymic"
                                  type="text"
                                  required
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-4"
                              label="Подразделение"
                              label-for="input-4">
                    <b-form-input id="input-4"
                                  v-model="employee.department"
                                  type="text"
                                  required
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-5"
                              label="Должность"
                              label-for="input-5">
                    <b-form-input id="input-5"
                                  v-model="employee.position"
                                  type="text"
                                  required
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-6"
                              label="Адрес"
                              label-for="input-6">
                    <b-form-input id="input-6"
                                  v-model="employee.address"
                                  type="text"
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-7"
                              label="Номер телефона"
                              label-for="input-7">
                    <b-form-input id="input-7"
                                  v-model="employee.telephonenumber"
                                  type="text"
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-8"
                              label="Дата приёма"
                              label-for="input-8">
                    <b-form-input id="input-8"
                                  v-model="employee.dateofreceipt"
                                  type="date"
                                  required
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-9"
                              label="Дата увольнения"
                              label-for="input-9">
                    <b-form-input id="input-9"
                                  v-model="employee.dateofdismissal"
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
    import Employee from "../models/employee";
    export default {
        name: "Employees",
        components: {ListItems, NavigationBar},
        data() {
            return {
                employee: new Employee(),
                title: '',
                tableItems: [],
                isBusy: false,
                message: '',
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
                this.isBusy = !this.isBusy;
                RestAPIService.readAll("employees")
                    .then( response => {
                        this.tableItems = response.data;
                        this.isBusy = !this.isBusy;
                        if (this.tableItems.length === 0) this.message = 'Сотрудники не найдены!';
                    }).catch(err => {
                        this.isBusy = !this.isBusy;
                        this.message = err.message;
                })
            },
            validateAndSubmit(e) {
                e.preventDefault();
                if (this.title === "Добавить") {
                    RestAPIService.create("employees",this.employee).then(()=>{
                        this.$bvToast.toast('Информация о сотруднике добавлена!',{autoHideDelay:5000,title:'Транспортная система'});
                        this.$bvModal.hide('modal-form');
                        this.refreshTableItems();
                    })
                }
                else {
                    RestAPIService.update("employees", this.employee).then(()=>{
                        this.$bvToast.toast('Информация о сотруднике обновлена!',{autoHideDelay:5000,title:'Транспортная система'});
                        this.$bvModal.hide('modal-form');
                        this.refreshTableItems();
                    })
                }
            },
            onSelectedRow(row) {
                this.employee = row[0] ? row[0] : row;
            },
            editEmployee() {
                if (this.employee.id) {
                    this.title = 'Редактировать';
                    this.$bvModal.show('modal-form');
                }
                else this.$bvToast.toast('Выберите сотрудника!',{autoHideDelay:5000,title:'Транспортная система'});
            },
            deleteEmployee() {
                if (this.employee.id) {
                    this.$bvModal.msgBoxConfirm(
                        `Вы действительно хотите удалить сотрудника
                         ${this.employee.surname + ' ' + this.employee.name} ?`,{
                            title: 'Подтверждение',
                            okTitle: 'Да',
                            okVariant: 'danger',
                            cancelTitle: 'Нет',
                        }).then(value => {
                            if (value === true) {
                                RestAPIService.delete(this.employee.id,"employees").then(
                                    () => {
                                        this.$bvToast.toast('Выбранный сотрудник удален!',{autoHideDelay: 5000, title: 'Транспортная система'});
                                        this.refreshTableItems();
                                    }
                                );
                            }
                    })

                }
                else this.$bvToast.toast('Выберите сотрудника!',{autoHideDelay:5000, title: 'Транспортная система'})
            },
            addEmployee() {
                this.employee = new Employee;
                this.title = 'Добавить';
                this.$bvModal.show('modal-form');
            },
            showEmployeeDocs() {
                if (this.employee.id) {
                    this.$router.push(`/employees/docs/${this.employee.id}`);
                }
                else this.$bvToast.toast('Выберите сотрудника!', {autoHideDelay:5000, title: 'Транспортная система'})
            }
        },
        created() {
            this.refreshTableItems();
        }
    }
</script>

<style scoped>
    .buttons {
        margin: 45px;
        padding: 5px;
    }

</style>