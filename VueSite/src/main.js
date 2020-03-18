// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import echarts from 'echarts'
import VueResource  from 'vue-resource'
import fontawesome from '@fortawesome/fontawesome'
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome"
import solid from '@fortawesome/fontawesome-free-solid'
import regular from '@fortawesome/fontawesome-free-regular'
import brands from '@fortawesome/fontawesome-free-brands'
import store from './store'
import http from './utils/http'
import Vuex from 'vuex'
import router from './router'
import VueRouter from 'vue-router'

//引入font-awesome图标
fontawesome.library.add(solid)
fontawesome.library.add(regular)
fontawesome.library.add(brands)

Vue.component('font-awesome-icon', FontAwesomeIcon)

Vue.config.productionTip = false

Vue.prototype.$http = http

//引入element-ui
Vue.use(ElementUI)
// 引入echarts
Vue.prototype.$echarts = echarts
//引入vue-resourse
Vue.use(VueResource)
//引入vuex
Vue.use(Vuex)
//引入router
Vue.use(VueRouter)

// main.js
router.beforeEach((to, from, next) => {
  if (store.state.token) { // 判断是否有token
    if (to.path === '/') {
      next({ path: '/manage' });
    } else {
      if (store.state.roles.length === 0) { // 判断当前用户是否已拉取完user_info信息
        store.commit("getUserInfo")
        next()
      } else {
        next() //当有用户权限的时候，说明所有可访问路由已生成 如访问没权限的全面会自动进入404页面
      }
    }
  } else {
    if(to.path === '/'){
      next();
    }else{
      next('/'); // 否则全部重定向到登录页
    }
  }
});

/* eslint-disable no-new */
var vue = new Vue({
  el: '#app',
  components: { App },
  router,
  store,
  template: '<App/>'
})
export default vue
