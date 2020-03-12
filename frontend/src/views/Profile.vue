<template>
    <div>
        <NavigationBar></NavigationBar>
        <b-container>
            <b-row>
                <b-col>
                    <SideMenu></SideMenu>
                </b-col>
            <b-col cols="8">
                <p>
                    <strong>Имя:</strong>
                    {{userAsEmployeeDetails.name}}
                </p>
                <p>
                    <strong>Фамилия:</strong>
                    {{userAsEmployeeDetails.surname}}
                </p>
                <p>
                    <strong>Отчество:</strong>
                    {{userAsEmployeeDetails.patronymic}}
                </p>
                <p>
                    <strong>Подразделение:</strong>
                    {{userAsEmployeeDetails.department}}
                </p>
                <p>
                    <strong>Должность:</strong>
                    {{userAsEmployeeDetails.position}}
                </p>
                <strong>Роли в системе:</strong>
                <ul>
                    <li v-for="(role,index) in currentUser.roles" :key="index">{{role}}</li>
                </ul>
            </b-col>
            </b-row>
        </b-container>
    </div>
</template>

<script>
    import RestAPIService from "../service/RestAPIService";
    import NavigationBar from "../components/NavigationBar";
    import SideMenu from "../components/SideMenu";

    export default {
        name: "Profile",
        components: {SideMenu, NavigationBar},
        data() {
            return {
                userAsEmployeeDetails: {},
            }
        },
        computed: {
            currentUser() {
                return this.$store.state.auth.user;
            }
        },
        created() {
            if (this.$store.state.auth.user.employeeid)
            {
                RestAPIService.retrieve(this.$store.state.auth.user.employeeid, "employees").then(
                    response => {
                        this.userAsEmployeeDetails = response.data;
                    });
            }
        },

        mounted() {
            if (!this.currentUser) {
                this.$router.push('/login');
            }
        }
    };
</script>
