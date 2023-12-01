import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from './axios/index.js'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.use(ElementUI);

Vue.config.productionTip = false
Vue.prototype.$axios=axios;


new Vue({
    router,
    render: h => h(App)
}).$mount('#app')
