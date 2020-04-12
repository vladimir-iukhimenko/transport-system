<template>
    <div role="tablist">
        <b-card no-body class="mb-1">
            <b-card-header header-tag="header" class="p-1" role="tab">
                <b-button block href="#" v-b-toggle.accordion-1 variant="info">Транспортные средства</b-button>
            </b-card-header>
            <b-collapse id="accordion-1" visible accordion="my-accordion" role="tabpanel">
                <b-card-body>
                    <p><router-link to="/transports">Список транспортных средств</router-link></p>
                    <p><router-link to="/transports/add">Добавить транспорт</router-link></p>
                    <p><router-link to="/transportdocs">Документы транспортных средств</router-link></p>
                    <p><router-link to="/engines">Двигатели</router-link></p>
                </b-card-body>
            </b-collapse>
        </b-card>
        <b-card no-body class="mb-1">
            <b-card-header header-tag="header" class="p-1" role="tab">
                <b-button block href="#" v-b-toggle.accordion-2 variant="info">Заказы</b-button>
            </b-card-header>
            <b-collapse id="accordion-2" accordion="my-accordion" role="tabpanel">
                <b-card-body>
                    <p><router-link to="/transportorders">Список транспортных заказов</router-link></p>
                    <p><router-link to="/transportorders/create">Новый транспортный заказ</router-link></p>
                </b-card-body>
            </b-collapse>
        </b-card>
        <b-card no-body class="mb-1">
            <b-card-header header-tag="header" class="p-1" role="tab">
                <b-button block href="#" v-b-toggle.accordion-3 variant="info">Грузы</b-button>
            </b-card-header>
            <b-collapse id="accordion-3" accordion="my-accordion" role="tabpanel">
                <b-card-body>
                    <p><router-link to="/nomenclatures">Номенклатуры грузов</router-link></p>
                </b-card-body>
            </b-collapse>
        </b-card>
        <b-card no-body class="mb-1">
            <b-card-header header-tag="header" class="p-1" role="tab">
                <b-button block href="#" v-b-toggle.accordion-4 variant="info">Сотрудники</b-button>
            </b-card-header>
            <b-collapse id="accordion-4" accordion="my-accordion" role="tabpanel">
                <b-card-body>
                    <p><router-link to="/employees">Список сотрудников</router-link></p>
                    <p><b-link @click="showEmployeeSelection = true">Документы сотрудников</b-link></p>
                </b-card-body>
            </b-collapse>
        </b-card>
        <EmployeeSelection :is-shown="showEmployeeSelection" @getSelectedEmployeeId="openEmployeeDocs"></EmployeeSelection>
    </div>
</template>

<script>
    import EmployeeSelection from "./EmployeeSelection";
    export default {
        name: "SideMenu",
        components: {EmployeeSelection},
        data() {
            return {
                showEmployeeSelection: false,
            }
        },
        methods: {
            openEmployeeDocs(id) {
                this.$router.push(`/employees/docs/${id}`);
            }
        }
    }
</script>

<style scoped>

</style>