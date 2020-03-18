<template>
    <div class="container">
        <h2>{{header}}</h2>
        <div>
            <b-pagination
                    v-show="rows>5"
                    v-model="currentPage"
                    :total-rows="rows"
                    :per-page="perPage"
                    aria-controls="listTable">
            </b-pagination>
            <b-table id="listTable" :per-page="perPage" :current-page="currentPage"
                     :selectable="isSelectable" select-mode="single" @row-selected="onRowSelected"
                     striped hover
                     :busy="isBusy" :items="tableItems" :fields="tableFields" responsive="sm">
                <template v-slot:table-busy>
                    <div class="text-center text-danger my-2">
                        <b-spinner class="align-middle"></b-spinner>
                        <strong> Загрузка...</strong>
                    </div>
                </template>
            </b-table>
            <div class="form-group">
                <div v-if="!isBusy && tableItems.length===0" class="alert alert-danger" role="alert">{{message}}</div>
            </div>
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
            },
            isBusy: {
                type: Boolean,
                default: true
            },
            message: {
                type: String
            },
        },
        data() {
            return {
                selected: [],
                perPage: 5,
                currentPage: 1,
            }
        },
        methods: {
            onRowSelected(items) {
                this.$emit("selected",items);
                this.selected = items;
            }
        },
        computed: {
            rows() {
                return this.tableItems.length;
            }
        }
    }
</script>

<style scoped>

</style>