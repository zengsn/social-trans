// pages/addTask/addTask.js  添加任务
var app = getApp();
var getDate=require('../../utils/getdate.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    //要提交的原文
    taskText:null,
    //当前日期
    date:getDate.formatTime(new Date(),0),
    //最远截止日期，默认一年后
    endDate:getDate.formatTime(new Date(),1),
    //任务名
    taskName:null,
    //任务描述
    description:null,
    //任务赏金
    taskManey:null,
    //是否英文转中文
    toEnglish:'ZHtoEN',
    checked:'true'

  
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
  
  },
  //开关选择器的改变事件
  select: function (e) {
    this.setData({
      checked: !this.data.checked,
    })
    if (this.data.toEnglish == "ZHtoEN") {
      this.setData({
        
        toEnglish: "ENtoZH"

      })
      // console.log(this.data.value)
    } else {
      this.setData({
        
        toEnglish: "ZHtoEN"

      })
      // console.log(this.data.value)
    }
  },
//时间选择器
  bindDateChange: function (e) {
    this.setData({
      date: e.detail.value
    })
  },
  formSubmit: function (e) {
    console.log("e:",e.detail.value)
    this.setData({
      checked:e.detail.value.checked,
      taskName: e.detail.value.taskName,
      description: e.detail.value.description,
      taskText: e.detail.value.taskText,
      date: e.detail.value.date,
      taskMoney: e.detail.value.taskMoney,
    })
    this.addTask()
  },
//提交任务
  addTask: function (e) {
    let that = this
    let session = app.globalData.session
    console.log("session:" + app.globalData.session)
    console.log("taskText:"+this.data.taskText)
    console.log("taskName:" + this.data.taskName)
    console.log("finishTime:"+this.data.date)
    if (this.data.taskText!='') {
      wx.request({
        //url: 'http://localhost:8080/social-trans/task/addTaskFromMiniprogram',
        url: 'https://sotrans.infoaas.com/sotrans/task/addTaskFromMiniprogram',
        header: {
          'content-type': 'application/x-www-form-urlencoded'
        },
        method: 'POST',
        data: {
          session: session,
          taskName:that.data.taskName,
          description:that.data.description,
          taskMoney:that.data.taskMoney,
          checked:that.data.checked,
          taskText: that.data.taskText,
          finishTime:that.data.date
        },
        success(res) {
          console.log(res.data)
          that.setData({
            code: res.data.code,
            msg: res.data.msg
          })
          if (that.data.code == 200) {
            wx.showToast({
              title: res.data.msg,
              icon: 'success',
              duration: 2000
            })
          } else {
            wx.showToast({
              title: res.data.msg,
              icon: 'none',
              duration: 2000
            })
          }
        }
      })
    } else {
      wx.showToast({
        title: '提交内容不能为空',
        icon: 'none',
        duration: 2000
      })
    }
  }
})