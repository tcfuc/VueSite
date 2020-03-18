<template>
  <el-container style="height:650px">

    <el-header class="borderBottom manage-title" style="height:50px;" >
      <!-- 子页面标题 -->
      <div class="manage-left">
        <i class="el-icon-more-outline"></i>
        <span>机构管理</span>
      </div>
      <!-- 功能按钮 -->
      <div class="manage-right">
        <!-- <el-button plain size="small" type="warning" icon="el-icon-view" @click="resetForm(), viewButton()" v-if="radio === 1">查询</el-button>
        <el-button plain size="small" type="warning" icon="el-icon-view" @click="resetForm(), viewButton()" v-if="radio === 0">隐藏</el-button> -->

        <el-button plain size="small" type="info" icon="el-icon-refresh" @click="resetForm(), selectInfo()">刷新</el-button>
        <el-button plain size="small" type="success" icon="el-icon-plus" @click="resetForm(), dialogFormInsert = true">新增</el-button>
      </div>
    </el-header>


    <el-main class="no-padding-margin main">
      <!-- 查询 输入框和按钮 -->
      <div style="text-align:left;">
        <el-form class="main-form" >
          <el-row class="main-form-row">
              <el-col :span="5">
                <label>机构代码：</label>
                <el-input size="mini" v-model="form.office_code" placeholder="请输入内容" class="main-form-input"></el-input>
              </el-col>
              <el-col :span="5">
                <label>机构名称：</label>
                <el-input size="mini" v-model="form.office_name" placeholder="请输入内容" class="main-form-input"></el-input>
              </el-col>
              <el-col :span="5">
                <label>机构全称：</label>
                <el-input size="mini" v-model="form.full_name" placeholder="请输入内容" class="main-form-input"></el-input>
              </el-col>
              <el-col :span="5">
                <label>机构类型：</label>
                <el-select v-model="form.office_type" size="mini" clearable placeholder="请选择" class="main-form-select">
                  <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                    >
                  </el-option>
                </el-select>
              </el-col>
              <el-col :span="4">
                <el-button size="mini" type="primary" icon="el-icon-search" @click="selectInfo()">查询</el-button>
                  <el-button size="mini" icon="el-icon-search" @click="resetForm(), selectInfo()">重置</el-button>
              </el-col>
          </el-row>
        </el-form>
      </div>
      <!-- 机构表格 -->
      <div class="main-table">
        <el-table
          ref="singleTable"
          border 
          stripe 
          size="mini"
          :data="treeData"
          row-key="office_code"
          highlight-current-row
          @current-change="handleCurrentChange"
          style=" width:100%;">
          <template v-for="(col,index) in manageTitle">
            <el-table-column v-if="col.type === 'name'" :prop="col.prop" :label="col.label" align="left" :width="col.width">
            </el-table-column>
            <el-table-column v-if="col.type === 'normal'" :prop="col.prop" :label="col.label" align="center" :width="col.width">
            </el-table-column>
            <el-table-column v-if="col.type === 'format'" :prop="col.prop" :label="col.label" align="center"  :formatter="formatOfficeType" show-overflow-tooltip>
            </el-table-column>
            <el-table-column v-if="col.type === 'operate'" :prop="col.prop" :label="col.label" align="center" width="150">
                <!-- 表格内操作按钮 -->
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    type="primary" 
                    icon="el-icon-edit"
                    title="修改" 
                    circle
                    @click="handleEditInfo(scope.$index, scope.row)"></el-button>
                  <el-button
                    size="mini"
                    type="danger"
                    icon="el-icon-delete" 
                    title="删除"
                    circle
                    @click="handleDeleteInfo(scope.$index, scope.row)"></el-button>
                  <el-button
                    size="mini"
                    type="primary" 
                    icon="el-icon-plus" 
                    title="新增下级公司"
                    circle
                    @click="handleInsertInfo(scope.$index, scope.row)"></el-button>
                </template>
            </el-table-column>
          </template>
        </el-table>
      </div>

      <!-- 新增机构对话框 -->
      <el-dialog title="新增机构" :visible.sync="dialogFormInsert" @close='resetForm'>
        <el-form :model="form" label-width="80px" ref="form" :rules="rules">
          <el-row>
            <el-col :span="10">
              <el-form-item label="上级机构" prop="parent_code" >
                <el-input size="small" v-model="form.parent_code" autocomplete="off"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="10" >
              <el-form-item label="机构名称" prop="office_name" >
                <el-input size="small" v-model="form.office_name" autocomplete="off"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="10" :offset="2">
              <el-form-item label="机构代码" prop="office_code" >
                <el-input size="small" v-model="form.office_code" autocomplete="off"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="10" >
              <el-form-item label="机构全称" prop="full_name" >
                <el-input size="small" v-model="form.full_name" autocomplete="off"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="10" :offset="2">
              <el-form-item label="机构类型" prop="office_type" >
                <el-select v-model="form.office_type" clearable placeholder="请选择">
                  <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                    >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="23">
              <el-form-item label="备注信息" prop="remarks" >
                <el-input size="small" type="textarea" :rows="4" v-model="form.remarks" autocomplete="off"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormInsert = false, resetForm()">取 消</el-button>
          <el-button type="primary" @click="handleInsertInfo()">确 定</el-button>
        </div>
      </el-dialog>

      <!-- 编辑机构对话框 -->
      <el-dialog title="编辑机构" :visible.sync="dialogFormEdit"  @close='resetForm' >
        <el-form :model="form" label-width="80px" ref="form" :rules="rules">
          <el-row>
            <el-col :span="10">
              <el-form-item label="上级机构" prop="parent_code" >
                <el-input size="small" v-model="form.parent_code" autocomplete="off"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
           <el-row>
            <el-col :span="10" >
              <el-form-item label="机构名称" prop="office_name" >
                <el-input size="small" v-model="form.office_name" autocomplete="off"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="10" :offset="2">
              <el-form-item label="机构代码" prop="office_code" >
                <el-input size="small" v-model="form.office_code" autocomplete="off" disabled></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="10" >
              <el-form-item label="机构全称" prop="full_name" >
                <el-input size="small" v-model="form.full_name" autocomplete="off"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="10" :offset="2">
              <el-form-item label="机构类型" prop="office_type" >
                <el-select v-model="form.office_type" clearable placeholder="请选择">
                  <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                    >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="23">
              <el-form-item label="备注信息" prop="remarks" >
                <el-input size="small" type="textarea" :rows="4" v-model="form.remarks" autocomplete="off"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormEdit = false, resetForm()">取 消</el-button>
          <el-button type="primary" @click="handleEditInfo()">确 定</el-button>
        </div>
      </el-dialog>

    </el-main>
  </el-container>
