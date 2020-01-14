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
                <button class="btn btn-success" v-on:click="editEngineClicked(engine.id)">Редактировать</button>
            </td>
            <td>
                <button class="btn btn-warning" v-on:click="deleteEngineClicked(engine.id)">Удалить</button>
            </td>
        </tr>
        </tbody>
    </table>
        <div class="row">
            <button class="btn btn btn-success" v-on:click="addEngineClicked()">Добавить двигатель</button>
        </div>
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
                .then(()=> {
                    this.message = `Двигатель удален!`;
                    this.refreshEngines();
                });
            },
            editEngineClicked(id){
                this.$router.push(`/engines/${id}`)
            },
            addEngineClicked(){
                this.$router.push(`/engines/add`)
            }
        },
        created() {
            this.refreshEngines();
        }
    };
</script>

<style scoped>

</style>