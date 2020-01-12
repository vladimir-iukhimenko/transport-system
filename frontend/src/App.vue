<template>
  <div class="container">
    <MainApp />
  </div>
</template>

<script>
import MainApp from "./components/MainApp";

export default {
  name: 'app',
  components: {
    MainApp,
  }
}
</script>

<style>
@import "~bootstrap/dist/css/bootstrap.min.css";
</style>