</template>

<script>
import Api from "@/api/api";
import http from '@/utils/http'
import { viewBtn } from '@/utils/utils'
import { validateBlank } from '@/utils/validators'
import { getParters } from '@/utils/treeSort'

export default {
  data() {
    return{
        //查询模块状态
        radio: 1,
        //输入框合法状态
        boolean : true,
        //模态框状态
        dialogFormInsert: false,
        dialogFormEdit: false,

        rules : {

        },
        //初始表单数组
        defaultForm: {
        },
        //机构信息表单数组
        form: {
          parent_code : '',
          office_name : '',
          office_code : '',
          full_name : '',
          tree_sort : '',
          office_type : '',
          remarks : '',
        },
        //机构表格表头
        manageTitle: [
          { label: "机构名称",prop: "office_name", type: "name" },
          { label: "机构代码",prop: "office_code", type: "normal" },
          { label: "机构全称",prop: "full_name", type: "normal" },
          { label: "排序号",prop: "tree_sort", type: "normal", width: "80" },
          { label: "机构类型",prop: "office_type", type: "format" },
          { label: "备注信息",prop: "remarks", type: "normal" },
          { label: "操作",prop: "", type: "operate" },
        ],
        //总数据
        manageData: [],
        //树形数据
        treeData: [],
        //表格定位
        currentRow: null,
        //双向绑定input
        input : "",
        //多选选项
        options: [{
          value: '1',
          label: '省级公司'
        }, {
          value: '2',
          label: '市级公司'
        },{
          value: '3',
          label: '部门'
        },],
        value: '',
      }
    },
    created: function() {
      const that = this;
      that.defaultForm = JSON.parse(JSON.stringify(that.form));
    },
    mounted:function() {
        this.selectInfo()
    },
    methods: {
      //获取vue实例
      vueObject() {
        var self = this
        return self
      },
      //隐藏和展现按钮
      viewButton(){
        viewBtn(this.vueObject())
      },
      //初始化数据
      resetForm() {
        this.form = JSON.parse(JSON.stringify(this.defaultForm));
      },


      handleCurrentChange(val) {
        this.currentRow = val;
      },
      //判断区域类型
      formatOfficeType: function (row, column, cellValue) {
        if (cellValue === "1"){
          return '省级公司';
        }else if (cellValue === "2"){
          return '市级公司';
        }else if (cellValue === "3"){
          return '部门';
        }
      },

      //新增机构操作
      handleInsertInfo() {
        this.$confirm('是否确定要新增该机构?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          if(this.boolean == true){
            this.insertInfo()
            this.dialogFormInsert = false
          }
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消新增'
          });          
        });
      },
      //新增下级公司操作
      handleInsertInfo(index, row) {
        if(this.dialogFormInsert === false){
          this.dialogFormInsert = true;
          this.form.parent_code = row.office_code;
          this.form.office_code = row.office_code + '1';
        } else {
          this.$confirm('是否确定要新增下级机构?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            if(this.boolean == true){
              this.insertInfo()
              this.dialogFormInsert = false
            }
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
      //删除公司操作
      handleDeleteInfo(index, row){
        this.$confirm('此操作将永久删除该机构, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.deleteInfo(index, row)
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
      },
      //修改公司操作
      handleEditInfo(index, row) {
        if(this.dialogFormEdit === false){
          this.dialogFormEdit = true;
          this.form = row;
        } else {
          this.$confirm('此操作将修改该机构信息, 是否继续?', '提示', {
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

      //查询公司信息
      selectInfo() {
        return new Promise((resolve, reject) =>{
          http({
            method: 'post',
            url: Api.queryOfficeApi,
            params: {
              office_name : this.form.office_name,
              office_code : this.form.office_code,
              full_name : this.form.full_name,
              office_type : this.form.office_type,
            }
          }).then(res=>{
              this.manageData = res;
              if(this.form.office_name == '' && this.form.office_code =='' && this.form.full_name == '' && this.form.office_type ==''){
                this.treeData = getParters(res);
              } else {
                this.treeData = res;
              }
          }).catch(err => {
            console.log(err)
            this.$message({
            type: 'waring',
            message: '查询失败!'
            });
          })
        })
      },
      //新增公司信息
      insertInfo() {
        return new Promise((resolve, reject) =>{
          http({
            method: 'post',
            url: Api.insertOfficeApi,
            params: {
              parent_code : this.form.parent_code,
              office_name : this.form.office_name,
              office_code : this.form.office_code,
              full_name : this.form.full_name,
              tree_sort : this.form.tree_sort,
              office_type : this.form.office_type,
              remarks : this.form.remarks,
            },
          }).then(res=>{
            this.$message({
              type: 'success',
              message: '新增成功!'
            });
            this.selectInfo()
          }).catch(err => {
            console.log(err)
            this.$message({
              type: 'warning',
              message: '新增失败!'
            });
          })
        })
      },
      //删除公司信息
      deleteInfo(index, row) {
        return new Promise((resolve, reject) =>{
          http({
            method: 'post',
            url: Api.deleteOfficeApi,
            params: {
              office_code: row.office_code,
            }
          }).then(res=>{
            this.$message({
            type: 'success',
            message: '删除成功!'
            });
            this.selectInfo()
          }).catch(err => {
            console.log(err)
            this.$message({
            type: 'waring',
            message: '删除失败!'
            });
          })
        })
      },
      //修改公司信息
      updateInfo() {
        return new Promise((resolve, reject) =>{
          http({
            method: 'post',
            url: Api.updateOfficeApi,
            params: {
              parent_code : this.form.parent_code,
              office_name : this.form.office_name,
              office_code : this.form.office_code,
              full_name : this.form.full_name,
              tree_sort : this.form.tree_sort,
              office_type : this.form.office_type,
              remarks : this.form.remarks,
            }
          }).then(res=>{
            this.$message({
            type: 'success',
            message: '修改成功!'
            });
            this.dialogFormEdit = false;
          }).catch(err => {
            console.log(err)
            this.$message({
            type: 'waring',
            message: '修改失败!'
            });
          })
        })
      },

  }
}
</script>

<style scoped>
@import "../../css/manage.css";

</style>
