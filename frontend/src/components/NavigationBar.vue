<template>
    <div>
        <b-navbar toggleable="lg" type="dark" variant="info">
            <b-navbar-brand to="/">Транспортная система</b-navbar-brand>
            <b-navbar-toggle target="nav-line"></b-navbar-toggle>
            <b-collapse id="nav-line" is-nav>
                <b-navbar-nav class="ml-auto">
                    <b-nav-form>
                        <b-form-input class="mr-sm-2" size="sm" placeholder="Поиск"></b-form-input>
                        <b-button class="my-2 my-sm-0" size="sm" type="submit">Поиск</b-button>
                    </b-nav-form>
                    <b-nav-item-dropdown text="Язык" right>
                        <b-dropdown-item>РУС</b-dropdown-item>
                        <b-dropdown-item>ENG</b-dropdown-item>
                    </b-nav-item-dropdown>
                    <b-nav-item-dropdown right>
                        <template v-slot:button-content>
                            <em>{{user}}</em>
                        </template>
                        <b-dropdown-item v-on:click="$router.push(`/profile`)">Профиль</b-dropdown-item>
                        <b-dropdown-item v-on:click="logout">Выйти</b-dropdown-item>
                    </b-nav-item-dropdown>
                </b-navbar-nav>
            </b-collapse>
        </b-navbar>
    </div>
</template>

<script>

    export default {
        name: "NavigationBar",
        data() {
            return {
                user: '',
            }
        },
        created() {
                if (this.$store.state.auth.user.employee) {
                    this.user = String(this.$store.state.auth.user.employee.name + ' ' + this.$store.state.auth.user.employee.surname);
                }
                else this.user = this.$store.state.auth.user.username;

        },
        methods: {
            logout() {
                this.$store.dispatch('auth/logout');
                this.$router.push(`/login`);
            }
        }
    }

</script>

<style scoped>

</style>