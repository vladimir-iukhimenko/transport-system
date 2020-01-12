<template>
    <div class="container">
    <h2>Двигатели</h2>
        <div v-if="message" class="alert alert-success">
            {{message}}
        </div>
    <div class="container">
    <table class="table">
        <thead>
        <tr>
            <th>Наименование</th>
            <th>Объем</th>
            <th>Топливо</th>
            <th>Модели автомобиля с данным двигаталем</th>
            <th>Действия</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="engine in engines" v-bind:key="engine.id">
            <td>{{engine.name}}</td>
            <td>{{engine.volume}}</td>
            <td>{{engine.fuel}}</td>
            <td>
                <a href="/boundedtransportmodels/">Показать</a>
            </td>
            <td>
                <a href="/engines/edit/">Редактировать</a>
            </td>
            <td>
                <button class="btn btn-warning" v-on:click="deleteEngineClicked(engine.id)">Удалить</button>
            </td>
        </tr>
        </tbody>
    </table>
    </div>
    </div>
</template>

<script>
    import EngineDataService from "../service/EngineDataService";
    export default {
        name: "ListEngines",
        data() {
            return {
                engines: [],
                message: null,
            };
        },
        methods: {
            refreshEngines(){
                EngineDataService.getAllEngines()
                .then(response=>{
                    this.engines = response.data;
                });
            },
            deleteEngineClicked(id){
                EngineDataService.deleteEngine(id)
                .then(response=> {
                    response.status;
                    this.message = `Двигатель удален!`;
                    this.refreshEngines();
                });
            }
        },
        created() {
            this.refreshEngines();
        }
    };
</script>

<style scoped>

</style>