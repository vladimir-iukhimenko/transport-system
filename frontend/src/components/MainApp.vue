<template>
    <div>
        This is the main page of Transport system!
        <ListEngines></ListEngines>
    </div>
</template>

<script>
    import ListEngines from "./ListEngines";
    export default {
        name: "MainApp",
        components: {
            ListEngines
        }
    };
</script>

<style scoped>

</style>