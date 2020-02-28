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

export default router;