
;(function($){
    /**
     * 去除字符串左右的全角与半角空格
     * @param {} value
     */
    var trimSpanceAll=function(value){
        value=U_trimSpanceBJ(value);
        value=U_trimSpanceQJ(value);
        return value;
    }

    /**
     * 去除字符串左右的半角空格
     * @param {} value
     */
    var trimSpanceBJ=function (value){
        return value.replace(/[(^/s*)|(/s*$)]/g,"");
    }

    /**
     * 去除字符串左右的全角空格
     * @param {} value
     * @return {}
     */
    var trimSpanceQJ=function (value){
        return value.replace(/[(^　*)|(　*$)]/g,"");
    }

    /**
     * 根据formatter 建立日期对象
     * @param {} dateString	时间日期的字符串值
     * @param {} formatter 格式化参数，默认yyyy-MM-dd hh:mi:ss
     * @return {}　new Date
     */
    var buildDate= function (dateString,formatter){
        var today = new Date();
        if(!dateString || dateString == ""){
            return today;
        }
        if(!formatter || formatter == ""){
            formatter = "yyyy-MM-dd hh:mi:ss";
        }
        var yearMarker = formatter.replace(/[^y|Y]/g,'');
        var monthMarker = formatter.replace(/[^M]/g,'');
        var dayMarker = formatter.replace(/[^d]/g,'');
        var hourMarker = formatter.replace(/[^h]/g,'');
        var minutesMarker = formatter.replace(/[^mi]/g,'');
        var secondsMarker = formatter.replace(/[^ss]/g,'');

        var yearPosition = formatter.indexOf(yearMarker);
        var yearLength = yearMarker.length;
        var year =　dateString.substring(yearPosition ,yearPosition + yearLength) * 1;
        if( yearLength == 2){
            if(year < 50 ){
                year += 2000;
            }else{
                year += 1900;
            }
        }
        var monthPosition = formatter.indexOf(monthMarker);
        var month = dateString.substring(monthPosition,monthPosition + monthMarker.length) * 1 - 1;
        var dayPosition = formatter.indexOf(dayMarker);
        var day = dateString.substring( dayPosition,dayPosition + dayMarker.length )* 1;
        var hourPosition = formatter.indexOf(hourMarker);
        var hour = dateString.substring( hourPosition,hourPosition + hourMarker.length )* 1;
        var minutesPosition = formatter.indexOf(minutesMarker);
        var minutes = dateString.substring( minutesPosition,minutesPosition + minutesMarker.length )* 1;
        var secondsPosition = formatter.indexOf(secondsMarker);
        var seconds = dateString.substring( secondsPosition,secondsPosition + secondsMarker.length )* 1;
        return new Date(year,month,day,hour,minutes,seconds);
    }

    /**
     * 计算两个日期相差多少天
     * @param {} startDate　开始时间（必须为Date类型）
     * @param {} endDate　结束时间（必须为Date类型）
     * @return {}	返回天数
     */
    var dateDiffer=function (startDate, endDate) {
        //得到时间戳相减 得到以毫秒为单位的差
        var mmSec = (endDate.getTime()-startDate.getTime());
        //单位转换为天并返回
        //return (mmSec / 3600000 / 24);
        //返回小时单位的差值
        return (mmSec / 3600000);
    };

    /**
     * 获取一个月有几天
     * @param {} year 年
     * @param {} month　月
     * @return {}　天数
     */
    var dateMonthDays=function (year,month){
        var d=new Date(year,month,0);
        return d.getDate();
    };

    //日期时间格式化
    /**
     YYYY或yyyy 表示年
     MM 月
     dd 或DD 日
     hh或 HH 小时
     mm  分钟
     ss 或 SS 秒
     @param  formatStr  时间格式  例如 YYYY-MM-dd hh：mm：ss
     @param  date  国际标准时间

     */
    var fomateDate=function (formatStr,date){
        var str = formatStr;
        str=str.replace(/yyyy|YYYY/,date.getFullYear());
        str=str.replace(/yy|YY/,(date.getYear() % 100)>9?(date.getYear() % 100).toString():'0' + (date.getYear() % 100));
        str=str.replace(/MM/,(date.getMonth()+1)>9?(date.getMonth()+1):'0' + (date.getMonth()+1));
        str=str.replace(/dd|DD/,date.getDate()>9?date.getDate().toString():'0' + date.getDate());
        str=str.replace(/hh|HH/,date.getHours()>9?date.getHours().toString():'0' + date.getHours());
        str=str.replace(/mm/,date.getMinutes()>9?date.getMinutes().toString():'0' + date.getMinutes());
        str=str.replace(/ss|SS/,date.getSeconds()>9?date.getSeconds().toString():'0' + date.getSeconds());
        return str;
    }

    var eventBind=function(elId,event,fn){
        if(elId!=null && elId!="" && elId!=undefined && event!=null && event!="" && event!=undefined){
            if(fn!=null && fn!="" && fn!=undefined && typeof(fn)=="function"){
                var closeBtn=$("#"+ elId);
                closeBtn.bind(event,fn);
            }
        }
    }

    var reImp={
        trimSpanceAll:trimSpanceAll,
        trimSpanceBJ:trimSpanceBJ,
        trimSpanceQJ:trimSpanceQJ,
        buildDate:buildDate,
        dateDiffer:dateDiffer,
        dateDiffer:dateDiffer,
        dateMonthDays:dateMonthDays,
        fomateDate:fomateDate,
        eventBind:eventBind
    };

    if($.C_OBJ_MAP==null || $.C_OBJ_MAP=="" || $.C_OBJ_MAP==undefined){
        $.C_OBJ_MAP={};
    }
    $.C_APPOBJ_MAP= $.extend($.C_OBJ_MAP,{"commonUtils":reImp});
    if($.C_getCommon==null||$.C_getCommon=="" || $.C_getCommon==undefined){
        $.C_getCommon=function (id){
            return $.C_APPOBJ_MAP[id];
        }
    }
})(jQuery);