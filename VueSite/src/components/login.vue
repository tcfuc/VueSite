<template>
    <el-container>
        <el-header class="login-header">VueSite</el-header>
        <el-main class="login-main">
            <el-form ref="form" :model="form" status-icon :rules="rules">
                <el-form-item prop="login_code">
                    <el-input v-model="form.login_code" 
                        type="login_code" placeholder="输入账号" autocomplete="off" clearable value="admin"></el-input>
                </el-form-item>

                <el-form-item prop="password">
                    <el-input v-model="form.password"
                        type="password" placeholder="输入密码" autocomplete="off" clearable value="123"></el-input>
                </el-form-item>

                 <el-form-item>
                    <el-button class="login-button" type="primary" @click="submitForm('form')">登录(默认账号)</el-button>
                </el-form-item>
            </el-form>
        </el-main>
        <el-footer class="login-footer">© 2019 Demo - Powered By zhouyang V1.0.0</el-footer>
    </el-container>
</template>

<script>
import Api from "../api/api"
import { setToken } from "../utils/auth"
import http from '@/utils/http'
import { validateAccount,validatePass } from '@/utils/validators'

export default {
    data() {
      return {
        form: {
          login_code: 'admin',
          password: '123',
          type: [],
          data: [],
        },
        rules: {
          login_code: [
            { validator: validateAccount, trigger: 'blur' },
          ],
          password: [
            { validator: validatePass, trigger: 'blur' },
          ],
        }
      };
    },
    mounted:function() {
      this.$store.commit('del_token')
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.loginCheck();
          } else {
            this.$notify.error({
              title: '错误',
              message: '请输入合法的账号或密码',
              showClose: false
            });
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      loginCheck() {
        return new Promise((resolve, reject) =>{
          http({
              method: 'post',
              url: Api.loginApi,
              params : {
                login_code : this.form.login_code,
                password : this.form.password
              }
          }).then((res)=>{
              //引用auth.js的方法  把token放入cookies
              if(res != "failure"){
                setToken(res);
                this.$store.commit('set_token', res);
                this.$router.push({path:'/manage'});
                this.$notify.success({
                  title: '登陆成功',
                  message: '欢迎来到VueSite',
                  showClose: false
                });
              } else {
                this.$notify.error({
                  title: '登陆失败',
                  message: '请稍后再试',
                  showClose: false
                });
                return false;
              }
          }).catch(err => {
              console.log(err)
          })
        })
      },
    }
}
</script>

<style scoped>
.login-header{
    margin-top: 100px;
    font-size: 60px;
}
.login-main{
    /* 水平居中 */
    margin:0 auto;
    margin-top: 30px;
    width: 350px;
    /* 边框圆角 */
    border-radius:5px;
    /* 边框阴影 */
    box-shadow: rgb(156, 148, 148) 0px 0px 10px 2px ;
}
.login-button{
    width: 100%;
    margin-top: 10px;
}
.login-footer{
    line-height: 60px;
}
</style>
