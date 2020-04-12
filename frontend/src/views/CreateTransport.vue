<template>
    <div>
        <NavigationBar></NavigationBar>
        <h3>Добавить транспорт</h3>
        <b-form @submit="validateAndSubmit">
            <b-form-group id="input-group-1"
                          label="Название"
                          label-for="input-1">
                <b-form-input id="input-1"
                              v-model="transportmodel.name"
                              type="text"
                              required
                              placeholder="">
                </b-form-input>
            </b-form-group>
            <b-form-group id="input-group-2"
                          label="Производитель"
                          label-for="input-2">
                <b-form-input id="input-2"
                              v-model="transportmodel.producer"
                              type="text"
                              required
                              placeholder="">
                </b-form-input>
            </b-form-group>
            <b-form-group id="input-group-3"
                          label="Максимальный вес"
                          label-for="input-3">
                <b-form-input id="input-3"
                              v-model="transportmodel.maxweight"
                              type="text"
                              required>
                </b-form-input>
            </b-form-group>
            <b-form-group id="input-group-4"
                          label="Длина"
                          label-for="input-4">
                <b-form-input id="input-4"
                              v-model="transportmodel.length"
                              type="number"
                              required>
                </b-form-input>
            </b-form-group>
            <b-form-group id="input-group-5"
                          label="Ширина"
                          label-for="input-5">
                <b-form-input id="input-5"
                              v-model="transportmodel.width"
                              type="number"
                              required>
                </b-form-input>
            </b-form-group>
            <b-form-group id="input-group-6"
                          label="Высота"
                          label-for="input-6">
                <b-form-input id="input-6"
                              v-model="transportmodel.height"
                              type="number"
                              required>
                </b-form-input>
            </b-form-group>
            <b-button variant="primary" type="submit">Добавить</b-button>
        </b-form>
    </div>
</template>

<script>
    import NavigationBar from "../components/NavigationBar";
    import RestAPIService from "../service/RestAPIService";
    export default {
        name: "CreateTransport",
        components: {NavigationBar},
        data() {
            return {
                transportmodel: [],
            }
        },
        methods: {
            validateAndSubmit(e){
                e.preventDefault();
                    RestAPIService.create("transportmodels",{
                        name: this.transportmodel.name,
                        producer: this.transportmodel.producer,
                        maxweight: this.transportmodel.maxweight,
                        length: this.transportmodel.length,
                        width: this.transportmodel.width,
                        height: this.transportmodel.height
                    }).then(response => {
                        this.$router.push(`/transports/${response.data.id}`);
                    })
            }
        }
    }
</script>

<style scoped>

</style>