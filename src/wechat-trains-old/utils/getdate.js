//获取当前日期，用于任务发布
function formateTime(date,end){
  if(end==1){
    var year = date.getFullYear()+1;
    var month = date.getMonth()+1;
    var day = date.getDate();
    return [year, month, day].map(formatNumber).join('-')
  }else{
    var year = date.getFullYear();
    var month = date.getMonth()+1;
    var day = date.getDate();
    return [year, month, day].map(formatNumber).join('-')
  }

}

function formatNumber(n){
  n.toString();
  return n[1] ? n:'0'+n;
}

module.exports={
  formatTime:formateTime
}