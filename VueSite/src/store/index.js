import Vue from 'vue'
import Vuex from 'vuex'
import Api from "@/api/api"
import http from '@/utils/http'
import { setRole,setUserName } from "../utils/auth"
import persistedState from 'vuex-persistedstate' //首先引入

Vue.use(Vuex)

const state = {
  token : '',
  roles : '',
  userName : '',
}

const getters = {
  getToken : state => state.token,
  getRoles : state => {
    return state.roles;
  },
}


// 进行异步操作和ajax请求 this.$store.dispatch("initNodeList", data);
const actions = {
  getInfo(){
      http({
        method: 'post',
        url: Api.getUserInfo,
        params: {
        }
      }).then(res=>{
        state.roles = res[0].mgr_type
      }).catch(err => {
        console.log(err)
      })
  }
}

// 执行同步操作改变state this.$store.commit('changeNodeList', { key: 'name', val: newVal })
const mutations = {
  set_token(state, token) {
    state.token = token
    sessionStorage.token = token
  },
  del_token(state) {
    state.token = ''
    sessionStorage.removeItem('token')
  },
  getUserInfo(){
    http({
      method: 'post',
      url: Api.getUserInfo,
      params: {
      }
    }).then(res=>{
      state.roles = res[0].mgr_type
      state.userName = res[0].user_name
      var role  = res[0].mgr_type
      var userName = res[0].user_name
      setRole(role)
      setUserName(userName)
    }).catch(err => {
      console.log(err)
    })
}
}

const store = new Vuex.Store({
  state,
  getters,
  actions,
  mutations,
  plugins: [persistedState()] //state持久化加上这个就可以了 
})

export default store