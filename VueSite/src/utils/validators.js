//验证账号格式
var validateAccount = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请输入账号'));
  } else if (value.length>10 || value.length<3){
    callback(new Error('长度在 3 到 10 个字符'));
  } else {
    callback();
  }
};
//验证密码格式
var validatePass = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请输入密码'));
  } else if (value.length>12 || value.length<3){
    callback(new Error('长度在 3 到 12 个字符'));
  } else {
    callback();
  }
};
//验证是否为空
var validateBlank = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('必填信息'));
  } else {
    callback();
  }
};

export {
  validateAccount,
  validatePass,
  validateBlank
}