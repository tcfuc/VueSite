
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

// 树形化组件
// 获取父节点
function getParters(jsonData){
  var parentsData = [];
  for(var i = 0; i<jsonData.length; i++){
    if(jsonData[i].parent_code == 0){
      jsonData[i]["children"] = [];
      parentsData.push(jsonData[i]);
    }
  }
  for(var i = 0; i<parentsData.length; i++){
    if(parentsData[i].company_code != undefined){
      parentsData[i].children = getCompanyChildren(parentsData[i], jsonData);
    }
    if(parentsData[i].office_code != undefined){
      parentsData[i].children = getOfficeChildren(parentsData[i], jsonData);
    }
    if(parentsData[i].area_code != undefined){
      parentsData[i].children = getAreaChildren(parentsData[i], jsonData);
    }
  }
  console.log(parentsData);
  return parentsData;
}

// 获取office表children节点
function getOfficeChildren(parentsData, jsonData){
  var childrenData = [];
  for(var i = 0; i<jsonData.length; i++){
    if(jsonData[i].parent_code == parentsData.office_code){
      childrenData.push(jsonData[i]);
    }
  }
  for(var i = 0; i<childrenData.length; i++){
    if(childrenData[i].tree_leaf == 0){
      childrenData[i]["children"] = [];
      childrenData[i].children = getOfficeChildren(childrenData[i], jsonData)
    }
  }
  return childrenData;
}

// 获取company表children节点
function getCompanyChildren(parentsData, jsonData){
  var childrenData = [];
  for(var i = 0; i<jsonData.length; i++){
    if(jsonData[i].parent_code == parentsData.company_code){
      childrenData.push(jsonData[i]);
    }
  }
  for(var i = 0; i<childrenData.length; i++){
    if(childrenData[i].tree_leaf == 0){
      childrenData[i]["children"] = [];
      childrenData[i].children = getCompanyChildren(childrenData[i], jsonData)
    }
  }
  return childrenData;
}

// 获取area表children节点
function getAreaChildren(parentsData, jsonData){
  var childrenData = [];
  for(var i = 0; i<jsonData.length; i++){
    if(jsonData[i].parent_code == parentsData.area_code){
      childrenData.push(jsonData[i]);
    }
  }
  for(var i = 0; i<childrenData.length; i++){
    if(childrenData[i].tree_leaf == 0){
      childrenData[i]["children"] = [];
      childrenData[i].children = getAreaChildren(childrenData[i], jsonData)
    }
  }
  return childrenData;
}

export {
  getParters,
}