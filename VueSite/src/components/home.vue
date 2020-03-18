<template>
  <el-container>
        <el-header style="height:120px;margin-top:15px">
            <el-row :gutter="15">
                <el-col :span="6" >
                    <el-card :body-style="{ padding: '0px' }">
                        <el-col :span="8" style="padding:0;">
                            <div class="box-icon" style="background-color: #48D1CC!important;">
                                <i class="fas fa-cog" style="color:white"></i>
                            </div>
                        </el-col>
                        <div>
                            <span class="box-span"><br/>CPU总使用率<br/>{{cpuPercent}}%</span>
                        </div>
                    </el-card>
                </el-col>
                <el-col :span="6" >
                    <el-card :body-style="{ padding: '0px' }">
                        <el-col :span="8" style="padding:0;">
                            <div class="box-icon" style="background-color: #dd4b39!important;">
                                <i class="fas fa-user" style="color:white"></i>
                            </div>
                        </el-col>
                        <div>
                            <span class="box-span"><br/>用户数<br/>{{userNumber}}人</span>
                        </div>
                    </el-card>
                </el-col>
                <el-col :span="6" >
                    <el-card :body-style="{ padding: '0px' }">
                        <el-col :span="8" style="padding:0;">
                            <div class="box-icon" style="background-color: #218868!important;">
                                <i class="fab fa-internet-explorer" style="color:white"></i>
                            </div>
                        </el-col>
                        <div>
                            <span class="box-span"><br/>在线用户数<br/>{{httpUserNumber}}人</span>
                        </div>
                    </el-card>
                </el-col>
                <el-col :span="6" >
                    <el-card :body-style="{ padding: '0px' }">
                        <el-col :span="8" style="padding:0;">
                            <div class="box-icon" style="background-color: #EEB422!important;">
                                <i class="fas fa-users" style="color:white"></i>
                            </div>
                        </el-col>
                        <div>
                            <span class="box-span"><br/>新用户数<br/>{{newUserNumber}}人</span>
                        </div>
                    </el-card>
                </el-col>
            </el-row>
        </el-header>

        <el-main>
            <el-card class="box-card">
                <div slot="header" class="clearfix" style="text-align:left">
                    <span>信息报告</span>
                </div>
                <el-row :gutter="15">
                    <el-col :span="16">
                        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
                        <div id="myChartReport" style="height:300px;"></div>
                    </el-col>
                    <el-col :span="7">
                        <p style="font-size:24px">季度目标</p>
                        <div>
                            <p style="margin-bottom:0">
                                <span style="float:left">新增用户数</span>
                                <span style="float:right">259/600</span>
                            </p>
                            <el-progress :text-inside="true" :stroke-width="17" :percentage="43"></el-progress>
                        </div>
                        <div>
                            <p style="margin-bottom:0">
                                <span style="float:left">查询访问量</span>
                                <span style="float:right">4396/7500</span>
                            </p>
                            <el-progress :text-inside="true" :stroke-width="17" :percentage="58" color="#1874CD"></el-progress>
                        </div>
                        <div>
                            <p style="margin-bottom:0">
                                <span style="float:left">新增公司数</span>
                                <span style="float:right">07/30</span>
                            </p>
                            <el-progress :text-inside="true" :stroke-width="17" :percentage="23" color="#8e71c7"></el-progress>
                        </div>
                        <div>
                            <p style="margin-bottom:0">
                                <span style="float:left">新增机构数</span>
                                <span style="float:right">74/150</span>
                            </p>
                            <el-progress :text-inside="true" :stroke-width="17" :percentage="49" status="success"></el-progress>
                        </div>
                    </el-col>
                </el-row>
            </el-card>
        </el-main>

        <el-footer>
            <p>
                <span>© 2019 VueSite Demo - Powered By JeeSite </span>
                <span>当前版本： V1.0</span>
            </p>
        </el-footer>
    </el-container>
</template>

<script>
import Api from "@/api/api"
import http from '@/utils/http'

export default {
    data() {
        return {
            currentDate: new Date(),
            currentMouth: new Date().getMonth + 1,
            cpuPercent: 1,
            userNumber: 1,
            httpUserNumber: 1,
            newUserNumber:5,
        };
    },
    mounted:function() {
        this.setChart()
        this.selectCPUInfo()
        this.selectInfo()
        setInterval(this.selectCPUInfo, 2000)
    },
    methods: {
        setChart() {
            this.setChartReport();
        },
        //查询CPU使用率
        selectCPUInfo() {
            return new Promise((resolve, reject) =>{
                http({
                    method: 'post',
                    url: Api.getCPUInfo,
                    params: {
                    }
                }).then(res=>{
                    //this.cpuPercent = parseInt((res[0].CPUCombined + res[1].CPUCombined + res[2].CPUCombined + res[3].CPUCombined)/4 * 100);
                    this.cpuPercent = parseInt(res[0].CPUCombined * 100);
                }).catch(err => {
                    console.log(err)
                    this.$message({
                    type: 'waring',
                    message: '查询失败!'
                    });
                })
            })
        },
        //查询用户信息
        selectInfo() {
            return new Promise((resolve, reject) =>{
                http({
                method: 'post',
                url: Api.queryUserApi,
                params: {
                }
                }).then(res=>{
                this.userNumber = res.length;
                }).catch(err => {
                console.log(err)
                this.$message({
                type: 'waring',
                message: '查询失败!'
                });
                })
            })
        },
        setChartReport() {
            // 基于准备好的dom，初始化echarts实例
            let myChartReport = this.$echarts.init(document.getElementById('myChartReport'))

            let option = {
                title: {
                    text: '近六个月数据报告'
                },
                tooltip : {
                    trigger: 'axis',
                },
                legend: {
                    data:['新增用户数量','新增公司数量','新增机构数量']
                },
                toolbox: {
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis : [
                    {
                        type : 'category',
                        boundaryGap : false,
                        data : ['一','二','三','四','五','六']
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        name:'新增用户数量',
                        type:'line',
                        data:[122, 137, 112, 162, 189, 201]
                    },
                    {
                        name:'新增公司数量',
                        type:'line',
                        data:[3, 4, 2, 9, 11, 21]
                    },
                    {
                        name:'新增机构数量',
                        type:'line',
                        data:[33, 41, 19, 87, 107, 176]
                    },

                ]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChartReport.setOption(option);
        }
    }
}
</script>

<style scoped>
  .time {
    font-size: 13px;
    color: #999;
  }
  
  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
      display: table;
      content: "";
  }
  
  .clearfix:after {
      clear: both
  }

  .box-icon{
    border-top-left-radius: 2px;
    border-top-right-radius: 0;
    border-bottom-right-radius: 0;
    border-bottom-left-radius: 2px;
    display: block;
    float: left;
    height: 97px;
    width: 97px;
    text-align: center;
    font-size: 45px;
    line-height: 103px;
    background: rgba(255, 255, 255);
  }

  .box-span{
      line-height: 25px;
  }
</style>
