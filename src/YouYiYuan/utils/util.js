function formatTime(date) {
  var year = date.getFullYear()
  var month = date.getMonth() + 1
  var day = date.getDate()

  var hour = date.getHours()
  var minute = date.getMinutes()
  var second = date.getSeconds()


  return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

function formatNumber(n) {
  n = n.toString()
  return n[1] ? n : '0' + n
}

function conferences(key, isSearch) {
  var tasks = [{
    text: 'Using the Original and Symmetrical Face Test Samples to Perform Two-step Collaborative Representation for Face Recognition',
    trans: '英译中',
    chars: 140,
    fee: 60,
    awards: 2,
    startDate: '10.23',
    endDate: '10.25',
    expired: '3天'
  }, {
      text: 'Face recognition in real world environments is mainly affected by critical factors such as illumination variation, occlusion and small sample size. ',
      trans: '英译中',
      chars: 140,
      fee: 60,
      awards: 2,
      startDate: '10.23',
      endDate: '10.25',
      expired: '3天'
    }, {
      text: 'Robust face recognition based on non-negative sparse discriminative low-rank representation',
      trans: '英译中',
      chars: 140,
      fee: 60,
      awards: 2,
      startDate: '10.23',
      endDate: '10.25',
      expired: '3天'
    }, {
      text: 'Sparse representation can be described in high dimensions and used in many applications, including MRI imaging and radar imaging.',
      trans: '英译中',
      chars: 140,
      fee: 60,
      awards: 2,
      startDate: '10.23',
      endDate: '10.25',
      expired: '3天'
    }, {
      text: 'Using the Original and Symmetrical Face Test Samples to Perform Two-step Collaborative Representation for Face Recognition',
      trans: '英译中',
      chars: 140,
      fee: 60,
      awards: 2,
      startDate: '10.23',
      endDate: '10.25',
      expired: '3天'
    }, {
      text: 'Dimensionality reduction (DR) technique is a significant tool for feature extraction.',
      trans: '英译中',
      chars: 140,
      fee: 60,
      awards: 2,
      startDate: '10.23',
      endDate: '10.25',
      expired: '3天'
    }, {
      text: 'Deep convolutional neural networks (CNNs) have achieved many state-of-the-art results recently in different fields of computer vision.',
      trans: '英译中',
      chars: 140,
      fee: 60,
      awards: 2,
      startDate: '10.23',
      endDate: '10.25',
      expired: '3天'
    }]
  return tasks
}

function tags() {
  var tags = [{
    text: '人工智能',
    index: 6301
  }, {
    text: '肿瘤医学',
    index: 5112
  }, {
    text: '深度学习',
    index: 2301
  }, {
    text: '生物制药',
    index: 1200
  }, {
    text: '生物识别',
    index: 500
  }, {
    text: '自动驾驶',
    index: 300
  }, {
    text: '农业信息化',
    index: 112
  }, {
    text: '自动化种植',
    index: 50
  }];
  return tags
}

module.exports = {
  formatTime: formatTime,
  conferences: conferences,
  tags: tags
}
