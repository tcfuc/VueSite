<template>
  <el-container>

    <el-header class="borderBottom manage-title" style="height:50px;" >
      <!-- 子页面标题 -->
      <div class="manage-left">
        <i class="el-icon-coffee"></i>
        <span>岗位管理</span>
      </div>
      <!-- 功能按钮 -->
      <div class="manage-right">
        <!-- <el-button plain size="small" type="warning" icon="el-icon-view" @click="viewButton(), resetForm()" v-if="radio === 1">查询</el-button>
        <el-button plain size="small" type="warning" icon="el-icon-view" @click="viewButton(), resetForm()" v-if="radio === 0">隐藏</el-button> -->

        <el-button plain size="small" type="info" icon="el-icon-refresh" @click="selectInfo()">刷新</el-button>
        <el-button plain size="small" type="success" icon="el-icon-plus" @click="dialogFormInsert = true, resetForm()">新增</el-button>
      </div>
    </el-header>


    <el-main  class="no-padding-margin main">
        <!-- 查询 输入框和按钮 -->
      <div style="text-align:left;">
        <el-form :model="form" class="main-form" label-width="80px" ref="form" :rules="rules" >
          <el-row class="main-form-row">
              <el-col :span="5">
                  <label>岗位名称：</label>
                  <el-input size="mini" v-model="form.post_name" placeholder="请输入内容" autocomplete="off" class="main-form-input"></el-input>
              </el-col>
              <el-col :span="5" >
                  <label>岗位编码：</label>
                  <el-input size="mini" v-model="form.post_code" placeholder="请输入内容" autocomplete="off" class="main-form-input"></el-input>
              </el-col>
              <el-col :span="5">
                  <label>岗位分类：</label>
                  <el-select size="mini" v-model="form.post_type" placeholder="请选择" class="main-form-input">
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
      <!-- 岗位表格 -->
      <div class="main-table">
        <el-table
          ref="singleTable"
          border
          stripe
          v-loading="loading"
          size="mini"
          :data="manageData"
          highlight-current-row
          :height="maxHeight"
          @current-change="handleCurrentChange"
          style="width: 100%">
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
                </template>
              </el-table-column>
          </template>
        </el-table>
      </div>

      <!-- 新增岗位对话框 -->
      <el-dialog title="新增岗位" :visible.sync="dialogFormInsert" @close='resetForm'>
        <el-form :model="form" label-width="80px" ref="form" :rules="rules">
          <el-row>
            <el-col :span="10">
              <el-form-item label="岗位名称" prop="post_name">
                <el-input size="small" v-model="form.post_name" autocomplete="off"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="10" :offset="2">
              <el-form-item label="岗位编码" prop="post_code">
                <el-input size="small" v-model="form.post_code" autocomplete="off"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item label="岗位分类" >
                <el-select size="small" v-model="form.post_type" placeholder="请选择">
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
            <el-col :span="10" :offset="2">
              <el-form-item label="排序号" >
                <el-input size="small" v-model="form.post_sort" autocomplete="off"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="23">
              <el-form-item label="备注信息" >
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

      <!-- 编辑岗位对话框 -->
      <el-dialog title="编辑岗位" :visible.sync="dialogFormEdit"  @close='resetForm'>
        <el-form :model="form" label-width="80px" :rules="rules">
          <el-row>
            <el-col :span="10">
              <el-form-item label="岗位名称" >
                <el-input size="small" v-model="form.post_name" autocomplete="off"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="10" :offset="2">
              <el-form-item label="岗位编码" >
                <el-input size="small" v-model="form.post_code" autocomplete="off" disabled ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item label="岗位分类" >
                <el-select size="small" v-model="form.post_type" placeholder="请选择">
                  <el-option label="高管" value="高管"></el-option>
                  <el-option label="中层" value="中层"></el-option>
                  <el-option label="基层" value="基层"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="10" :offset="2">
              <el-form-item label="排序号" >
                <el-input size="small" v-model="form.post_sort" autocomplete="off"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="23">
              <el-form-item label="备注信息" >
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
import Api from "@/api/api";
import http from '@/utils/http'
import { sizeChange,currentChangePage,pagination,viewBtn,heightDetect,heightDetectWindows,heightTable } from '@/utils/utils'
import { validateBlank } from '@/utils/validators'

