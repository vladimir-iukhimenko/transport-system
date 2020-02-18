<template>
    <div>
        <NavigationBar></NavigationBar>
        <b-container>
            <b-row>
                <b-col>
                    <SideMenu></SideMenu>
                </b-col>
                <b-col cols="8"><ListItems :table-items="tableItems" :table-fields="tableFields" header="Транспортные заказы"></ListItems></b-col>
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
                tableItems: RestAPIService.readAll('transportorders')
                    .then(response=>{this.tableItems = response.data}),
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
        }
    }
</script>

<style scoped>

</style>