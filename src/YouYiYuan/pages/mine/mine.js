// pages/mine/mine.js
var base64 = require("../example/images/base64");
var app = getApp()
Page({
  data:{
    userInfo:'',
    time: new Date()
  },
  onLoad:function(options){
    var that = this
    that.setData({
        icon: base64.icon20
    });
    //调用应用实例的方法获取全局数据
    app.getUserInfo(function(userInfo){
      //更新数据
      that.setData({
        userInfo:userInfo
      })
    })
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