<template>
  <el-container>
    <el-main>
      <el-row>
        <el-col :span="8">
          <el-progress type="circle" :width='320' :stroke-width="13" :percentage="100" status="text">内存总容量<br/>{{totalMemory}}</el-progress>
        </el-col>
        <el-col :span="8">
          <el-progress type="circle" :width='320' :stroke-width="13" :percentage="usedPercent" color="#CD3333" status="text">已使用容量<br/>{{usedMemory}}</el-progress>
        </el-col>
        <el-col :span="8">
          <el-progress type="circle" :width='320' :stroke-width="13" :percentage="remainPercent" color="#00CD66" status="text">剩余总容量<br/>{{remainMemory}}</el-progress>
        </el-col>
      </el-row>
    </el-main>
    <el-footer style="height:250px">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>硬盘容量</span>
        </div>
        <div>
          <el-table
            ref="singleTable"
            border
            stripe
            size="mini"
            :data="manageData"
            row-key="company_code"
            highlight-current-row
            @current-change="handleCurrentChange"
            style="width: 100%">
            <template v-for="(col,index) in manageTitle">
                <el-table-column v-if="col.type === 'index'" :prop="col.prop" :label="col.label" align="center" type="index" width="40">
                </el-table-column>
                <el-table-column v-if="col.type === 'normal'" :prop="col.prop" :label="col.label" align="center" :width="col.width">
                </el-table-column>
            </template>
          </el-table>
        </div>
      </el-card>
    </el-footer>
  </el-container>
</template>

<script>
import Api from "@/api/api";
import http from '@/utils/http'
  export default {
    data() {
      return {
        activeNames: ['1'],

        totalMemory: '1',
        usedMemory: '1',
        usedPercent: 0,
        remainMemory: '1',
        remainPercent: 0,

        //表格头
        manageTitle: [
          { label: "",prop: "", type: "index" },
          { label: "盘符路径",prop: "DiskName", type: "normal" },
          { label: "文件系统",prop: "DiskSysTypeName", type: "normal" },
          { label: "盘符类型",prop: "DiskTypeName", type: "normal", width: "80" },
          { label: "总大小(MB)",prop: "DiskTotal", type: "normal" },
          { label: "可用大小(MB)",prop: "DiskAvail", type: "normal" },
          { label: "已用大小(MB)",prop: "DiskUsed", type: "normal" },
          { label: "已用百分比",prop: "DiskUsePercent", type: "normal" },
       ],
        //总数据
        manageData: [],
        //表格定位
        currentRow: null,
      };
    },
    mounted:function() {
      this.selectServerInfo()
      this.selectDiskInfo()
      setInterval(this.selectServerInfo, 3500)
    },
    methods: {
      handleChange(val) {
        console.log(val);
      },
      handleCurrentChange(){
      },
      selectServerInfo() {
        return new Promise((resolve, reject) =>{
          http({
            method: 'post',
            url: Api.getServerInfo,
            params: {
            }
          }).then(res=>{
            this.totalMemory = res.TotalMemory + 'MB' ;
            this.remainMemory = res.RemainMemory + 'MB' ;
            this.usedMemory = res.Usedmemory + 'MB' ;
            this.remainPercent = parseInt(res.RemainMemory)/parseInt(res.TotalMemory) * 100;
            this.usedPercent = parseInt(res.Usedmemory)/parseInt(res.TotalMemory) * 100;
          }).catch(err => {
            console.log(err)
            this.$message({
            type: 'waring',
            message: '查询失败!'
            });
          })
        })
      },
      selectDiskInfo() {
        return new Promise((resolve, reject) =>{
          http({
            method: 'post',
            url: Api.getDiskInfo,
            params: {
            }
          }).then(res=>{
            this.manageData = res
          }).catch(err => {
            console.log(err)
            this.$message({
            type: 'waring',
            message: '查询失败!'
            });
          })
        })
      }
    }
  }
</script>

<style>

</style>
