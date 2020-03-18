<template>
  <el-container>
    <el-main>
      <el-row>
        <el-col :span="6" >
          <el-progress type="circle" :width='200' :stroke-width="13" :percentage="cpuPercent" status="text">CPU总使用率<br/>{{cpuPercent}}%</el-progress>
        </el-col>
        <el-col :span="4" :offset="1">
          <el-progress type="circle" :width='180' :stroke-width="13" :percentage="cpu1Percent" color="#00CD66" status="text">CPU1使用率<br/>{{cpu1Percent}}%</el-progress>
        </el-col>
        <el-col :span="4">
          <el-progress type="circle" :width='180' :stroke-width="13" :percentage="cpu2Percent" color="#00CD66" status="text">CPU2使用率<br/>{{cpu2Percent}}%</el-progress>
        </el-col>
        <el-col :span="4">
          <el-progress type="circle" :width='180' :stroke-width="13" :percentage="cpu3Percent" color="#00CD66" status="text">CPU3使用率<br/>{{cpu3Percent}}%</el-progress>
        </el-col>
        <el-col :span="4">
          <el-progress type="circle" :width='180' :stroke-width="13" :percentage="cpu4Percent" color="#00CD66" status="text">CPU4使用率<br/>{{cpu4Percent}}%</el-progress>
        </el-col>
      </el-row>
    </el-main>
    <el-footer style="height:400px">
      <el-card class="box-card">
        <div slot="header" class="clearfix" style="text-align:left">
          <span style="font-size:24px">服务器信息</span>
        </div>
        <div class="text item">
          <el-row >
            <el-col :span="4">服务器名称</el-col>
            <el-col :span="8">{{ this.serverName }}</el-col>
            <el-col :span="4">操作系统</el-col>
            <el-col :span="8">{{ operaSystem }}</el-col>
          </el-row>
          <el-divider></el-divider>
          <el-row >
            <el-col :span="4">服务器IP</el-col>
            <el-col :span="8">{{ serverIP }}</el-col>
            <el-col :span="4">系统架构</el-col>
            <el-col :span="8">{{ systemArchitecture }}</el-col>
          </el-row>
        </div>
      </el-card>

      <el-card class="box-card">
        <div slot="header" class="clearfix" style="text-align:left">
          <span style="font-size:24px">运行环境信息</span>
        </div>
        <div class="text item">
          <el-row >
            <el-col :span="4">Java名称</el-col>
            <el-col :span="8">{{ javaName }}</el-col>
            <el-col :span="4">Java版本</el-col>
            <el-col :span="8">{{ javaVersion }}</el-col>
          </el-row>
          <el-divider></el-divider>
          <el-row >
            <el-col :span="4">安装路径</el-col>
            <el-col :span="8">{{ installPath }}</el-col>
          </el-row>
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

        serverName: '1',
        operaSystem: '1',
        serverIP: '1',
        systemArchitecture: '1',

        javaName: '1',
        javaVersion: '1',
        installPath: '1',

        cpuPercent: 0,
        cpu1Percent: 0,
        cpu2Percent: 0,
        cpu3Percent: 0,
        cpu4Percent: 0,
      };
    },
    mounted:function() {
      this.selectServerInfo()
      this.selectCPUInfo()
      setInterval(this.selectCPUInfo, 2000)
    },
    methods: {
      selectServerInfo() {
        return new Promise((resolve, reject) =>{
          http({
            method: 'post',
            url: Api.getServerInfo,
            params: {
            }
          }).then(res=>{
            this.serverName = res.ServerName ;
            this.operaSystem = res.OperaSystem ;
            this.serverIP = res.ServerIP ;
            this.systemArchitecture = res.SystemArchitecture ;

            this.javaName = res.JavaName ;
            this.javaVersion = res.JavaVersion ;
            this.installPath = res.InstallPath ;
          }).catch(err => {
            console.log(err)
            this.$message({
            type: 'waring',
            message: '查询失败!'
            });
          })
        })
      },
      selectCPUInfo() {
        return new Promise((resolve, reject) =>{
          http({
            method: 'post',
            url: Api.getCPUInfo,
            params: {
            }
          }).then(res=>{
            this.cpu1Percent = parseInt(res[0].CPUCombined * 100);
            // this.cpu2Percent = parseInt(res[1].CPUCombined * 100);
            // this.cpu3Percent = parseInt(res[2].CPUCombined * 100);
            // this.cpu4Percent = parseInt(res[3].CPUCombined * 100);
            // this.cpuPercent = (this.cpu1Percent + this.cpu2Percent + this.cpu3Percent + this.cpu4Percent)/4
            this.cpuPercent = this.cpu1Percent

          }).catch(err => {
            console.log(err)
            this.$message({
            type: 'waring',
            message: '查询失败!'
            });
          })
        })
      },
    }
  }
</script>
<style scoped>
  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 0px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }
</style>
