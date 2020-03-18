<template>
  <el-container style="height:650px">
    
    <el-header class="borderBottom manage-title" style="height:50px;" >
      <!-- 子页面标题 -->
      <div class="manage-left">
        <i class="el-icon-map-location"></i>
        <span>行政区划</span>
      </div>
      <!-- 功能按钮 -->
      <div class="manage-right">
        <el-button plain size="small" type="info" icon="el-icon-refresh" @click="resetForm(), selectInfo()">刷新</el-button>
        <el-button plain size="small" type="success" icon="el-icon-plus" @click="resetForm(), dialogFormInsert = true">新增</el-button>
      </div>
    </el-header>


    <el-main class="no-padding-margin main">
      <!-- 查询 输入框和按钮 -->
      <div style="text-align:left;">
        <el-form :model="form" class="main-form" label-width="80px" ref="form" :rules="rules" >
          <el-row class="main-form-row">
              <el-col :span="5">
                <label>区域代码：</label>
                <el-input size="mini" v-model="form.area_code" placeholder="请输入内容" class="main-form-input"></el-input>
              </el-col>
              <el-col :span="5">
                <label>区域名称：</label>
                <el-input size="mini" v-model="form.area_name" placeholder="请输入内容" class="main-form-input"></el-input>
              </el-col>
              <el-col :span="4">
                  <el-button size="mini" type="primary" icon="el-icon-search" @click="selectInfo()">查询</el-button>
                  <el-button size="mini" icon="el-icon-search" @click="resetForm(), selectInfo()">重置</el-button>
              </el-col>
          </el-row>
        </el-form>
      </div>
      <!-- 区域表格 -->
      <div class="main-table">
        <el-table
          ref="singleTable"
          border
          stripe
          size="mini"
          :data="treeData"
          row-key="area_code"
          highlight-current-row
          @current-change="handleCurrentChange"
          style="width: 100%">
          <template v-for="(col,index) in manageTitle">
              <el-table-column v-if="col.type === 'name'" :prop="col.prop" :label="col.label" align="left" :width="col.width">
              </el-table-column>
              <el-table-column v-if="col.type === 'normal'" :prop="col.prop" :label="col.label" align="center" show-overflow-tooltip>
              </el-table-column>
              <el-table-column v-if="col.type === 'format'" :prop="col.prop" :label="col.label" align="center"  :formatter="formatAreaType" show-overflow-tooltip>
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
                    title="新增下级区域"
                    circle
                    @click="handleInsertInfo(scope.$index, scope.row)"></el-button>
                </template>
              </el-table-column>
          </template>
        </el-table>
      </div>

      <!-- 新增区域对话框 -->
      <el-dialog title="新增区域" :visible.sync="dialogFormInsert" @close='resetForm'>
        <el-form :model="form" label-width="80px" ref="form" :rules="rules">
          <el-row>
            <el-col :span="10">
              <el-form-item label="上级区域" prop="parent_code" >
                <el-input size="small" v-model="form.parent_code" autocomplete="off"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="10">
              <el-form-item label="区域名称" prop="area_name" >
                <el-input size="small" v-model="form.area_name" autocomplete="off"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="10" :offset="2">
              <el-form-item label="区域类型" prop="area_type" >
                 <el-select v-model="form.area_type" clearable placeholder="请选择">
                  <el-option
                    v-for="item in areaTypeOptions"
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

      <!-- 编辑区域对话框 -->
      <el-dialog title="编辑区域" :visible.sync="dialogFormEdit"  @close='resetForm'>
        <el-form :model="form" label-width="80px" :rules="rules">
          <el-row>
            <el-col :span="10">
              <el-form-item label="上级区域" prop="parent_code" >
                <el-input size="small" v-model="form.parent_code" autocomplete="off"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="10" >
              <el-form-item label="区域代码" prop="area_code" >
                <el-input size="small" v-model="form.area_code" autocomplete="off" disabled></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="10" :offset="2">
              <el-form-item label="区域名称" prop="area_name" >
                <el-input size="small" v-model="form.area_name" autocomplete="off"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="10" >
              <el-form-item label="区域类型" prop="area_type" >
                 <el-select v-model="form.area_type" clearable placeholder="请选择">
                  <el-option
                    v-for="item in areaTypeOptions"
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

        //初始表单数组
        defaultForm: {
        },
        //区域信息表单数组
        form: {
          parent_code : '',
          parent_name : '',
          area_code : '',
          area_name : '',
          area_type : '',
          remarks : '',
          update_date : '',
        },
        //输入规则
        rules: {
        },
        //表格头
        manageTitle: [
          { label: "区域名称",prop: "area_name", type: "name" },
          { label: "区域代码",prop: "area_code", type: "normal" },
          { label: "区域类型",prop: "area_type", type: "format" },
          { label: "备注信息",prop: "remarks", type: "normal" },
          { label: "操作",prop: "", type: "operate" },
        ],
        //总数据
        manageData: [],
        //树形数据
        treeData: [],
        //表格定位
        currentRow: null,
        //多选选项
        areaTypeOptions: [ {
          value: '1',
          label: '省份直辖市'
        }, {
          value: '2',
          label: '地市'
        }, {
          value: '3',
          label: '区县'
        }, ],
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
      //初始化数据
      resetForm() {
        this.form = JSON.parse(JSON.stringify(this.defaultForm));
      },

      
      handleCurrentChange(val) {
        this.currentRow = val;
      },
      //判断区域类型
      formatAreaType: function (row, column, cellValue) {
        if (cellValue === "1"){
          return '省份直辖市';
        }else if (cellValue === "2"){
          return '地市';
        }else if (cellValue === "3"){
          return '区县';
        }
      },


      //新增区域操作
      handleInsertInfo() {
        this.$confirm('是否确定要新增该区域?', '提示', {
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
      //新增下级区域操作
      handleInsertInfo(index, row) {
        if(this.dialogFormInsert === false){
          this.dialogFormInsert = true;
          this.form.parent_code = row.area_code;
        } else {
          this.$confirm('是否确定要新增下级区域?', '提示', {
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
      //删除区域操作
      handleDeleteInfo(index, row){
        this.$confirm('此操作将永久删除该区域, 是否继续?', '提示', {
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
      //修改区域操作
      handleEditInfo(index, row) {
        if(this.dialogFormEdit === false){
          this.dialogFormEdit = true;
          this.form = row;
        } else {
          this.$confirm('此操作将修改该区域信息, 是否继续?', '提示', {
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


      //查询区域信息
      selectInfo() {
        return new Promise((resolve, reject) =>{
          http({
            method: 'post',
            url: Api.queryAreaApi,
            params: {
              area_name : this.form.area_name,
              area_code : this.form.area_code,
            }
          }).then(res=>{
              this.manageData = res;
              if(this.form.area_name == '' && this.form.area_code ==''){
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
      //新增区域信息
      insertInfo() {
        return new Promise((resolve, reject) =>{
          http({
            method: 'post',
            url: Api.insertAreaApi,
            params: {
              parent_code : this.form.parent_code,
              area_code : this.form.area_code,
              area_name : this.form.area_name,
              area_type : this.form.area_type,
              tree_sorts : this.form.tree_sorts,
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
      //删除区域信息
      deleteInfo(index, row) {
        return new Promise((resolve, reject) =>{
          http({
            method: 'post',
            url: Api.deleteAreaApi,
            params: {
              area_code: row.area_code,
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
      //修改区域信息
      updateInfo() {
        return new Promise((resolve, reject) =>{
          http({
            method: 'post',
            url: Api.updateAreaApi,
            params: {
              parent_code : this.form.parent_code,
              area_code : this.form.area_code,
              area_name : this.form.area_name,
              area_type : this.form.area_type,
              tree_sorts : this.form.tree_sorts,
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
      }
  }
}
</script>

<style scoped>
@import "../../css/manage.css";

</style>
