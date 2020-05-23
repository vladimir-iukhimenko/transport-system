<template>
    <div>
        <NavigationBar></NavigationBar>
        <b-container>
            <b-row>
                <b-col>
                    <b-button style="float: right;" variant="outline-primary" @click="openNewUserForm">Добавить</b-button>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Логин</th>
                                <th scope="col">Сотрудник</th>
                                <th scope="col">Активен</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="(tableItem,index) in tableItems" :key="index">
                                <td>{{index+1}}</td>
                                <td>{{tableItem.username}}</td>
                                <td><b-link @click="onEmployeeSelectionModal(tableItem)">
                                    {{tableItem.employee !== null ? tableItem.employee.name + ' ' + tableItem.employee.surname : "Связать с сотрудником"}}</b-link></td>
                                <td><b-form-checkbox switch v-model="tableItem.enabled" @change="changeEnabledStatus(tableItem)"></b-form-checkbox></td>
                                <td><b-button size="sm" variant="outline-primary" @click="onOpenModal(tableItem)">Роли</b-button>
                                <b-button size="sm" variant="outline-danger" @click="deleteUser(tableItem)">Удалить</b-button> </td>
                            </tr>
                        <p>{{user}}</p>
                        </tbody>
                    </table>
                </b-col>
            </b-row>
        </b-container>
        <b-modal id="user-roles" no-close-on-backdrop hide-footer>
            <template v-slot:modal-header>
                <h5>Роли</h5>
                <b-button size="sm" variant="outline-danger" @click="onCloseModal">Подтвердить</b-button>
            </template>
            <b-form-group label="Выбрать роли для пользователя:">
                <b-form-checkbox-group v-model="selectedRoles">
                    <b-form-checkbox value="ROLE_USER">Сотрудник</b-form-checkbox>
                    <b-form-checkbox value="ROLE_MODERATOR">Модератор</b-form-checkbox>
                    <b-form-checkbox value="ROLE_ADMIN">Администратор</b-form-checkbox>
                </b-form-checkbox-group>
            </b-form-group>
        </b-modal>
        <b-modal id="new-user" hide-footer>
            <template v-slot:modal-header>
                <h5>Новый пользователь</h5>
                <b-button size="sm" variant="outline-danger" @click="createUser">Добавить</b-button>
            </template>
            <b-form @submit.prevent="createUser">
                <b-form-group id="input-group-1"
                              label="UserID"
                              label-for="input-1">
                    <b-form-input id="input-1"
                                  v-model="user.username"
                                  type="text"
                                  required
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-2"
                              label="Пароль"
                              label-for="input-2">
                    <b-form-input id="input-2"
                                  v-model="user.password"
                                  type="password"
                                  required
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
            </b-form>
        </b-modal>
        <EmployeeSelection :is-shown="showEmployeeSelection" @getSelectedEmployeeId="changeEmployee"></EmployeeSelection>
    </div>
</template>

<script>
    import NavigationBar from "../components/NavigationBar";
    import RestAPIService from "../service/RestAPIService";
    import EmployeeSelection from "../components/EmployeeSelection";
    export default {
        name: "Admin",
        components: {EmployeeSelection, NavigationBar},
        data() {
            return {
                tableItems: [],
                employeeData: '',
                user: {},
                selectedRoles: [],
                showEmployeeSelection: false,
            }
        },
        methods: {
            refreshEmployeesdata() {
                RestAPIService.readAll("auth/users").then(response => {this.tableItems = response.data;})
            },
            changeUserState(user,isRoleChanged) {
                RestAPIService.update("admin", {
                    username: this.user.username,
                    enabled: this.user.enabled,
                    employeeid: this.user.employee ? this.user.employee.id : null,
                    roles: isRoleChanged ? this.selectedRoles : this.user.roles,
                })
            },
            onOpenModal(user) {
                this.user = user;
                this.$bvModal.show("user-roles");
            },
            onCloseModal() {
                this.$bvModal.hide("user-roles");
                this.changeUserState(this.user,true);
            },
            onEmployeeSelectionModal(user) {
                this.user = user;
                this.showEmployeeSelection = true;
            },
            changeEmployee(id) {
                RestAPIService.retrieve(id,"employees").then(
                    response => {
                        this.user.employee = response.data;
                        this.changeUserState(this.user, false);
                    }
                );
                this.showEmployeeSelection = false;
            },
            changeEnabledStatus(user) {
                this.user = user;
                this.user.enabled = !this.user.enabled;
                this.changeUserState(this.user,false)
            },
            deleteUser(user) {
                RestAPIService.delete(user.id,"admin")
                this.refreshEmployeesdata();
            },
            openNewUserForm() {
                this.user = {};
                this.$bvModal.show("new-user");
            },
            createUser() {
                this.$store.dispatch('auth/register', this.user);
                this.$bvModal.hide("new-user");
                this.refreshEmployeesdata();
            }
        },
        created() {
            this.refreshEmployeesdata();
        }

    }
</script>

<style scoped>
    .table {
        table-layout: fixed;
        width: 100%;
    }
</style>