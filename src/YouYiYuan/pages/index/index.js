//index.js
var util = require('../../utils/util.js')
var base64 = require("../example/images/base64");
//获取应用实例
var app = getApp()
Page({
  data: {
    motto: '学术会议与学术活动',
    userInfo: {},
    windowHeight: 0,
    windowWidth : 0,
    inputShowed: false,
    inputVal: "",
    showMore: false,
    isLower: false,
    isEnd: false,
    conferences: []
  },
  onLoad: function (options) {
    //console.log(options.type)
    var that = this
    // 设置窗口大小
    wx.getSystemInfo({
      success: (res) => {
        that.setData({
          windowHeight: res.windowHeight - 5,
          windowWidth: res.windowWidth
        })
        //console.dir(that.data.windowHeight)
        // 加载数据
        that.loadConferences()
      }
    })
  },
  showInput: function () {
    this.setData({
        inputShowed: true
    });
  },
  hideInput: function () {
    this.setData({
        inputVal: "",
        inputShowed: false
    });
  },
  clearInput: function () {
    this.setData({
        inputVal: "",
        searchResults: []
    });
  },
  inputTyping: function (e) {
    var conferences = util.conferences(e.detail.value,1)
    this.setData({
        inputVal: e.detail.value,
        searchResults: conferences
    });
  },

  onUpper : function() {},
  onLower : function() {
    var that = this
    that.setData({
      isLower: true
    });
    // load more data
    that.loadMoreConferences()
  },
  onScroll : function() {},

  loadConferences : function() {
    var that = this;
    that.setData({
      conferences: util.conferences()
    })    
  },

  currentPage: 1, // current page
  loadMoreConferences : function() {
    var that = this
    var page = that.currentPage++
    var moreConferences = util.conferences('',page)
    that.setData({
      isLower: false,
      isEnd: moreConferences.length==0,
      conferences: that.data.conferences.concat(moreConferences)
    })    
    console.dir(page)
  }
})
