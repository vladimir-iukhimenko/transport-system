<template>
    <div>
        <NavigationBar @search="searchItems"></NavigationBar>
        <b-container>
            <b-row>
                <b-col>
                    <SideMenu></SideMenu>
                </b-col>
                <b-col cols="8"><ListItems :is-busy="isBusy" :table-items="tableItems" :table-fields="tableFields" :message="message" header="Транспортные заказы"></ListItems></b-col>
            </b-row>
        </b-container>
    </div>
</template>

<script>
    import NavigationBar from "../components/NavigationBar";
    import SideMenu from "../components/SideMenu";
    import ListItems from "../components/ListItems";
    import RestAPIService from "../service/RestAPIService";
    export default {
        name: "MainPage",
        components: {
          NavigationBar,SideMenu,ListItems
        },
        data() {
            return {
                isBusy: false,
                message: '',
                tableItems: [],
                tableFields:[
                {
                    key: 'ordernumber',
                    label: 'Номер заказа'
                },
                {
                    key: 'orderdate',
                    label: 'Дата заказа'
                },
            ],
            }
        },
        methods: {
            refreshTableItems() {
                this.isBusy = !this.isBusy;
                RestAPIService.readAll("transportorders")
                    .then( response => {
                        this.tableItems = response.data;
                        this.isBusy = !this.isBusy;
                        if (this.tableItems.length === 0) this.message = 'Транспортные заказы не найдены!';
                    }).catch(err => {
                    this.isBusy = !this.isBusy;
                    this.message = err.message;
                })
            },
            searchItems(object) {
                RestAPIService.search({
                    query: object.query,
                    context: object.context,
                }).then(response => {this.tableItems = response.data});
            }
        },
        created() {
            this.refreshTableItems();
        }
    }
</script>

<style scoped>

</style>