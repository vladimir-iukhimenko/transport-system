<template>
    <div class="container">
        <h2>Сотрудники</h2>
        <div v-if="message" class="alert alert-success">
            {{message}}
        </div>
        <div>
            <b-table striped hover :busy="employees.length===0" :items="employees" :fields="employee_fields" responsive="sm">
                <template v-slot:table-busy>
                    <div class="text-center text-danger my-2">
                        <b-spinner class="align-middle"></b-spinner>
                        <strong>Загрузка...</strong>
                    </div>
                </template>
            </b-table>
        </div>
    </div>
</template>

<script>
    import RestAPIService from "../service/RestAPIService";
    export default {
        name: "ListEmployees",
        data() {
            return{
                employee_fields:[
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
                employees: [],
                message: null,
            };
        },
        methods: {
            refreshEmployees(){
                RestAPIService.readAll("employees")
                .then(response=>{
                    this.employees = response.data;
                });
            },
            deleteEmployeeClicked(id){
                RestAPIService.delete(id,"employees")
                .then(()=>{
                    this.message = "Информация о сотруднике удалена!";
                    this.refreshEmployees();
                });
            },
            editEmployeeClicked(id){
                this.$router.push(`/employees/edit/${id}`)
            },
            addEmployeeClicked(){
                this.$router.push(`/employees/add`)
            },
        },
        created() {
            this.refreshEmployees();
        }
    };
</script>

<style scoped>

</style>