export default {
  data() {
    return{
        //查询模块状态
        radio: 1,
        //输入框合法状态
        boolean : false,
        //模态框状态
        dialogFormInsert: false,
        dialogFormEdit: false,

        //Dialog绑定的表单数组
        defaultForm: {
        },
        //岗位信息表单数组
        form: {
          post_name : '',
          post_code : '',
          post_type : '',
          post_sort : '',
          remarks : '',
        },
        //输入规则
        rules: {
          post_code: [
            { validator: validateBlank, trigger: 'blur' },
          ],
          post_name: [
            { validator: validateBlank, trigger: 'blur' },
          ],
        },
        //表格头
        manageTitle: [
          { label: "",prop: "", type: "index" },
          { label: "岗位名称",prop: "post_name", type: "normal" },
          { label: "岗位编码",prop: "post_code", type: "normal" },
          { label: "排序号",prop: "post_sort", type: "normal" },
          { label: "岗位分类",prop: "post_type", type: "normal" },
          { label: "备注信息",prop: "remarks", type: "normal" },
          { label: "操作",prop: "", type: "operate" },
        ],
        //总数据
        manageData: [],
        //分页数据
        manageDataPage: [],
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
        extHeight: 310 ,
        //查询模块高度
        queryModuleHeight: 63,

        //多选框
        options: [{
          value: '',
          label: ''
        }, {
          value: '高管',
          label: '高管'
        }, {
          value: '中层',
          label: '中层'
        }, {
          value: '基层',
          label: '基层'
        }, ],
      }
    },
    created: function() {
      const that = this;
      that.handleHeightTable()  
      that.defaultForm = JSON.parse(JSON.stringify(that.form));
    },
    mounted: function() {
      const that = this;
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

      handleCurrentChange(val) {
        this.currentRow = val;
      },
      //初始化数据
      resetForm() {
        this.form = JSON.parse(JSON.stringify(this.defaultForm));
      },
      //检查数据是否合法
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.boolean = true;
          } else {
            this.$message({
              type: 'warning',
              message: '您填写的信息有误，请根据提示修正。'
            }); 
            this.boolean = false;
          }
        })
      },

      //新增岗位操作
      handleInsertInfo() {
        this.$confirm('是否确定要新增此岗位?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.submitForm('form');
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
      //删除岗位操作
      handleDeleteInfo(index, row){
        this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
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
      //修改岗位操作
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

      //查询岗位信息
      selectInfo() {
        return new Promise((resolve, reject) =>{
          http({
            method: 'post',
            url: Api.queryPostApi,
            params: {
              post_name : this.form.post_name,
              post_code : this.form.post_code,
              post_type : this.form.post_type,
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
      //新增岗位信息
      insertInfo() {
        return new Promise((resolve, reject) =>{
          http({
            method: 'post',
            url: Api.insertPostApi,
            params: {
              post_name : this.form.post_name,
              post_code : this.form.post_code,
              post_type : this.form.post_type,
              post_sort : this.form.post_sort,
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
      //删除岗位信息
      deleteInfo(index, row) {
        return new Promise((resolve, reject) =>{
          http({
            method: 'post',
            url: Api.deletePostApi,
            params: {
              post_code: row.post_code,
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
      //修改岗位信息
      updateInfo() {
        return new Promise((resolve, reject) =>{
          http({
            method: 'post',
            url: Api.updatePostApi,
            params: {
              post_name : this.form.post_name,
              post_code : this.form.post_code,
              post_type : this.form.post_type,
              post_sort : this.form.post_sort,
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
