//app.js
var pubsub = require('/utils/pubsub')
App({
  /**
 * 微信登录，获取 code 和 encryptData
 */
  getWxLoginResult: function getLoginCode() {
    let that=this
    wx.login({
      success: function (res) {
        if (res.code) {
          console.log("res.code:"+res.code)
         // console.log(that)
          that.globalData.code=res.code
          that.getSessionKey()
          console.log("kk"+that.globalData.code)
        }
      },
      fail: function (loginError) {
        var error = new LoginError(constants.ERR_WX_LOGIN_FAILED, '微信登录失败，请检查网络状态');
        error.detail = loginError;
        //  callback(error, null);
      },
    })
  },

  /**
 * 登陆服务器获取sessionKey
 */
  getSessionKey: function () {
    let that=this
    console.log("123"+this.globalData.code)
    wx: wx.request({
      //url: 'http://localhost:8080/social-trans/user/loginFromMiniprogram',
      url: 'https://sotrans.infoaas.com/sotrans/user/loginFromMiniprogram',
      data: {
        code: that.globalData.code
      },
      success: function (res) {
        wx.setStorage({
          key: 'sessionKey',
          data: res.data.sessionKey,
          
        })
        that.globalData.session =res.data.sessionKey
        console.log("res.data.session:"+res.data.sessionKey)
        console.log("that.globalData.session:" + that.globalData.session)
        pubsub.publish('loginFlag', '登陆成功')
      },
      fail: function () {
        wx.showModal({
          title: '提示',
          content: '登录失败，可能是网络错误或者服务器发生异常',
          success: function (res) {
            if (res.confirm) {
              console.log('用户点击确定')
            } else if (res.cancel) {
              console.log('用户点击取消')
            }
          }
        })
      },
    })
  },


  /**
   * 初始化函数
   */
  onLaunch: function () {
    let that = this
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)
    //从本地取出sessionKey

    this.globalData.session = wx.getStorageSync('sessionKey')
    if (this.globalData.session) {
      console.log('hahahah11')
      wx.checkSession({
        success: function () {
          //sessionKey未过期的处理逻辑
         // pubsub.publish('loginFlag', '登陆未过期')
          //pubsub.publish('loginFlag', '登陆成功')
          console.log("checksSssion:true")
        },
        fail: function () {
          console.log("456"+that.globalData.code)
          that.getWxLoginResult()
          
        }
      })
    } else {
     // console.log( this.globalData.code)
      this.getWxLoginResult()
    }
    // 获取用户信息
   /** wx.getSetting({
      success(res) {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              that.globalData.userInfo = res.userInfo
              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        } else {
          wx.authorize({
            scope: 'scope.userInfo',
            success(res) {
              this.globalData.userInfo = res.userInfo
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })**/
  },
  globalData: {
    userInfo: null,
    session: null,
    code:null
  }
})