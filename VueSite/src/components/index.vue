<template>
    <el-container>
        <!-- 导航栏 -->
        <el-header class="index-header">
            <el-menu
            :default-active="activeIndex1"
            class="el-menu-demo"
            mode="horizontal"
            @select="handleSelect"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#fff">
                <el-menu-item index="1" 
                :style="headerTitleStyles"
                v-on:click="controlNavMenu" >VueSite</el-menu-item>
                <el-submenu index="2" 
                :style="headerStyles" >
                    <template slot="title"><i class="el-icon-user" style="font-size:25px;width:30px" title="用户设置"></i>{{userName}}</template>
                    <router-link to="/manage">
                        <el-menu-item index="2-1" @click="handleTabsEdit('个人中心', 'add', 'personalCenter')">个人中心</el-menu-item>
                    </router-link>
                    <el-menu-item index="2-2" @click="logOut()" >退出登录</el-menu-item>
                </el-submenu>
            </el-menu>
        </el-header>
        <el-container>
            <!-- 侧边栏 -->
            <el-aside class="index-aside" v-bind:style="asideStyles">
                <el-menu default-active="1-4-1" 
                class="el-menu-vertical-demo" 
                :unique-opened="true" 
                @open="handleOpen" 
                @close="handleClose" 
                :collapse="isCollapse" 
                v-bind:style="asideMenuStyles">
                    <el-submenu index="1" v-if="roles == '0' || roles == '1' ">
                        <template slot="title">
                            <i class="el-icon-more-outline"></i>
                            <span slot="title">组织管理</span>
                        </template>
                        <el-menu-item-group>
                             <router-link to="/manage">
                                <el-menu-item index="1-1" @click="handleTabsEdit('用户管理', 'add', 'userManage')">用户管理</el-menu-item>
                             </router-link>
                            <router-link to="/manage">
                                <el-menu-item index="1-2" @click="handleTabsEdit('机构管理', 'add', 'officeManage')">机构管理</el-menu-item>
                             </router-link>
                             <router-link to="/manage">
                                <el-menu-item index="1-3" @click="handleTabsEdit('公司管理', 'add', 'companyManage')">公司管理</el-menu-item>
                             </router-link>
                            <router-link to="/manage">
                                <el-menu-item index="1-4" @click="handleTabsEdit('岗位管理', 'add', 'postManage')">岗位管理</el-menu-item>
                             </router-link>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-submenu index="2" v-if="roles == '1' ">
                        <template slot="title">
                            <i class="el-icon-lock"></i>
                            <span slot="title">权限管理</span>
                        </template>
                        <el-menu-item-group>
                             <router-link to="/manage">
                                <el-menu-item index="2-1" @click="handleTabsEdit('二级管理员', 'add', 'secondAdmin')">二级管理员</el-menu-item>
                             </router-link>
                             <router-link to="/manage">
                                <el-menu-item index="2-2" @click="handleTabsEdit('系统管理员', 'add', 'systemAdmin')">系统管理员</el-menu-item>
                             </router-link>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-submenu index="3" v-if="roles == '0' || roles == '1' ">
                        <template slot="title">
                            <i class="el-icon-setting"></i>
                            <span slot="title">系统设置</span>
                        </template>
                        <el-menu-item-group>
                            <router-link to="/manage">
                                <el-menu-item index="3-1" @click="handleTabsEdit('行政区划', 'add', 'administrativeArea')" >行政区划</el-menu-item>
                            </router-link>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-submenu index="4">
                        <template slot="title">
                            <i class="el-icon-view"></i>
                            <span slot="title">系统监控</span>
                        </template>
                        <el-menu-item-group>
                            <router-link to="/manage">
                                <el-menu-item index="4-1" @click="handleTabsEdit('数据监控', 'add', 'dataMonitor')" >数据监控</el-menu-item>
                             </router-link>
                            <router-link to="/manage">
                                <el-menu-item index="4-2" @click="handleTabsEdit('服务器监控', 'add', 'serverMonitor')" >服务器监控</el-menu-item>
                            </router-link>
                            <router-link to="/manage">
                                <el-menu-item index="4-3" @click="handleTabsEdit('存储监控', 'add', 'storageMonitoring')" >存储监控</el-menu-item>
                            </router-link>
                        </el-menu-item-group>
                    </el-submenu>
                </el-menu>
            </el-aside>
            <el-container>
            <!-- 主页面 -->
            <el-main class="index-main" >
                <!-- 标签页 -->
                <el-tabs 
                style="margin:0;"
                v-model="editableTabsValue" 
                type="card" 
                closable 
                @edit="handleTabsEdit">
                    
                    <template v-for="(item, index) in editableTabs">
                        <el-tab-pane
                        :key="item.name"
                        :label="item.title"
                        :name="item.name">
                            <router-view :name="item.componentName"></router-view>
                        </el-tab-pane>
                    </template>
                </el-tabs>
            </el-main>
            </el-container>
        </el-container>
    </el-container>
