//关于，即下边的设置按钮对应页面
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */

  data: {
    avatarImag: null,
    nickName: null,
    canIUse: wx.canIUse('button.open-type.getUserInfo')
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      //nickName: app.globalData.userInfo.nickName,
      //avatarImag: app.globalData.userInfo.avatarUrl
    })
    // 查看是否授权
    wx.getSetting({
      success: function (res) {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称
          wx.getUserInfo({
            success: function (res) {
              console.log(res.userInfo)
            }
          })
        }
      }
    })
  },
  //授权处理函数
  bindGetUserInfo: function (e) {
    console.log(e.detail.userInfo),
    this.setData({
      avatarImag:e.detail.userInfo.avatarUrl,
      nickName:e.detail.userInfo.nickName
    })
  },
//查看已提交的任务
  finish: function (e) {
    let that = this
    wx.request({
      //url: 'http://localhost:8080/social-trans/task/getFinishTaskFromMiniprogram',
      url: 'https://sotrans.infoaas.com/sotrans/task/getFinishTaskFromMiniprogram',
      data: {
        session: app.globalData.session
      },
      success: function (res) {
        console.log(res.data)
        if (res.data.code == 600) {
          wx.showToast({
            title: '您暂时未提交任务',
            icon: 'none',
            duration: 2000,
          })
        } else {
          // 由于请求回来的数据中含有 "&", "?", "=", 这样navigatorTo的url会解析错误，所以要把 & 换成 and, 把 ? 换成 questionMark，把 = 换成 equalMark
          var resDataStr = JSON.stringify(res.data.data).replace(/\&/g, "andMark").replace(/\?/g, "questionMark").replace(/\=/g, "equalMark");
          wx.navigateTo({
            //只将返回结果中的data字段显示
            url: '/pages/finishTask/finishTask?data=' + resDataStr
          })
        }

      },
      fail: function (res) {
        wx.showToast({
          title: '连接服务器失败',
          icon: 'none',
          duration: 2000,
        })
      }
    })
  },
  //查看用户发布的任务
  release: function (e) {
    let that = this
    wx.request({
      //url: 'http://localhost:8080/social-trans/task/showReleaseFromMiniprogram',
      url: 'https://sotrans.infoaas.com/sotrans/task/showReleaseFromMiniprogram',
      data: {
        session: app.globalData.session
      },
      success: function (res) {
        console.log(res.data)
        if (res.data.code == 500) {
          wx.showToast({
            title: '您还没有发布的任务',
            icon: 'none',
            duration: 2000,
          })
        } else {
          // 由于请求回来的数据中含有 "&", "?", "=", 这样navigatorTo的url会解析错误，所以要把 & 换成 and, 把 ? 换成 questionMark，把 = 换成 equalMark
          var resDataStr = JSON.stringify(res.data.data).replace(/\&/g, "andMark").replace(/\?/g, "questionMark").replace(/\=/g, "equalMark");
          wx.navigateTo({
            //只将返回结果中的data字段显示
            url: '/pages/releaseTask/releaseTask?data=' + resDataStr
          })
        }

      },
      fail: function (res) {
        wx.showToast({
          title: '连接服务器失败',
          icon: 'none',
          duration: 2000,
        })
      }
    })
  },

  //查看未完成的任务
  unfinish: function (e) {
    let that = this
    wx.request({
      //url: 'http://localhost:8080/social-trans/task/getUnFinishFromMiniprogram',
      url: 'https://sotrans.infoaas.com/sotrans/task/getUnFinishFromMiniprogram',
      data: {
        session: app.globalData.session
      },
      success: function (res) {
        console.log(res.data)
        if (res.data.code == 800) {
          wx.showToast({
            title: '您还没有未完成的任务',
            icon: 'none',
            duration: 2000,
          })
        } else {
          console.log("res.data.data:", res.data.data)
          // 由于请求回来的数据中含有 "&", "?", "=", 这样navigatorTo的url会解析错误，所以要把 & 换成 and, 把 ? 换成 questionMark，把 = 换成 equalMark
          var resDataStr = JSON.stringify(res.data.data).replace(/\&/g, "andMark").replace(/\?/g, "questionMark").replace(/\=/g, "equalMark");
          wx.navigateTo({
            //只将返回结果中的data字段显示
            url: '/pages/acceptTask/acceptTask?data=' + resDataStr
          })
        }

      },
      fail: function (res) {
        wx.showToast({
          title: '连接服务器失败',
          icon: 'none',
          duration: 2000,
        })
      }
    })
  },


  add:function(e){
    wx.navigateTo({
      url: '/pages/addTask/addTask',
    })
  },

  setup:function(e){
    wx.navigateTo({
      url: '/pages/setup/setup',
    })
  }
})