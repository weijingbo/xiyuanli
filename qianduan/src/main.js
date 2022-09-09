import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import XLSX from 'xlsx'
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios';
import rules from 'element-ui-validation';

Vue.prototype.$rules = rules;
Vue.prototype.$axios = axios;
Vue.config.debug = true;
Vue.use(ElementUI);
Vue.use(XLSX);


Vue.config.productionTip = false

new Vue({
  router:router,
  render: h => h(App),
}).$mount('#app')