</template>

<script>
import { getUserName,setRole,getRole,removeToken,removeRole } from "@/utils/auth"
export default {
    data() {
        return {
            activeIndex1: '1',
            //用户名
            userName: 'test',
            //信息数
            messageNumber: '3',
            //是否打开侧边栏
            isCollapse: false,
            //获取屏幕高度
            fullHeight: document.documentElement.clientHeight,
            //权限级别
            roles : '',
            editableTabsValue: '1',
            editableTabs: [
                {  
                    title: '首页',
                    name: '1',
                    componentName: 'home',
                },
            ],
            tabIndex: 2,
            
            //内联式css
            headerTitleStyles:{
                float:'left',
                width:'200px',
                'font-size' : '24px',
                'border-bottom-color' : 'rgb(84, 92, 100)',
                padding:0,
            },
            headerStyles:{
                float:'right',
                width: '200px',
                'border-bottom-color' : 'rgb(84, 92, 100)',
                'margin-right' : '5px',
            },
            asideStyles:{
                width:'201px',
            },
            asideMenuStyles:{
                height: this.fullHeight+'px',
            },
        };
    },
    created: function(){
    },
    mounted:function() {
        const that = this;
        //载入时设置aside高度
        that.asideMenuStyles.height = that.fullHeight - 76 + 'px';
        //监视屏幕高度变化
        window.onresize = () => {
            return (() => {
            window.fullHeight = document.documentElement.clientHeight
            that.fullHeight = window.fullHeight - 76
            that.asideMenuStyles.height = that.fullHeight + 'px'
            })()
        };
        console.log(getRole())
        setTimeout(() =>{
            that.getRoleInfo();
            that.getName();
        },600)
    },
    watch: {
        //监视window.onresize值变化
        fullHeight (val) {
            if(!this.timer) {
            this.fullHeight = val
            this.timer = true
            let that = this
            setTimeout(function (){
                that.timer = false
            },400)
            };
        },
    },
    computed: {
    },
    methods: {
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        },
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        },
        //控制aside展出与缩进
        controlNavMenu() {
            if(this.isCollapse == false){
                this.isCollapse = true; 
                this.asideStyles.width = "65px";
            } else {
                this.asideStyles.width = "201px";
                this.isCollapse = false;
            };
        },
        //操作标签页
        handleTabsEdit(targetName, action, componentName) {
            let tabs = this.editableTabs;
            if (action === 'add') {
                var boolean = true;
                tabs.forEach((tab, index) => {
                    if (tab.title === targetName) {
                        this.editableTabsValue = tab.name;
                        boolean = false;
                    }
                })
                if(boolean){
                    let newTabName = ++this.tabIndex + '';
                    console.log(this.tabIndex)
                    this.editableTabs.push({
                        title: targetName,
                        name: newTabName,
                        componentName: componentName,
                    });
                    this.editableTabsValue = newTabName;
                }
            }
            if (action === 'remove') {
                let activeName = this.editableTabsValue;
                if (activeName === targetName) {
                    tabs.forEach((tab, index) => {
                        if (tab.name === targetName) {
                            let nextTab = tabs[index + 1] || tabs[index - 1];
                            if (nextTab) {
                            activeName = nextTab.name;
                            }
                        }
                    });
                }
        
                this.editableTabsValue = activeName;
                this.editableTabs = tabs.filter(tab => tab.name !== targetName);
            }
        },
        //获取权限
        getRoleInfo (){
            this.roles = getRole()
        },
        //获取用户名
        getName() {
            this.userName = getUserName()
        },
        //退出登录
        logOut (){
            var router = this.$router
            removeToken();
            router.go(0)
        },
    }
}
</script>

<style scoped>

    .index-header {
        padding: 0;
        margin: 0;
        background-color: #B3C0D1;
        color: #333;
        text-align: center;
        line-height: 60px;
    }
    
    .index-aside {
        color: #333;
        text-align: center;
        line-height: 200px;
    }

    .index-main {
        background-color: rgb(255, 255, 255);
        color: #333;
        text-align: center;
        padding: 0;
    }

    .el-menu-vertical-demo:not(.el-menu--collapse) {
        width: 200px;
    }

    .el-menu-item {
        text-align: center;
    }
    
    .el-menu--horizontal>.el-menu-item {
        float: right;
        text-align: center;
    }

    .i {
        width: 100%;
        display: block;
    }

    .el-tabs__header{
        margin: 0;
    }

    .clearfix:before,.clearfix:after {
        display: table;
        content: "";
    }
    
    .clearfix:after {
        clear: both
    }

    .el-tabs__nav .el-tabs__item:nth-child(1) span{
    display: none;
    }

</style>
