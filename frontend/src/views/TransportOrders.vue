<template>
    <div>
        <NavigationBar></NavigationBar>
        <div v-if="message" class="alert alert-success">
            {{message}}
        </div>
        <ListItems :is-selectable="true" @selected="onSelectedRow" :table-items="tableItems" :table-fields="tableFields" header="Документы транспортных средств">
        </ListItems>
    </div>
</template>

<script>
    import NavigationBar from "../components/NavigationBar";
    import ListItems from "../components/ListItems";
    import RestAPIService from "../service/RestAPIService";
    export default {
        name: "TransportOrders",
        components: {NavigationBar, ListItems},
        data() {
            return {
                tableItems: RestAPIService.readAll("transportorders")
                    .then(response=>{this.tableItems = response.data}),
                tableFields: [
                    {
                        key: 'ordernumber',
                        label: 'Номер заказа'
                    },
                    {
                        key: 'orderdate',
                        label: 'Дата заказа'
                    },
                    {
                        key: 'transportpresentingdate',
                        label: 'Дата выполнения заказа'
                    },
                    {
                        key: 'loadingplace',
                        label: 'Место погрузки'
                    },
                    {
                        key: 'unloadingplace',
                        label: 'Место разгрузки'
                    },
                    {
                        key: 'placemethod',
                        label: 'Метод погрузки'
                    },
                    {
                        key: 'telephonenumber',
                        label: 'Контактный номер'
                    },
                ]
            }
        }
    }
</script>

<style scoped>

</style>