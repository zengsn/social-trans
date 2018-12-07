// pages/taskList/taskList.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
  taskList:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var data = JSON.parse(options.data)
    this.setData({
      taskList: data
    })
    console.log("taskList:",this.data.taskList)
  },
})