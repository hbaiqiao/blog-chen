import { createApp } from 'vue'
import App from './App.vue'
import router from '../router/index'
import store from '../store/index'
import axios from 'axios';

import VueAxios from 'vue-axios'
// axios.defaults.baseURL = 'http://localhost:6660/api'

axios.defaults.baseURL = 'http://47.106.177.94:6660/api'
// Vue.prototype.$User = 'chen'
createApp(App).use(router).use(store).use(VueAxios,axios).mount('#app')

//登录拦截  路由守卫
router.beforeEach((to, from, next) => {
    if (to.meta.requireAuth) {
      if (store.state.manager.num) {
        next()
      } else {
        next({
          path: 'login',
          query: {redirect: to.fullPath}
        })
      }
    } else {
      next()
    }
  }
)
