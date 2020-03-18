<template>
    <el-container>

    <el-header class="borderBottom manage-title" style="height:50px;" >
      <!-- 子页面标题 -->
      <div class="manage-left">
        <i class="el-icon-unlock"></i>
        <span>二级管理员</span>
      </div>
      <!-- 功能按钮 -->
      <div class="manage-right">

        <el-button plain size="small" type="info" icon="el-icon-refresh" @click="selectInfo()">刷新</el-button>
        <el-button plain size="small" type="success" icon="el-icon-plus" @click="dialogFormInsert = true, resetForm()">新增</el-button>
      </div>
    </el-header>


    <el-main  class="no-padding-margin main">
        <!-- 查询 输入框和按钮 -->
      <div style="text-align:left;">
        <el-form :model="form" class="main-form" label-width="80px" ref="form" :rules="rules" >
            <el-row class="main-form-row">
              <el-col :span="4">
                <label>账号：</label>
                <el-input size="mini" v-model="form.login_code" placeholder="请输入内容" class="main-form-input"></el-input>
              </el-col>
              <el-col :span="4">
                <label>昵称：</label>
                <el-input size="mini" v-model="form.user_name" placeholder="请输入内容" class="main-form-input"></el-input>
              </el-col>
              <el-col :span="4">
                <el-button size="mini" type="primary" icon="el-icon-search" @click="selectInfo()">查询</el-button>
                <el-button size="mini" icon="el-icon-search" @click="resetForm(), selectInfo()">重置</el-button>
              </el-col>
            </el-row>
        </el-form>
      </div>
      <!-- 二级管理员表格 -->
      <div class="main-table">
        <el-table
          ref="singleTable"
          border
          stripe
          size="mini"
          :data="manageDataPage"
          highlight-current-row
          :height="maxHeight"
          @current-change="handleCurrentChange"
          style="width: 100%;">
          <template v-for="(col,index) in manageTitle">
            <el-table-column v-if="col.type === 'index'" :prop="col.prop" :label="col.label" align="center" type="index" width="40">
              </el-table-column>
              <el-table-column v-if="col.type === 'normal'" :prop="col.prop" :label="col.label" align="center" :width="col.width">
              </el-table-column>
              <el-table-column v-if="col.type === 'operate'" :prop="col.prop" :label="col.label" align="center" width="150">
                <!-- 表格内操作按钮 -->
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    type="danger"
                    icon="el-icon-delete" 
                    title="取消二级管理员身份"
                    circle
                    @click="handleDeleteInfo(scope.$index, scope.row)"></el-button>
                </template>
              </el-table-column>
          </template>
        </el-table>
      </div>

            <!-- 新增对话框 -->
      <el-dialog title="新增二级管理员" :visible.sync="dialogFormInsert" @close='resetForm'>
        <el-form :model="form" label-width="80px" ref="form" :rules="rules">
          <el-row>
            <el-col :span="10">
              <el-form-item label="登录账号" prop="user_code ">
                <el-input size="small" v-model="form.user_code" autocomplete="off"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormInsert = false, resetForm()">取 消</el-button>
          <el-button type="primary" @click="handleInsertInfo()">确 定</el-button>
        </div>
      </el-dialog>
    </el-main>


    <el-footer>
      <!-- 分页 -->
      <div class="block pagination">
        <el-pagination
          background
          @size-change="handleSizeChange"
          @current-change="handleCurrentChangePage"
          :current-page="currentPage"
          :page-sizes="[20, 50, 100, 150]"
          :page-size="20"
          layout="total, sizes, prev, pager, next"
          :total="totalManage">
        </el-pagination>
      </div>
    </el-footer>
  </el-container>
</template>

<script>
import Api from "@/api/api"
import http from '@/utils/http'
import { sizeChange,currentChangePage,pagination,viewBtn,heightDetect,heightDetectWindows,heightTable } from '@/utils/utils'

