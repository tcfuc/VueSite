import axios from 'axios';
import { MessageBox,Message } from 'element-ui'
import store from '@/store/index'
import { getToken,removeToken } from '@/utils/auth'


const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // api 的 base_url
  withCredentials: true, // 跨域请求时发送 cookies
  timeout: 5000, // request timeout
  headers: {
    "Access-Control-Allow-Origin": "*",
    'Access-Control-Allow-Headers': 'Content-Type, Content-Length, Authorization, Accept, X-Requested-With ,yourHeaderFeild',
    "Access-Control-Allow-Methods": "PUT,POST,GET,DELETE,OPTIONS",
    "Content-Type": "application/json;charset=utf-8"
  },//设置请求头
})

//http request 拦截器
service.interceptors.request.use(
  config => {
    if (store.state.token) {
      config.headers['token'] = getToken() // 让每个请求携带token--['Token']为自定义key 请根据实际情况自行修改
    }
    return config;
  }, error => {
    console.log(error) // for debug
    return Promise.reject(error);
  }
);

// respone拦截器
service.interceptors.response.use(
  /**
   * 下面的注释为通过在response里，自定义code来标示请求状态
   * 当code返回如下情况则说明权限有问题，登出并返回到登录页
   * 如想通过 XMLHttpRequest 来状态码标识 逻辑可写在下面error中
   * 以下代码均为样例，请结合自生需求加以修改，若不需要，则可删除
   */
  response => {
    const res = response.data
      // 50008:非法的token; 50012:其他客户端登录了;  50014:Token 过期了;
    if (res.code === "50008" || res.code === "50012" || res.code === "50014") {
      // 请自行在引入 MessageBox
      // import { Message, MessageBox } from 'element-ui'
      MessageBox.alert('你已被登出，请重新登录', '确定登出', {
        confirmButtonText: '重新登录',
        type: 'warning',
        callback: action => {
          removeToken();
          store.commit('del_token');
          location.reload(); // 为了重新实例化vue-router对象 避免bug
          this.$message({
          });
        }
      })
      return Promise.reject(res.message || 'error')
    } else {
      return res
    }
  },
  error => {
    console.log('err' + error) // for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service