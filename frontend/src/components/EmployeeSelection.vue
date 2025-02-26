<template>
    <div>
        <b-modal id="employeeSelection" no-close-on-backdrop hide-footer title="Выберите сотрудника">
            <b-form @submit.prevent="pushEmployeeId">
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

    export default {
        name: "EmployeeSelection",
        components: {},
        data() {
            return {
                employeeId: '',
                employees: []
            };
        },
        props: {
            isShown: {
                type: Boolean,
                default: false,
            }
        },
        methods: {
            pushEmployeeId() {
                this.$emit("getSelectedEmployeeId",this.employeeId);
            }
        },
        mounted() {
            RestAPIService.readAll("employees")
                .then(response => {this.employees = response.data});
            },
        watch: {
            isShown: function () {
                if (this.isShown === true) this.$bvModal.show("employeeSelection");
                else this.$bvModal.hide("employeeSelection");
            }
        }
    }
</script>

<style scoped>

</style>