export default {
  data() {
    return{
      //查询模块状态
      radio: 1,
      //模态框状态
      dialogFormInsert: false,
      dialogFormEdit: false,
      loading: false,

      //初始表单数组
      defaultForm: {
      },
      //用户信息表单数组
      form: {
        user_code : '',
        login_code : '',
        user_name : '',
      },
      //输入规则
      rules: {
        // post_code: [
        //   { validator: validateBlank, trigger: 'blur' },
        // ],
        // post_name: [
        //   { validator: validateBlank, trigger: 'blur' },
        // ],
      },
      //表格头
      manageTitle: [
        { label: "",prop: "", type: "index" },
        { label: "登录账号",prop: "login_code", type: "normal" },
        { label: "用户昵称",prop: "user_name", type: "normal" },
        { label: "操作",prop: "", type: "operate" },
        ],
        //总数据
        manageData: [],
        //分页数据
        manageDataPage: [],
        //表格定位
        currentRow: null,
        //信息总条数
        totalManage: 0,
        //页码
        currentPage: 1,
        //每页信息条数
        pageSize: 20,

        //屏幕高度
        fullHeight: 0,
        //表格最大高度
        maxHeight: 0,
        //其他组件高度
        extHeight: 310,
        //查询模块高度
        queryModuleHeight: 63,
        //管理员类型
        mgr_type : '0',
    }
  },
  created: function() {
    const that = this;
    that.handleHeightTable()  
    //备份初始表单
    that.defaultForm = JSON.parse(JSON.stringify(that.form));
  },
  mounted:function() {
    const that = this;
    //查询所有用户信息
    that.selectInfo()  
    //监视屏幕高度变化
    heightDetectWindows(this.vueObject(),this.extHeight)
  },
  watch: {
    //监视window.onresize值变化
    fullHeight(val) {
      heightDetect(this.vueObject(),val)
    }
  },
  methods: {
    //获取vue实例
    vueObject() {
      var self = this
      return self
    },
    // 确定分页信息条数
    handleSizeChange(val) {
      sizeChange(this.vueObject(),val)
    },
    // 确定分页页码
    handleCurrentChangePage(val) {
      currentChangePage(this.vueObject(),val)
    },
    //隐藏和展现按钮
    viewButton(){
      viewBtn(this.vueObject())
    },
    //设置表格初始高度
    handleHeightTable(){
      heightTable(this.vueObject())
    },


    //清空表单
    resetForm(){
      this.form = JSON.parse(JSON.stringify(this.defaultForm));
    },
    handleCurrentChange(val) {
      this.currentRow = val;
    },


    //删除用户操作
    handleDeleteInfo(index, row){
      this.$confirm('此操作将永久删除该二级管理员, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.deleteInfo(index, row)
        // 延迟加载
        setTimeout(() =>{
          this.selectInfo()
        },400);
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });          
      });
    },

    //新增用户操作
    handleInsertInfo() {
      this.$confirm('此操作将新增该用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.insertInfo()
        // 延迟加载
        setTimeout(() =>{
          this.selectInfo()
        },400);
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消新增'
        });          
      });
    },


    //查询二级管理员信息
    selectInfo() {
      return new Promise((resolve, reject) =>{
        http({
          method: 'post',
          url: Api.queryUserApi,
          params: {
            user_code: this.form.user_code,
            login_code: this.form.login_code,
            user_name : this.form.user_name,
            mgr_type : this.mgr_type,
          }
        }).then(res=>{
          this.manageData = res;
          this.totalManage = res.length;
          this.handleSizeChange(this.pageSize);
        }).catch(err => {
          console.log(err)
          this.$message({
          type: 'waring',
          message: '查询失败!'
          });
        })
      })
    },
    //取消二级管理员身份
    deleteInfo(index, row) {
      return new Promise((resolve, reject) =>{
        http({
          method: 'post',
          url: Api.updateUserApi,
          params: {
            user_code: row.user_code,
            mgr_type: '2',
          }
        }).then(res=>{
          this.$message({
          type: 'success',
          message: '删除成功!'
          });
        }).catch(err => {
          console.log(err)
          this.$message({
          type: 'waring',
          message: '删除失败!'
          });
        })
      })
    },
    //新增二级管理员信息
    insertInfo() {
      return new Promise((resolve, reject) =>{
        http({
          method: 'post',
          url: Api.updateUserApi,
          params: {
            user_code: this.form.user_code,
            mgr_type: '0'
          }
        }).then(res=>{
          this.$message({
          type: 'success',
          message: '新增成功!'
          });
          this.dialogFormInsert = false;
        }).catch(err => {
          console.log(err)
          this.$message({
          type: 'waring',
          message: '新增失败!'
          });
        })
      })
    }
  }
}
</script>

<style scoped>
@import "../../css/manage.css";

</style>
