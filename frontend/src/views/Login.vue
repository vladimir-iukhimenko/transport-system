<template>
    <div>
        <b-card bg-variant="light">
            <b-form @submit.prevent="handleLogin">
                <b-form-group label="Вход в систему" label-class="font-weight-bold pt-0" label-size="lg" class="mb-0"></b-form-group>
                <b-form-group id="input-group-1"
                              label="Логин"
                              label-for="input-1">
                    <b-form-input id="input-1"
                                  v-model="user.username"
                                  type="text"
                                  required
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <b-form-group id="input-group-2"
                              label="Пароль"
                              label-for="input-2">
                    <b-form-input id="input-2"
                                  v-model="user.password"
                                  type="password"
                                  required
                                  placeholder="">
                    </b-form-input>
                </b-form-group>
                <div class="form-group">
                    <button class="btn btn-primary btn-block" :disabled="loading">
                        <span v-show="loading" class="spinner-border spinner-border-sm"></span>
                        <span>Войти</span>
                    </button>
                    <button class="btn btn-primary btn-block" @click="$router.push('/register')">Зарегистрироваться</button>
                </div>
                <div class="form-group">
                    <div v-if="message" class="alert alert-danger" role="alert">{{message}}</div>
                </div>
            </b-form>
        </b-card>

        </div>
</template>

<script>
    import User from "../models/user";
    export default {
        name: "Login",
        data() {
            return {
                user: new User('', ''),
                loading: false,
                message: ''
            };
        },
        computed: {
            loggedIn() {
                return this.$store.state.auth.status.loggedIn;
            }
        },
        created() {
            if (this.loggedIn) {
                this.$router.push('/');
            }
        },
        methods: {
            handleLogin() {
                this.loading = true;
                this.message = '';
                this.$store.dispatch('auth/login', this.user).then(
                    () => {
                        this.$router.push(`/`);
                    },
                    error => {
                        this.message = error.response.data.message.includes("credentials") ? "Неверный логин или пароль!" : error.response.data.message;
                        this.loading = false;
                    }
                );
            }
        },
    }

</script>

<style scoped>

</style>
