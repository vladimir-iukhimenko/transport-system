import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

const router = new Router({
    mode:"history",
    routes: [
        {
            path: "/",
            name: "Home",
            component: () => import("./components/MainApp")
        },
        {
            path: "/transports",
            name: "Transports",
            component: () => import("./components/ListTransports")
        },
        {
            path: "/transports/:id",
            name: "New Transport",
            component: () => import("./components/Transport")
        },
        {
            path: "/transports/edit/:id",
            name: "Edit Transport",
            component: () => import("./components/Transport")
        },
        {
            path: "/engines",
            name: "Engines",
            component: () => import("./components/ListEngines")
        },
        {
            path: "/engines/:id",
            name: "Engine Details",
            component: () => import("./components/Engine")
        },
        {
            path: "/engines/add",
            name: "New Engine Addition",
            component: () => import("./components/Engine")
        }
    ]
});

export default router;