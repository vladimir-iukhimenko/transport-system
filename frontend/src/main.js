import Vue from 'vue';
import App from './App.vue';
import router from './routes';
import store from './store';
import ValidationProvider from 'vee-validate';
import {BootstrapVue, BootstrapVueIcons} from "bootstrap-vue";
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';
import {library} from "@fortawesome/fontawesome-svg-core";
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";
import {faHome,faUser,faUserPlus,faSignInAlt,faSignOutAlt} from "@fortawesome/free-solid-svg-icons";

library.add(faHome,faUser,faUserPlus,faSignInAlt,faSignOutAlt);

Vue.config.productionTip = false;

Vue.use(BootstrapVue);
Vue.use(BootstrapVueIcons);
Vue.component('font-awesone-icon', FontAwesomeIcon);
Vue.component('ValidationProvider', ValidationProvider);

new Vue({
  router,
  store,
  render: h => h(App),

}).$mount('#app');

