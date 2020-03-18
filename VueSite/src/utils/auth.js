import Cookies from 'js-cookie'

export function setUserName(userName) {
  return Cookies.set("userName", userName);
}
export function getUserName() {
  return Cookies.get("userName");
}
export function setRole(role) {
 return Cookies.set("role", role);
}
export function getRole() {
 return Cookies.get("role");
}
export function setToken(token) {
 return Cookies.set("token", token);
}
export function getToken() {
 return Cookies.get("token");
}
export function removeToken() {
  return Cookies.remove("token")
}
