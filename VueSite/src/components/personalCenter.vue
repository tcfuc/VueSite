<template>
  <el-container >

    <el-header class="borderBottom manage-title" style="height:50px;" >
      <!-- 子页面标题 -->
      <div class="manage-left">
        <i class="el-icon-star-off"></i>
        <span>个人中心</span>
      </div>
    </el-header>


    <el-main  class="no-padding-margin main">
      <!-- 功能按钮 -->
      <div class="">
        <el-tabs tab-position="right" style="height: 600px;">
          <el-tab-pane label="个人信息">
            <!-- 查询 输入框和按钮 -->
            <div style="text-align:center;">
              <el-form :model="form" class="pc-form" label-width="80px" ref="form" :rules="rules">
                  <el-row class="main-form-row">
                    <el-col :span="24">
                      <label>用户昵称：</label>
                      <el-input size="small" v-model="form.login_code" placeholder="请输入内容" class="pc-form-input" ></el-input>
                    </el-col>
                  </el-row>
                  <el-row class="main-form-row">
                    <el-col :span="24">
                      <label>电子邮箱：</label>
                      <el-input size="small" v-model="form.user_name" placeholder="请输入内容" class="pc-form-input"></el-input>
                    </el-col>
                  </el-row>
                  <el-row class="main-form-row">
                    <el-col :span="24">
                      <label>手机号码：</label>
                      <el-input size="small" v-model="form.email" placeholder="请输入内容" class="pc-form-input"></el-input>
                    </el-col>
                  </el-row>
                  <el-row class="main-form-row">
                    <el-col :span="8" :offset="8">
                      <el-form-item label="备注信息:" prop="remarks" >
                        <el-input size="small" type="textarea" :rows="5" v-model="form.remarks" autocomplete="off" class="pc-form-input"></el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row  class="main-form-row">
                    <el-col :span="24">
                      <el-button type="primary" icon="el-icon-search" @click="handleEditInfo()">保存</el-button>
                    </el-col>
                  </el-row>
              </el-form>
            </div>
          </el-tab-pane>
          <el-tab-pane label="修改密码">
            <div style="text-align:center;">
              <el-form :model="form" class="pc-form" label-width="80px" ref="form" :rules="rules">
                  <el-row class="main-form-row">
                    <el-col :span="24">
                      <label>输入新密码：</label>
                      <el-input size="small" v-model="form.password" placeholder="请输入内容" class="pc-form-input" suffix-icon="el-icon-lock"></el-input>
                    </el-col>
                  </el-row>
                  <el-row  class="main-form-row">
                    <el-col :span="24">
                      <el-button type="primary" icon="el-icon-search" @click="handleEditInfo()">保存</el-button>
                    </el-col>
                  </el-row>
              </el-form>
            </div>
          </el-tab-pane>
        </el-tabs>
      </div>
    </el-main>
  
  </el-container>
</template>

<script>
import Api from "@/api/api"
import http from '@/utils/http'
import { getUserName } from "@/utils/auth"

export default {
  data() {
      return {
        activeName: 'first',
        
        form: {
          user_code : '',
          login_code : '',
          user_name : '',
          email : '',
          mobile : '',
          phone : '',
          remarks : '',
          password : '',
        },
        rules:{},
      };
  },
  mounted:function() {
    const that = this;
    that.getName();
    setTimeout(() =>{
        that.selectInfo()
    },400)
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    },
    //获取用户名
    getName() {
        this.form.user_name = getUserName()
    },
    //修改用户操作
    handleEditInfo(index, row) {
      if(this.dialogFormEdit === false){
        this.dialogFormEdit = true;
        this.form = row;
      } else {
        this.$confirm('此操作将修改该用户信息, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.updateInfo()
          setTimeout(() =>{
            this.resetForm()
          },350);
          // 延迟加载
          setTimeout(() =>{
            this.selectInfo()
          },400);
          
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消修改'
          });          
        });
      }
    },

    //查询用户信息
    selectInfo() {
      return new Promise((resolve, reject) =>{
        http({
          method: 'post',
          url: Api.queryUserApi,
          params: {
            user_code: this.form.user_code,
            login_code: this.form.login_code,
            user_name : this.form.user_name,
            email : this.form.email,
            mobile : this.form.mobile,
            phone : this.form.phone,
          }
        }).then(res=>{
          this.form.user_code = res[0].user_code;
          this.form.login_code = res[0].login_code;
          this.form.user_name = res[0].user_name;
          this.form.email = res[0].email;
          this.form.mobile = res[0].mobile;
          this.form.phone = res[0].phone;
          this.form.remarks = res[0].remarks;
        }).catch(err => {
          console.log(err)
          this.$message({
          type: 'waring',
          message: '查询失败!'
          });
        })
      })
    },
    //修改用户信息
    updateInfo() {
      return new Promise((resolve, reject) =>{
        http({
          method: 'post',
          url: Api.updateUserApi,
          params: {
            user_code: this.form.user_code,
            login_code: this.form.login_code,
            user_name : this.form.user_name,
            email : this.form.email,
            mobile : this.form.mobile,
            phone : this.form.phone,
            password : this.form.user_code,
          }
        }).then(res=>{
          this.$message({
          type: 'success',
          message: '修改成功!'
          });
        }).catch(err => {
          console.log(err)
          this.$message({
          type: 'waring',
          message: '修改失败!'
          });
        })
      })
    },
    //退出登录
    logOut (){
        var router = this.$router
        removeToken();
        router.push({path:'/'})
        router.go(0);
    },
  }
}
</script>

<style scoped>
@import "../css/manage.css";

</style>
