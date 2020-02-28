<template>
    <div>
        <NavigationBar></NavigationBar>
        <b-modal id="selection" no-close-on-backdrop hide-footer title="Выберите сотрудника">
            <b-form @submit="openDocs">
                <b-form-group id="input-group-1"
                              label="Сотрудник"
                              label-for="input-1">
                    <b-form-select id="input-1" v-model="employeeId">
                        <b-form-select-option v-for="employee in employees" :key="employee.id" :value="employee.id">{{employee.name + " " + employee.surname + " - " + employee.department}}</b-form-select-option>
                    </b-form-select>
                </b-form-group>
                <b-button variant="primary" type="submit">Перейти</b-button>
            </b-form>
        </b-modal>
    </div>
</template>

<script>
    import RestAPIService from "../service/RestAPIService";
    import NavigationBar from "./NavigationBar";

    export default {
        name: "EmployeeSelection",
        components: {NavigationBar},
        data() {
            return {
                employeeId: '',
                employees: []
            };
        },
        methods: {
            openDocs(e) {
                e.preventDefault();
                this.$router.push(`/employees/docs/${this.employeeId}`);
            }
        },
        mounted() {
            this.$bvModal.show('selection');
            RestAPIService.readAll("employees")
                .then(response => {this.employees = response.data});
            this.$root.$on('bv::modal::hide', (modalId) => {if (modalId.componentId === "selection") this.$router.push(`/employees`)})
        }
    }
</script>

<style scoped>

</style>