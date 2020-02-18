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
            path: "/engines/:id",
            name: "Engine Details",
            component: () => import("./components/Engine")
        },
        {
            path: "/engines/add",
            name: "New Engine Addition",
            component: () => import("./components/Engine")
        },
        {
            path: "/employees",
            component: () => import("./components/ListEmployees")
        },
        {
            path: "/transportdocs",
            component: () => import("./views/TransportDocs")
        }
    ]
});

export default router;