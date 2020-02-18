<template>
    <div>
        <b-dropdown id="dropdown-1" text="Транспортные средства" class="m-md-2">
            <b-dropdown-item ><router-link to="/transports">Парк ТС</router-link></b-dropdown-item>
            <b-dropdown-item><router-link to="/engines">Двигатели</router-link></b-dropdown-item>
            <b-dropdown-item><router-link to="/transports/documents">Документы</router-link></b-dropdown-item>
        </b-dropdown>
        <b-dropdown id="dropdown-2" text="Сотрудники" class="m-md-2">
            <b-dropdown-item><router-link to="/employees">Список сотрудников</router-link></b-dropdown-item>
            <b-dropdown-item><router-link to="/employees/documents">Документы</router-link></b-dropdown-item>
        </b-dropdown>
        <b-dropdown id="dropdown-3" text="Транспортные заказы" class="m-md-2">
            <b-dropdown-item><router-link to="/transportorders">Заказы</router-link></b-dropdown-item>
        </b-dropdown>
    </div>
</template>

<script>
    export default {
        name: "MainApp",
        methods: {

        }
    };

</script>

<style scoped>

</style>