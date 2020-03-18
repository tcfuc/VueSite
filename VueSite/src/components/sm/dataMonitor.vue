<template>
  <div>
    <iframe src="http://127.0.0.1:80/site/druid/index.html" :height="maxHeight" frameborder="0" scrolling="auto" style="width:100%;">
    <!-- <iframe src="http://47.103.223.240:80/site/druid/index.html" :height="maxHeight" frameborder="0" scrolling="auto" style="width:100%;"> -->
    </iframe>
  </div>
</template>

<script>
import { heightDetect,heightDetectWindows,heightTable } from '@/utils/utils'

export default {
  data() {
    return{
      //屏幕高度
      fullHeight: 0,
      //子页面最大高度
      maxHeight: 0,
      //其他组件高度
      extHeight: 116,
      //查询模块高度
      queryModuleHeight: 0,
    }
  },
  created: function() {
    const that = this;
    that.handleHeightTable()  
  },
  mounted:function() {
    const that = this;
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
    //设置表格初始高度
    handleHeightTable(){
      heightTable(this.vueObject())
    },
  }
}
</script>

<style>

</style>
