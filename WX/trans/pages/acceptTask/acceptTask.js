//查看已接受任务
// pages/finishTask/finishTask.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    taskList:[],
    originalText:null,
    transText:null,
    id:null,
    //传递给编辑页面，改变原来"接受任务"按的名称为“编辑译文”
    btn:"编辑译文",
    //accept用作传递给edit页面的参数，使edit页面的“编辑译文”按钮不弹出提示信息
    accept:'accepted'
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log("data:", options.data)
    
    var data = JSON.parse(options.data)
    this.setData({
      taskList: data
    })
    console.log("taskList:", this.data.taskList)
  },
//编辑按钮，将当前item对应任务的原文译文和id传送给编辑页面  xmlns:web="http://xmlns.jcp.org/xml/ns/javaee" 
  /**edit:function(e){
    console.log(e)
    this.setData({
      originalText: this.data.taskList[e.target.dataset.index].taskText,
      transText: this.data.taskList[e.target.dataset.index].tranText,
      id: this.data.taskList[e.target.dataset.index].taskId
    })

    console.log("原文："+this.data.originalText)
    wx.navigateTo({
      url: '/pages/edit/edit?originalText=' + this.data.originalText + '&transText=' + this.data.transText + '&id=' + this.data.id+'&btn='+this.data.btn,
    })
  },**/

  //放弃任务
  giveup:function(e){
    /**let that = this
    let session = app.globalData.session
    wx.request({
      url: 'http://localhost:8080/social-trans/task/giveupTaskFromMiniprogram',
      url: 'https://sotrans.infoaas.com/sotrans/task/giveupTaskFromMiniprogram',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      method: 'POST',

      data: {
        id: this.data.taskList[e.target.dataset.index].taskId,
        session: session
      },
      success(res) {
        //console.log(res.data)
        that.setData({
        })
        if (200) {
          wx.showToast({
            title: "删除成功",
            icon: 'success',
            duration: 2000
          })
        } else {
          wx.showToast({
            title: "删除失败",
            icon: 'none',
            duration: 2000
          })
        }
      }
    })**/

    wx.showToast({
      title: "此功能暂未开放",
      icon: 'none',
      duration: 2000
    })
  }
    
})
