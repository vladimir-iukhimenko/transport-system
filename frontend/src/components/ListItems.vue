<template>
    <div class="container">
        <h2>{{header}}</h2>
        <div>
            <b-table :selectable="isSelectable" select-mode="single" @row-selected="onRowSelected" striped hover :busy="tableItems.length===0" :items="tableItems" :fields="tableFields" responsive="sm">
                <template v-slot:table-busy>
                    <div class="text-center text-danger my-2">
                        <b-spinner class="align-middle"></b-spinner>
                        <strong> Загрузка...</strong>
                    </div>
                </template>
            </b-table>
        </div>
    </div>
</template>

<script>
    export default {
        name: "ListItems",
        props: {
            isSelectable: {
                type: Boolean,
                default: false
            },
            tableItems: {
                type: Array,
                required: true
            },
            tableFields: {
                type: Array,
                required: true
            },
            header: {
                type: String,
                required: false
            }
        },
        data() {
            return {
                selected: [],
            }
        },
        methods: {
            onRowSelected(items) {
                this.$emit("selected",items);
                this.selected = items;
            }
        },
    }
</script>

<style scoped>

</style>