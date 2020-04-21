<template>
    <div>
        <b-card bg-variant="light">
            <b-form @submit.prevent="handleRegister">
                <b-form-group label="Регистрация" label-class="font-weight-bold pt-0" label-size="lg" class="mb-0"></b-form-group>
                <b-form-group id="input-group-1"
                              label="Логин"
                              label-for="input-1">
                    <b-form-input id="input-1"
                                  v-model="$v.user.username.$model"
                                  :state="validateState('username')"
                                  type="text"
                                  placeholder="">
                    </b-form-input>
                    <b-form-text id="input-group-1">Допустимы только латинские буквы, точка и символ подчёркивания</b-form-text>
                </b-form-group>
                <b-form-group id="input-group-2"
                              label="Пароль"
                              label-for="input-2">
                    <b-form-input id="input-2"
                                  v-model="$v.user.password.$model"
                                  :state="validateState('password')"
                                  type="password"
                                  placeholder="">
                    </b-form-input>
                    <b-form-text id="input-group-2">Должен быть не короче 6 символов</b-form-text>
                </b-form-group>
                <b-button :variant="!successful ? 'primary' : 'dark'" :disabled="$v.$anyError || successful" type="submit" >Зарегистрироваться</b-button>
            <div v-if="message" class="alert" :class="successful ? 'alert-success' : 'alert-danger'">{{message}}</div>
            </b-form>
        </b-card>
        </div>
</template>

<script>
    import User from "../models/user";
    import {isCorrectUsername} from "../models/validators/validators";
    import {required, minLength} from "vuelidate/lib/validators";
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
        validations: {
            user: {
                username: {
                    required,
                    isCorrectUsername
                },
                password: {
                    required,
                    minLength: minLength(6)
                }
            }
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
                this.$v.user.$touch();
                if (this.$v.user.$anyError) return;
                this.message = '';
                this.submitted = true;
                this.$store.dispatch('auth/register', this.user).then(
                            data => {
                                this.successful = true;
                                this.message = data.message + " Переход на форму входа...";
                                setTimeout(this.redirect,3000);
                            },
                            error => {
                                this.message = error.response.data.message;
                                this.successful = false;
                            }
                        );
                    },
            validateState(name) {
                const { $dirty, $error } = this.$v.user[name];
                return $dirty ? !$error : null;
            },
            redirect() {
                this.$router.push(`/login`).catch(err => {return err});
            }
                },
            }
</script>

<style scoped>

</style>
