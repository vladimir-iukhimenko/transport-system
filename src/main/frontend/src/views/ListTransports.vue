<template>
    <div>
        <NavigationBar></NavigationBar>
        <b-container>
            <b-row>
                <b-col><TransportsList></TransportsList></b-col>
            </b-row>
        </b-container>
    </div>
</template>

<script>
    import NavigationBar from "../components/NavigationBar";
    import TransportsList from "../components/ListTransports";
    export default {
        name: "ListTransports",
        components: { NavigationBar, TransportsList},
    }
</script>

<style scoped>

</style>