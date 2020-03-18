const root = process.env.API_ROOT;

const Api= {
  loginApi: root + 'login.action',
  getUserInfo: root + 'getUserInfo.action',
  getServerInfo: root + 'getServerInfo.action',
  getDiskInfo: root + 'getDiskInfo.action',
  getCPUInfo: root + 'getCPUInfo.action',

  queryCompanyOfficeApi: root + 'queryCompanyOffice.action',
  insertCompanyOfficeApi: root + 'insertCompanyOffice.action',
  deleteCompanyOfficeApi: root + 'deleteCompanyOffice.action',
  updateCompanyOfficeApi: root + 'updateCompanyOffice.action',

  queryCompanyApi: root + 'queryCompany.action',
  insertCompanyApi: root + 'insertCompany.action',
  deleteCompanyApi: root + 'deleteCompany.action',
  updateCompanyApi: root + 'updateCompany.action',

  queryMenuApi: root + 'queryMenu.action',
  insertMenuApi: root + 'insertMenu.action',
  deleteMenuApi: root + 'deleteMenu.action',
  updateMenuApi: root + 'updateMenu.action',

  queryOfficeApi: root + 'queryOffice.action',
  insertOfficeApi: root + 'insertOffice.action',
  deleteOfficeApi: root + 'deleteOffice.action',
  updateOfficeApi: root + 'updateOffice.action',

  queryRoleMenuApi: root + 'queryRoleMenu.action',
  insertRoleMenuApi: root + 'insertRoleMenu.action',
  deleteRoleMenuApi: root + 'deleteRoleMenu.action',
  updateRoleMenuApi: root + 'updateRoleMenu.action',

  queryRoleApi: root + 'queryRole.action',
  insertRoleApi: root + 'insertRole.action',
  deleteRoleApi: root + 'deleteRole.action',
  updateRoleApi: root + 'updateRole.action',

  queryUserRoleApi: root + 'queryUserRole.action',
  insertUserRoleApi: root + 'insertUserRole.action',
  deleteUserRoleApi: root + 'deleteUserRole.action',
  updateUserRoleApi: root + 'updateUserRole.action',

  queryUserApi: root + 'queryUser.action',
  insertUserApi: root + 'insertUser.action',
  deleteUserApi: root + 'deleteUser.action',
  updateUserApi: root + 'updateUser.action',

  queryPostApi: root + 'queryPost.action',
  insertPostApi: root + 'insertPost.action',
  deletePostApi: root + 'deletePost.action',
  updatePostApi: root + 'updatePost.action',

  queryAreaApi: root + '/queryArea.action',
  insertAreaApi: root + '/insertArea.action',
  deleteAreaApi: root + '/deleteArea.action',
  updateAreaApi: root + '/updateArea.action',

}

export default Api