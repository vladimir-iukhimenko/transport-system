<template>
        <div>
            <h1 v-if="id !== 'add'">
                Редактировать двигатель</h1>
            <h1 v-else>Добавить двигатель</h1>
    <div class="container">
    <form @submit="validateAndSubmit">
        <fieldset class="form-group">
        <label>Наименование</label>
            <input type="text" class="form-control" v-model="engine.name">
        </fieldset>
        <fieldset class="form-group">
        <label>Объем</label>
            <input type="text" class="form-control" v-model="engine.volume">
        </fieldset>
        <fieldset class="form-group">
        <label>Топливо</label>
            <input type="text" class="form-control" v-model="engine.fuel">
        </fieldset>
        <button class="btn btn-success" type="submit" v-if="id !== 'add'">Редактировать</button>
        <button class="btn btn-success" type="submit" v-else>Добавить</button>
    </form>
    </div>
        </div>
</template>

<script>
    import RestAPIService from "../service/RestAPIService";

    export default {
        name: "Engine",
        data(){
            return{
                engine: [],
                errors: []
            };
        },
        computed: {
            id() {
                return this.$route.params.id;
            }
        },
        methods: {
            getEngineDetails() {
                RestAPIService.retrieve(this.id,"engines").then(resource => {
                    this.engine = resource.data
                });
            },
            validateAndSubmit(e) {
                e.preventDefault();
                this.errors=[];
                if (this.id !== "add") {
                    RestAPIService.update("engines",{
                        id: this.id,
                        name: this.engine.name,
                        volume: this.engine.volume,
                        fuel: this.engine.fuel
                    }).then(()=> {
                        this.$router.push(`/engines`);
                    });
                }
                else {
                    RestAPIService.create("engines",{
                        name: this.engine.name,
                        volume: this.engine.volume,
                        fuel: this.engine.fuel
                    }).then(()=> {
                        this.$router.push(`/engines`);
                    });
                }
            }
        },
        created() {
            if(this.id !== "add") {this.getEngineDetails();}

        }
    };
</script>

<style scoped>

</style>