<template>
    <div>
        <b-card bg-variant="light">
            <b-form @submit.prevent="handleRegister">
                <b-form-group label="Регистрация" label-class="font-weight-bold pt-0" label-size="lg" class="mb-0"></b-form-group>
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
                <b-button variant="primary" type="submit">Зарегистрироваться</b-button>
            <div v-if="message" class="alert" :class="successful ? 'alert-success' : 'alert-danger'">{{message}}</div>
            </b-form>
        </b-card>
        </div>
</template>

<script>
    import User from "../models/user";

    export default {
        name: 'Register',
        data() {
            return {
                user: new User('', ''),
                submitted: false,
                successful: false,
                message: ''
            };
        },
        computed: {
            loggedIn() {
                return this.$store.state.auth.status.loggedIn;
            }
        },
        mounted() {
            if (this.loggedIn) {
                this.$router.push('/profile');
            }
        },
        methods: {
            handleRegister() {
                this.message = '';
                this.submitted = true;
                this.$store.dispatch('auth/register', this.user).then(
                            data => {
                                this.successful = true;
                                this.message = data.message + " Переход на форму входа...";
                                setTimeout(this.redirect,3000);
                            },
                            error => {
                                this.message =
                                    (error.response && error.response.data) ||
                                    error.message ||
                                    error.toString();
                                this.successful = false;
                            }
                        );
                    },
            redirect() {
                this.$router.push(`/login`).catch(err => {return err});
            }
                },
            }
</script>

<style scoped>

</style>
