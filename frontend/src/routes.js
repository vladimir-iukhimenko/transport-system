import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

const router = new Router({
    mode:"history",
    routes: [
        {
            path: "/",
            name: "Home",
            component: () => import("./views/MainPage")
        },
        {
            path: "/login",
            name: "Login",
            component: () => import("./views/Login")
        },
        {
            path: "/register",
            name: "Register",
            component: () => import("./views/Register")
        },
        {
            path: "/profile",
            name: "Profile",
            component: () => import("./views/Profile")
        },
        {
            path: "/admin",
            name: "Admin",
            component: () => import("./views/Admin")
        },
        {
            path: "/transports",
            name: "Transports",
            component: () => import("./views/ListTransports")
        },
        {
            path: "/transports/add",
            name: "New Transport Model",
            component: () => import("./views/CreateTransport")
        },
        {
            path: "/transports/:modelId",
            name: "New Transport",
            component: () => import("./components/Transport")
        },
        {
            path: "/transports/edit/:transportId:modelId",
            name: "Edit Transport",
            component: () => import("./components/Transport")
        },
        {
            path: "/engines",
            name: "Engines",
            component: () => import("./views/Engines")
        },
        {
            path: "/employees",
            component: () => import("./views/Employees")
        },
        {
            path: "/employees/docs/:id",
            component: () => import("./views/EmployeeDocs")
        },
        {
            path: "/employees/select",
            component: () => import("./components/EmployeeSelection")
        },
        {
            path: "/transportdocs",
            component: () => import("./views/TransportDocs")
        },
        {
            path: "/transportorders",
            component: () => import("./views/TransportOrders")
        },
        {
            path: "/transportorders/create",
            component: () => import("./views/CreateTransportOrder")
        },
        {
            path: "/nomenclatures",
            component: () => import("./views/Nomenclatures")
        }
    ]
});

router.beforeEach((to, from, next) => {
    const publicPages = ['/login', '/register'];
    const authRequired = !publicPages.includes(to.path);
    const loggedIn = localStorage.getItem('user');

    if (authRequired && !loggedIn) {
        next('/login');
    } else {
        next();
    }
});

export default router;