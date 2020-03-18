import Vue from 'vue'
import Router from 'vue-router'

import index from '@/components/index'
import login from '@/components/login'
import home from '@/components/home'
import personalCenter from '@/components/personalCenter'

import userManage from '@/components/om/userManage'
import officeManage from '@/components/om/officeManage'
import companyManage from '@/components/om/companyManage'
import postManage from '@/components/om/postManage'

import secondAdmin from '@/components/rm/secondAdmin'
import systemAdmin from '@/components/rm/systemAdmin'

import dataMonitor from '@/components/sm/dataMonitor'
import serverMonitor from '@/components/sm/serverMonitor'
import storageMonitoring from '@/components/sm/storageMonitoring'

import administrativeArea from '@/components/ss/administrativeArea'





Vue.use(Router)


//所有权限通用路由表 
//如登录页和一些不用权限的公用页面
export const constantRouterMap =[
    {
      path: '/',
      name: 'login',
      component: login,
      meta: {role: ['0', '1', '2'], nav: false},
    },
    {
      path: '/index',
      name: 'index',
      components: {
        default: index,
      },
      meta: {role: ['0', '1', '2'], nav: false},
      children: [
        {
          path: '/manage', 
          components: {
            postManage: postManage,
            officeManage: officeManage,
            companyManage: companyManage,
            userManage: userManage,
            secondAdmin: secondAdmin,
            systemAdmin: systemAdmin,
            dataMonitor: dataMonitor,
            administrativeArea: administrativeArea,
            serverMonitor: serverMonitor,
            storageMonitoring: storageMonitoring,
            personalCenter: personalCenter,
            home: home,
          },
        },
        {
          path: '/rm', 
          components: {
          },
        },
      ]
    },
]

//实例化vue的时候只挂载constantRouter
export default new Router({
  routes: constantRouterMap
});

//异步挂载的路由
//动态需要根据权限加载的路由表 
export const asyncRouterMap = [
  {
      path: '/index',
      component: index,
      name: '权限测试页',
      // meta: { role: ['admin','super_editor'] }  //页面需要的权限
  },
  { path: '*', redirect: '/404', hidden: true }
];

