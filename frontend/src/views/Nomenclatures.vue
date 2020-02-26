<template>
    <div>
        <NavigationBar></NavigationBar>
        <b-card no-body>
            <b-tabs card vertical>
                <b-tab v-for="nomenclature in tableItems" :key="'nom-tab' + nomenclature.id" :title="nomenclature.number">
                    <h3>Габариты</h3>
                    <ListItems :table-items="[].concat(nomenclature)" :table-fields="nomenclatureFields"></ListItems>
                    <h3>Грузы</h3>
                    <ListItems :table-items="[].concat(nomenclature.goods)" :table-fields="goodsFields"></ListItems>
                    <b-button @click="openGoods(nomenclature)">Добавить груз</b-button>
                    <h3>Комментарии</h3>
                    <b-list-group>
                        <b-list-group-item v-for="comment in nomenclature.comments.allComments" :key="comment.commentid">
                            {{comment}}
                        </b-list-group-item>
                    </b-list-group>
                    <b-button @click="openComment(nomenclature)">Добавить комментарий</b-button>
                </b-tab>
                <b-modal id="comment" hide-footer title="Добавить комментарий">
                    <b-form-textarea id="textarea" v-model="comment" rows="3"></b-form-textarea>
                    <b-button @click="addComment">Добавить</b-button>
                </b-modal>
                <b-modal id="goods" hide-footer title="Добавить груз">
                    <b-form @submit="validateAndSubmit">
                        <b-form-group id="input-group-1"
                                      label="Наименование"
                                      label-for="input-1">
                            <b-form-input id="input-1"
                                          v-model="goods.name"
                                          type="text"
                                          required
                                          placeholder="">
                            </b-form-input>
                        </b-form-group>
                        <b-form-group id="input-group-2"
                                      label="Количество"
                                      label-for="input-2">
                            <b-form-input id="input-2"
                                          v-model="goods.amount"
                                          type="text"
                                          required
                                          placeholder="">
                            </b-form-input>
                        </b-form-group>
                        <b-form-group id="input-group-3"
                                      label="Длина"
                                      label-for="input-3">
                            <b-form-input id="input-3"
                                          v-model="goods.length"
                                          type="text"
                                          required
                                          placeholder="">
                            </b-form-input>
                        </b-form-group>
                        <b-form-group id="input-group-4"
                                      label="Высота"
                                      label-for="input-4">
                            <b-form-input id="input-4"
                                          v-model="goods.height"
                                          type="text"
                                          required
                                          placeholder="">
                            </b-form-input>
                        </b-form-group>
                        <b-form-group id="input-group-5"
                                      label="Масса"
                                      label-for="input-5">
                            <b-form-input id="input-5"
                                          v-model="goods.weight"
                                          type="text"
                                          required
                                          placeholder="">
                            </b-form-input>
                        </b-form-group>
                        <b-button class="float-right" variant="primary" type="submit">Добавить</b-button>
                    </b-form>
                </b-modal>
            </b-tabs>
        </b-card>
    </div>
</template>

<script>
    import NavigationBar from "../components/NavigationBar";
    import RestAPIService from "../service/RestAPIService";
    import ListItems from "../components/ListItems";
    export default {
        name: "Nomenclatures",
        components: {ListItems, NavigationBar},
        data() {
            return {
                comment: '',
                nomenclature: [],
                tableItems: [],
                goods: [],
                nomenclatureFields: [
                    {
                        key: 'length',
                        label: 'Длина'
                    },
                    {
                        key: 'width',
                        label: 'Ширина'
                    },
                    {
                        key: 'height',
                        label: 'Высота'
                    },
                    {
                        key: 'weight',
                        label: 'Вес'
                    }
                ],
                goodsFields: [
                    {
                        key: 'name',
                        label: 'Наименование груза'
                    },
                    {
                        key: 'amount',
                        label: 'Количество'
                    },
                    {
                        key: 'length',
                        label: 'Длина'
                    },
                    {
                        key: 'height',
                        label: 'Высота'
                    },
                    {
                        key: 'weight',
                        label: 'Масса'
                    }
                ]
            }
        },
        methods:{
            refreshTableItems() {
                RestAPIService.readAll("nomenclatures")
                    .then(response=>{this.tableItems = response.data});
            },
            addComment() {
                RestAPIService.update("nomenclatures",{
                    id: this.nomenclature.id,
                    number: this.nomenclature.number,
                    length: this.nomenclature.length,
                    width: this.nomenclature.width,
                    height: this.nomenclature.height,
                    weight: this.nomenclature.weight,
                    comment: this.comment,
                    goods: this.nomenclature.goods
                }).then(()=>{
                    this.$bvModal.hide("comment");
                    this.refreshTableItems();
                })
            },
            openComment(nomenclature) {
                this.nomenclature = nomenclature;
                this.$bvModal.show("comment");
            },
            openGoods(nomenclature) {
                this.nomenclature = nomenclature;
                this.$bvModal.show("goods");
            },
            validateAndSubmit(e) {
                e.preventDefault();
                RestAPIService.create("goods", {
                    nomenclatureid: this.nomenclature.id,
                    name: this.goods.name,
                    amount: this.goods.amount,
                    length: this.goods.length,
                    height: this.goods.height,
                    weight: this.goods.weight
                }).then(()=>{
                    this.$bvModal.hide("goods");
                    this.refreshTableItems();
                })
            }
        },
        created() {
            this.refreshTableItems();
        }
    };
</script>

<style scoped>

</style>