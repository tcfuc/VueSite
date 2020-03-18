// 　　 へ　　　　　／|
// 　　/＼7　　　 ∠＿/
// 　 /　│　　 ／　／             我
// 　│　Z ＿,＜　／　　 /`ヽ      
// 　│　　　　　ヽ　　 /　　〉     是
// 　 Y　　　　　`　 /　　/       
// 　ｲ●　､　●　　⊂⊃〈　  /        皮
// 　()　 V　　　　|　＼〈
// 　　>ｰ ､_　 ィ　 │ ／／        卡
// 　 / へ　　 /　ﾉ＜| ＼＼       
// 　 ヽ_ﾉ　　(_／　 │／／        丘
// 　　7　　　　　　　|／
// 　　＞―r￣￣`ｰ―＿

// 控制隐藏和展现查询div
function viewBtn(self) {
  if(self.radio == 1){
    self.radio = 0;
  } else {
    self.radio = 1;
  }
  viewHeight(self)
}

function viewHeight(self) {
  if(self.radio == 0){
    self.maxHeight -= self.queryModuleHeight
  } else {
    self.maxHeight += self.queryModuleHeight
  }
}

// 　　 へ　　　　　／|
// 　　/＼7　　　 ∠＿/
// 　 /　│　　 ／　／             我
// 　│　Z ＿,＜　／　　 /`ヽ      
// 　│　　　　　ヽ　　 /　　〉     是
// 　 Y　　　　　`　 /　　/       
// 　ｲ●　､　●　　⊂⊃〈　  /        皮
// 　()　 V　　　　|　＼〈
// 　　>ｰ ､_　 ィ　 │ ／／        卡
// 　 / へ　　 /　ﾉ＜| ＼＼       
// 　 ヽ_ﾉ　　(_／　 │／／        丘
// 　　7　　　　　　　|／
// 　　＞―r￣￣`ｰ―＿

// 分页组件
// 确定分页信息条数
function sizeChange(self,val) {
  self.pageSize = val
  currentChangePage(self,self.currentPage);
}
// 确定分页页码
function currentChangePage(self,val) {
  self.currentPage = val
  self.manageDataPage = pagination(self.manageData,self.manageDataPage,self.currentPage,self.pageSize);
}
// 进行分页数据切割
function pagination(list,tempList,currentPage,pageSize) { 
  let from = (currentPage - 1) * pageSize;
  let to = currentPage * pageSize;
  var tempList = [];
  for (; from < to; from++) {
    if (list[from]) {
      tempList.push(list[from]);
    }
  }
  console.log(tempList);
  return tempList;
}

// 　　 へ　　　　　／|
// 　　/＼7　　　 ∠＿/
// 　 /　│　　 ／　／             我
// 　│　Z ＿,＜　／　　 /`ヽ      
// 　│　　　　　ヽ　　 /　　〉     是
// 　 Y　　　　　`　 /　　/       
// 　ｲ●　､　●　　⊂⊃〈　  /        皮
// 　()　 V　　　　|　＼〈
// 　　>ｰ ､_　 ィ　 │ ／／        卡
// 　 / へ　　 /　ﾉ＜| ＼＼       
// 　 ヽ_ﾉ　　(_／　 │／／        丘
// 　　7　　　　　　　|／
// 　　＞―r￣￣`ｰ―＿

// 高度监视组件
function heightDetect(self,val){
  if(!self.timer) {
    self.fullHeight = val
    self.timer = true
    setTimeout(function (){
      self.timer = false
    },400)
  }
}

// 获取屏幕高度
function heightDetectWindows(self){
  window.onresize = () => {
    return (() => {
      window.fullHeight = document.documentElement.clientHeight
      self.fullHeight = window.fullHeight
    })()
  };
}

// 设置表格初始高度
function heightTable(self){
  self.fullHeight = document.documentElement.clientHeight
  self.maxHeight = self.fullHeight - self.extHeight
}


export {
  sizeChange,
  currentChangePage,
  pagination,
  viewBtn,
  viewHeight,
  heightDetect,
  heightDetectWindows,
  heightTable,
}