<template>
    <div>
        <NavigationBar></NavigationBar>
        <div v-if="message" class="alert alert-success">
            {{message}}
        </div>
        <ListItems :is-selectable="true" @selected="onSelectedRow" :table-items="tableItems" :table-fields="tableFields" header="Двигатели">
        </ListItems>
        <b-button @click="addEngine">Добавить</b-button>
        <b-button @click="editEngine">Редактировать</b-button>
        <b-modal id="modal-form" no-close-on-backdrop hide-footer :title="title + ' двигатель'">
            <b-form @submit="validateAndSubmit">
                <b-form-group id="input-group-1"
                              label="Наименование"
                              label-for="input-1">
                    <b-form-input id="input-1"
                                  v-model="engine[0].name"
                                  type="text"
                                  required
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-2"
                              label="Объем"
                              label-for="input-2">
                    <b-form-input id="input-2"
                                  v-model="engine[0].volume"
                                  type="text"
                                  required
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-3"
                              label="Топливо"
                              label-for="input-3">
                    <b-form-input id="input-3"
                                  v-model="engine[0].fuel"
                                  type="text"
                                  required
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-button class="float-right" variant="primary" type="submit">{{title}}</b-button>
            </b-form>
        </b-modal>
    </div>
</template>

<script>
    import NavigationBar from "../components/NavigationBar";
    import ListItems from "../components/ListItems";
    import RestAPIService from "../service/RestAPIService";
    export default {
        name: "Engines",
        components: {ListItems, NavigationBar},
        data() {
            return {
                engine: [[]],
                message: null,
                title: '',
                tableItems: RestAPIService.readAll("engines")
                    .then(response=>{this.tableItems = response.data}),
                tableFields: [
                    {
                        key: 'name',
                        label: 'Наименование'
                    },
                    {
                        key: 'volume',
                        label: 'Объем двигателя'
                    },
                    {
                        key: 'fuel',
                        label: 'Топливо'
                    },
                ]
            }
        },
        methods: {
            validateAndSubmit(e) {
                e.preventDefault();
                if (this.title === "Добавить") {
                    RestAPIService.create("engines",{
                        name: this.engine[0].name,
                        volume: this.engine[0].volume,
                        fuel: this.engine[0].fuel
                    }).then(()=>{
                        this.message = 'Информация о двигателе добавлена!';
                        this.$bvModal.hide('modal-form')})
                }
                else {
                    RestAPIService.update("engines", {
                        id: this.engine[0].id,
                        name: this.engine[0].name,
                        volume: this.engine[0].volume,
                        fuel: this.engine[0].fuel,
                    }).then(()=>{
                        this.message = 'Информация о двигателе обновлена!';
                        this.$bvModal.hide('modal-form')
                    })
                }

            },
            onSelectedRow(row) {
                this.engine = row;
            },
            editEngine() {
                if (this.engine[0].length !== 0) {
                    this.title = 'Редактировать';
                    this.$bvModal.show('modal-form');
                }
                else this.message = 'Выберите двигатель!';
            },
            addEngine() {
                this.engine = [[]];
                this.title = 'Добавить';
                this.$bvModal.show('modal-form');
            }
        },
    }
</script>

<style scoped>

</style>