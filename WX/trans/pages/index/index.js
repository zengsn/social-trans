//主页
required:
var sliderWidth = 96; // 需要设置slider的宽度，用于计算中间位置
var app = getApp();
var pubsub=require('../../utils/pubsub')
Page({
  data: {
    tabs: ["1", "2", "3"],
    activeIndex: 1,
    sliderOffset: 0,
    sliderLeft: 0,
    min: 1,
    resLength: 10,
    taskList: [],

    //传给edit的原文
    originalText: null,
    //传给edit的译文
    transText: null,
    //传递给edit的id
    id: null,

    btn: "接受任务",

    //文段中的值
    value1: null,
    value2: null,
    value3: null,

    //accept用作传递给edit页面的参数，决定edit页面的“编辑译文”按钮是否弹出提示信息
    accept: 'accept'

  },
  onLoad: function () {
    var that = this;
    this.getData();
    wx.getSystemInfo({
      success: function (res) {
        that.setData({
          sliderLeft: (res.windowWidth / that.data.tabs.length - sliderWidth) / 2,
          sliderOffset: res.windowWidth / that.data.tabs.length * that.data.activeIndex
        });
      }
    });
  },

  //将获取的Json数组taskText进行处理
  initData: function (e) {
    let that = this
    that.setData({
      value1: this.data.taskList[0].taskText,
      value2: this.data.taskList[1].taskText,
      value3: this.data.taskList[2].taskText,
    })
  },
  //navibar的点击事件
  tabClick: function (e) {
    this.setData({
      sliderOffset: e.currentTarget.offsetLeft,
      activeIndex: e.currentTarget.id
    });
  },

  //查看详情
  clickAccept1: function (e) {
    this.setData({
      originalText: this.data.taskList[0].taskText,
      transText: this.data.taskList[0].tranText,
      id: this.data.taskList[0].taskId
    }),
      // console.log(this.data.originalText)
      // console.log(this.data.transText)
      console.log("id of index",this.data.id)
    wx.navigateTo({
      url: '/pages/edit/edit?originalText=' + this.data.originalText + '&transText=' + this.data.transText + '&id=' + this.data.id + '&btn=' + this.data.btn + '&accept=' + this.data.accept,
    })
  },
  clickAccept2: function (e) {
    this.setData({
      originalText: this.data.taskList[1].taskText,
      transText: this.data.taskList[1].tranText,
      id: this.data.taskList[1].taskId
    }),
      wx.navigateTo({
        url: '/pages/edit/edit?originalText=' + this.data.originalText + '&transText=' + this.data.transText + '&id=' + this.data.id + '&btn=' + this.data.btn + '&accept=' + this.data.accept,
      })
  },
  clickAccept3: function (e) {
    this.setData({
      originalText: this.data.taskList[2].taskText,
      transText: this.data.taskList[2].tranText,
      id: this.data.taskList[2].taskId
    }),
      wx.navigateTo({
        url: '/pages/edit/edit?originalText=' + this.data.originalText + '&transText=' + this.data.transText + '&id=' + this.data.id + '&btn=' + this.data.btn + '&accept=' + this.data.accept,
      })
  },



  //换一个
  clickAnother1: function (e) {
    let that = this
    wx.request({
      //url: 'http://localhost:8080/social-trans/task/pushTaskListInJsonWithSessionKey', //仅为示例，并非真实的接口地址
      url: 'https://sotrans.infoaas.com/sotrans/task/pushTaskListInJsonWithSessionKey',
      header: {
        'content-type': 'application/json' // 默认值
      },
      data: {
        session: app.globalData.session,
        num: 1
      },
      success: function (res) {
        console.log("res.data:",res.data)
        //由于setData函数中不能直接给taskList[0]赋值，先创建一个临时数组保存res.data
        var tmparr = that.data.taskList;
        // console.log("taskList:", that.data.taskList);
        tmparr.splice(0, 1, res.data[0]);
        // console.log("tmparr:", tmparr);
        that.setData({
          value1: res.data[0].taskText,
          taskList: tmparr
        })
        console.log("value1:", that.data.taskList[0])
        console.log("this.data.btn:"+that.data.btn)
      }
    })
  },
  clickAnother2: function (e) {
    let that = this
    wx.request({
      //url: 'http://localhost:8080/social-trans/task/pushTaskListInJsonWithSessionKey', //仅为示例，并非真实的接口地址
      url: 'https://sotrans.infoaas.com/sotrans/task/pushTaskListInJsonWithSessionKey',
      header: {
        'content-type': 'application/json' // 默认值
      },
      data: {
        session: app.globalData.session,
        num: 1
      },
      success: function (res) {
        console.log(res.data)
        var tmparr = that.data.taskList;
        tmparr.splice(1, 1, res.data[0]);
        that.setData({
          value2: res.data[0].taskText,
          taskList: tmparr
        })
      }
    })
  },
  clickAnother3: function (e) {
    let that = this
    wx.request({
      //url: 'http://localhost:8080/social-trans/task/pushTaskListInJsonWithSessionKey', //仅为示例，并非真实的接口地址
      url: 'https://sotrans.infoaas.com/sotrans/task/pushTaskListInJsonWithSessionKey',
      header: {
        'content-type': 'application/json' // 默认值
      },
      data: {
        session: app.globalData.session,
        num: 1
      },
      success: function (res) {
        console.log(res.data)
        console.log("taskList:", that.data.taskList)
        var tmparr = that.data.taskList;
        console.log("tmparr:", tmparr)
        tmparr.splice(2, 1, res.data[0]);
        that.setData({
          value3: res.data[0].taskText,
          taskList: tmparr
        })
      }
    })
  },

  //发起请求
  startRequest: function (e) {
    if (!app.globalData.session) {
      wx.showToast({
        title: "尚未登陆，请稍候",
        icon: 'none',
        duration: 2000
      }),
        app.getSessionKey(),
        this.getData()
    } else {
      this.getData()
    }
  },
  //获取数据
  getData: function (e) {
    console.log("index,session:" + app.globalData.session)
    let that = this
    //console.log(app.globalData.session)
    if(app.globalData.session){
      wx.request({
        //url: 'http://localhost:8080/social-trans/task/pushTaskListInJsonWithSessionKey', 
        url: 'https://sotrans.infoaas.com/sotrans/task/pushTaskListInJsonWithSessionKey',
        header: {
          'content-type': 'application/json' // 默认值
        },
        data: {
          session: app.globalData.session,
          num: 3
        },
        success: function (res) {
          console.log(res.data)
          that.setData({
            resLength: res.data.length,
            taskList: res.data
          })
          that.initData();
          console.log("taskList:", that.data.taskList)
        }
      });
    }else{
      pubsub.subscribe("loginFlag", function (topics, data) {
        console.log(data)
        wx.request({
          //url: 'http://localhost:8080/social-trans/task/pushTaskListInJsonWithSessionKey', 
          url: 'https://sotrans.infoaas.com/sotrans/task/pushTaskListInJsonWithSessionKey',
          header: {
            'content-type': 'application/json' // 默认值
          },
          data: {
            session: app.globalData.session,
            num: 3
          },
          success: function (res) {
            console.log(res.data)
            that.setData({
              resLength: res.data.length,
              taskList: res.data
            })
            that.initData();
            console.log("taskList:", that.data.taskList)
          }
        });
      })
    }
  }
});