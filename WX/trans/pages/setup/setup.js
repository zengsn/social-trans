//编辑个人信息
// pages/setup/setup.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    countryCodes: ["+86", "+80", "+84", "+87"],
    countryCodeIndex: 0,

    checkboxItems: [
      { name: '政治', value: '政治', checked: true },
      { name: '历史', value: '历史', checked: false },
      { name: '地理', value: '地理', checked: false },
      { name: '文学', value: '文学', checked: false },
      { name: '商业', value: '商业', checked: false },
      { name: '小说', value: '小说', checked: false },
    ],
    account: null,
    password: null,
    phone: null,
    hobby: ["政治", "文学", "地理", "历史","商业","小说"]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  checkboxChange: function (e) {
    console.log('checkbox发生change事件，携带value值为：', e.detail.value);

    var checkboxItems = this.data.checkboxItems, values = e.detail.value;
    for (var i = 0, lenI = checkboxItems.length; i < lenI; ++i) {
      checkboxItems[i].checked = false;

      for (var j = 0, lenJ = values.length; j < lenJ; ++j) {
        if (checkboxItems[i].value == values[j]) {
          checkboxItems[i].checked = true;
          break;
        }
      }
    }
    this.setData({
      checkboxItems: checkboxItems,
      hobby: e.detail.value
    });
  },

  formSubmit: function (e) {
    console.log("e:", e.detail.value)
    this.setData({
      account: e.detail.value.account,
      password: e.detail.value.password,
      phone: e.detail.value.phone,
      hobby: e.detail.value.hobby,
    })
    this.submit()
  },

  submit: function (e) {
    let that = this
    let session = app.globalData.session
    console.log("账号："+this.data.account)
    console.log("密码：" + this.data.password)
    console.log("手机：" + this.data.phone)
    console.log("爱好：" + this.data.hobby)
    wx.showModal({
      title: '提示',
      content: '确定修改吗？',
      success: function (res) {
        if (res.confirm) {
          console.log('用户点击确定')
          wx: wx.request({
            //url: 'http://localhost:8080/social-trans/user/updateUserFromMiniprogram',
            url: 'https://sotrans.infoaas.com/sotrans/user/updateUserFromMiniprogram',
            data: {
              account: that.data.account,
              password: that.data.password,
              phone: that.data.phone,
              hobby: that.data.hobby,
              session: session,
            },
            header: {
              'content-type': 'application/x-www-form-urlencoded'
            },
            method: 'POST',
            success: function (res) {
              if (res.data.code == 200) {
                wx.showToast({
                  title: '修改成功',
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
        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })
  }
})