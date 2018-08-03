//编辑任务
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    //button的值，接收任务或者编辑译文
    btn: '接受任务',
    checked: 'false',
    edit: '原文',
    //显示的值value
    value: null,
    originalText: null,
    transText: null,
    //编辑框里的值
    editText: null,
    //任务ID
    id: null,
    //接收任务返回的结果参数
    msg: null,
    code: null,
    data: null,

    accept:null
  },

  /**
   * 生命周期函数--监听页面加载
   * 从options中取出原文、译文和任务id
   */
  onLoad: function (options) {
    this.setData({
      originalText: options.originalText,
      transText: options.transText,
      id: options.id,
      value: options.originalText,
      btn:options.btn,
      accept:options.accept
    })
    console.log("options of edit",options)
    console.log("options:"+this.data.transText)
    console.log(this.data.originalText)
    console.log(this.data.transText)
    console.log("id of edit:" + this.data.id)
  },


  //开关选择器的改变事件
  select: function (e) {
    this.setData({
      checked: !this.data.checked,
    })
    if (this.data.edit == "原文") {
      this.setData({
        value: this.data.transText,
        edit: "译文"

      })
      // console.log(this.data.value)
    } else {
      this.setData({
        value: this.data.originalText,
        edit: "原文"

      })
      // console.log(this.data.value)
    }
  },

  //接受任务
  acceptTask: function (e) {
    console.log("session:" + app.globalData.session)
    console.log("id:" + this.data.id)
    let that = this
    let session = app.globalData.session
    if(this.data.accept=="accept"){
      wx.request({
        //url: 'http://localhost:8080/social-trans/task/acceptTaskFromMiniprogram?session=' + session + '&id=' + that.data.id, //仅为示例，并非真实的接口地址
        url: 'https://sotrans.infoaas.com/sotrans/task/acceptTaskFromMiniprogram?session=' + session + '&id=' + that.data.id, 
        method: 'POST',
        success: function (res) {
          console.log(res.data)
          that.setData({
            code: res.data.code,
            msg: res.data.msg,
          })
          if (that.data.code = 200) {
            that.setData({
              editText: that.data.transText
            })
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
        },
        fail: function (res) {
          wx.showToast({
            title: '连接服务器失败',
            icon: 'none',
            duration: 2000
          })
        },
      })
    }else{
      that.setData({
        editText: that.data.transText
      })
    }
  
  },

  formSubmit: function (e) {
    console.log(e.detail.value)
    this.setData({
      editText:e.detail.value
    })
    this.submit();
  },
  //提交任务
  submit: function (e) {
    let that = this
    let session = app.globalData.session
    console.log("session:" + app.globalData.session)
    console.log("id:" + this.data.id)
    console.log("editText:",this.data.editText)
    if (this.data.editText) {
      wx.request({
        //url: 'http://localhost:8080/social-trans/task/submitTaskFromMiniprogram',
        url: 'https://sotrans.infoaas.com/sotrans/task/submitTaskFromMiniprogram',
        header: {
          'content-type': 'application/x-www-form-urlencoded'
        },
        method: 'POST',
        data: {
          session: session,
          id: that.data.id,
          transText: that.data.transText
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
    }else{
      wx.showToast({
        title:'提交内容不能为空',
        icon: 'none',
        duration: 2000
      })
    }
  }
})