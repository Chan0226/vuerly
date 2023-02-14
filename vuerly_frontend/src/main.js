import Vue from "vue";
import App from "./App.vue";
import router from "./routes";
import vuetify from "./plugins/vuetify";
import axios from "axios";
import VueSession from "vue-session";

var sessionOptions = {
  persist: true,
};
Vue.use(VueSession, sessionOptions);
Vue.config.productionTip = false;
Vue.prototype.$axios = axios;

new Vue({
  render: (h) => h(App),
  vuetify,
  router,
}).$mount("#app");
