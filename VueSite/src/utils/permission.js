import { asyncRoutes } from '@/router/index'
const actions = {
  permission: ({commit}, role) => { /* 根据权限信息加载权限路由 */
    let routeArr = deepCopy(asyncRoutes) // 含有复杂数组对象，循环进行深拷贝
    const b = filterAsyncRouter(routeArr, role)
    commit('navList', b)
  },
}
// 根据权限 筛选路由
function filterAsyncRouter (asyncRouterMap, roles) {
  const accessedRouters = asyncRouterMap.filter(route => {
    if (route.meta.role.indexOf(roles) >= 0) {
      if (route.children && route.children.length) {
        route.children = filterAsyncRouter(route.children, roles)
      }
      return true
    }
    return false
  })
  return accessedRouters
}
function deepCopy (routeArr) {
  return routeArr.map((arr) => {
    arr = Object.assign({}, arr)
    return arr
  })
}

export default actions