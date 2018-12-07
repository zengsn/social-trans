// pages/explore/tags.js
var util = require('../../utils/util.js')
Page({
  data:{
    max: 0,
    min: 0,
    tags: []
  },
  onLoad:function(options){
    var tags = util.tags()
    var max = 0, min = 10000
    for(var i=0;i<tags.length;i++){
      if (tags.index > max) {
        max = tags.index
      } 
      if (tags.index < min) {
        min = tags.index
      }
    }
    this.setData({
        tags: tags,
        max : max,
        min : min
    });
  },
  onReady:function(){
    // 页面渲染完成
  },
  onShow:function(){
    // 页面显示
  },
  onHide:function(){
    // 页面隐藏
  },
  onUnload:function(){
    // 页面关闭
  }
})