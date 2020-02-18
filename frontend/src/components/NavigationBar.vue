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
                            <em>Пользователь</em>
                        </template>
                        <b-dropdown-item>Профиль</b-dropdown-item>
                        <b-dropdown-item>Выйти</b-dropdown-item>
                    </b-nav-item-dropdown>
                </b-navbar-nav>
            </b-collapse>
        </b-navbar>
    </div>
</template>

<script>
    export default {
        name: "NavigationBar"
    }
</script>

<style scoped>

</style>