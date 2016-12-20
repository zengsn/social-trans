$(document).ready(function () {
    disablebackspace();
});

//终止回退键
function disablebackspace() {
    function banBackSpace(e) {
        var ev = e || window.event;//获取event对象
        var obj = ev.target || ev.srcElement;//获取事件源
        var t = obj.type || obj.getAttribute('type');//获取事件源类型
        //获取作为判断条件的事件类型
        var vReadOnly = obj.readOnly;
        var vDisabled = obj.disabled;
        //处理undefined值情况
        vReadOnly = (vReadOnly == undefined) ? false : vReadOnly;
        vDisabled = (vDisabled == undefined) ? true : vDisabled;
        //当敲Backspace键时，事件源类型为密码或单行、多行文本的，
        //并且readOnly属性为true或disabled属性为true的，则退格键失效
        var flag1 = ev.keyCode == 8 && (t == "password" || t == "text" || t == "textarea") && (vReadOnly == true || vDisabled == true);
        //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
        var flag2 = ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea";
        //判断
        if (flag2 || flag1)return false;
    }

    //禁止退格键 作用于Firefox、Opera
    document.onkeypress = banBackSpace;
    //禁止退格键 作用于IE、Chrome
    document.onkeydown = banBackSpace;
}

;
(function ($) {

    var maskContent = {
        css: {
            border: 'none',
            padding: '15px',
            backgroundColor: '#000',
            '-webkit-border-radius': '10px',
            '-moz-border-radius': '10px',
            opacity: .5,
            color: '#fff',
            font: '14px'
        },
        baseZ: 9999,
        message: '<img src="../image/wait.gif" border="0" />  正在处理中，请稍候...'
    };

    var lockpage = function (id) {
        if (id != undefined && id != null && id != '') {
            $("#" + id).block(maskContent);
        } else {
            $.blockUI(maskContent);
        }

    }

    var unLockpage = function (id) {
        if (id != undefined && id != null && id != '') {
            $("#" + id).unblock();
        } else {
            $.unblockUI();
        }
    }

    var getPageData = function (n, s, data, filter) {
        if (filter != undefined && filter != null && typeof(filter) == 'function') {
            data = filter(data);
        }
        var rows = data.rows;
        var total = rows.length;
        var vrows = [];
        var start = (n - 1) * s;
        for (var i = 0; i < s; i++) {
            var index = start + i;
            if (index >= total) {
                break;
            }
            vrows.push(rows[index]);
        }
        return {
            'total': total,
            'rows': vrows
        }
    }

// 高亮显示搜索到的关键字
    var HeightLight = function (doc, value) {
        var array = [];
        array = value.split(" ");
        for (var i = 0; i < array.length; i++) {
            var Keyword = array[i];
            // 文本选择器
            var TextRange;
            // 是否找到
            var Found = false;
            // 找到的次数
            var Count = 0;
            TextRange = doc.body.createTextRange();

            Found = TextRange.findText(Keyword);
            if (Found) {
                Count++;
            }
            while (Found && Count > 0) {
                TextRange.pasteHTML('<span style="background:yellow">' + Keyword
                    + '</span>');
                // 将滚动条定位到第一次查到的视口范围内
                if (Count == 1) {
                    TextRange.scrollIntoView();
                }
                // 继续查找
                Found = TextRange.findText(Keyword);
                if (!Found) {
                    Count = 0;
                } else {
                    Count++;
                }
            }
        }
    }

    var getQueryString = function (name) {
        // 如果链接没有参数，或者链接中不存在我们要获取的参数，直接返回空
        if (location.href.indexOf("?") == -1
            || location.href.indexOf(name + '=') == -1) {
            return '';
        }

        // 获取链接中参数部分
        var queryString = location.href.substring(location.href.indexOf("?") + 1);

        // 分离参数对 ?key=value&key2=value2
        var parameters = queryString.split("&");

        var pos, paraName, paraValue;
        for (var i = 0; i < parameters.length; i++) {
            // 获取等号位置
            pos = parameters[i].indexOf('=');
            if (pos == -1) {
                continue;
            }

            // 获取name 和 value
            paraName = parameters[i].substring(0, pos);
            paraValue = parameters[i].substring(pos + 1);

            // 如果查询的name等于当前name，就返回当前值，同时，将链接中的+号还原成空格
            if (paraName == name) {
                return unescape(paraValue.replace(/\+/g, " "));
            }
        }
        return '';
    }

    var ajaxFunction = function (url, params, sync, fn) {
        var rdata = false;
        try {
            $.ajax({
                async: sync,
                type: "post",
                url: url,
                dataType: "json",
                data: params,
                success: function (data) {
                    rdata = data;
                    if (fn && typeof(fn) == 'function') {
                        fn(data);
                    }
                },
                error: function (r) {
                    //alert("error");
                    window.top.log.debug("知识库请求失败，失败连接地址为：" + url);
                    if (fn && typeof(fn) == 'function') {
                        fn('error');
                    }
                }
            });
        } catch (ex) {
            //alert("ex error. " + ex.description);
        }
        return rdata;
    }

    var initDataGridPageing = function (id, data, pageNumber, pageSize) {
        var pager = $('#' + id).datagrid('getPager');
        pager.pagination({
            showPageList: true,
            onBeforeRefresh: function (pageNumber, pageSize) {
                // alert('before refresh');
                return true;
            },
            onSelectPage: function (pageNumber, pageSize) {
                $('#' + id).datagrid('loadData',
                    getPageData(pageNumber, pageSize, data));
            }
        });
    }

    var closeGrid = function (id) {
        var panel = $('#' + id).datagrid("getPanel");
        $(panel).panel("close");
    }

    var openGrid = function (id) {
        var panel = $('#' + id).datagrid("getPanel");
        $(panel).panel("open");
    }

    var dataGridPageNumSet = function (gridId, num) {
        var pager = $('#' + gridId).datagrid('getPager');
        pager.pagination('refresh', {
            pageNumber: 1
        });
    }

    var getPagingSearchData = function (url, params, sync, fn, isBlockPage) {

        var rdata = {
            'total': 0,
            'rows': []
        };
        if (sync == undefined || sync == null || sync == '') {
            sync = false;
        }
        if (isBlockPage == undefined || isBlockPage == null || isBlockPage == '') {
            isBlockPage = false;
        }
        if (isBlockPage) {
            $.blockUI(maskContent);
        }
        $.ajax({
            async: sync,
            type: "post",
            url: url,
            dataType: "json",
            data: params,
            success: function (data) {
                if (data) {
                    if (data.data) {
                        rdata.total = data.data.length;
                        rdata.rows = data.data;
                    }
                } else {
                    rdata.total = 0;
                    rdata.rows = [];
                }
                if (fn && typeof(fn) == "function") {
                    fn(rdata);
                }
                if (isBlockPage) {
                    $.unblockUI();
                }
            },
            error: function (r) {
                alert("error");
                $.unblockUI();
            }
        });
        return rdata;
    }

    var getPagingSearchDataWithAsync = function (id, url, params, gridPageSize) {
        var allGridData = getPagingSearchData(url, params, true, function (allGridData) {
            dataGridPageNumSet(id, '1');
            $('#' + id).datagrid('loadData', getPageData(1, gridPageSize, allGridData));
        }, true);
        return allGridData;
    }

    var getHtmlFormJsons = function (id) {
        var reData = {};
        var jsons = $("#" + id).serializeArray();
        for (var i = 0; i < jsons.length; i++) {
            var data = jsons[i];
            var name = data.name;
            var value = data.value;
            if (value == undefined || value == null || value.length < 0) {
                value = "";
            }
            if (name != undefined && name != null && name.length > 0) {
                reData[name] = $.trim(value);
            }
        }
        //对checkbox的处理
        var chs = $("#" + id + " input[type='checkbox']");
        $(chs).each(function (i, e) {
            var cn = e.name;
            if (reData[cn] != undefined && reData[cn] != null) {
                reData[cn] = 'Y';
            }
        });
        return reData;
    }

    var closeDialogWindows = function (id) {
        $('#' + id).window("close");
    }

    var showDialogWindows = function (id, init, uri) {
        $('#' + id).window("open");
        if (init == true) {
            window.setTimeout(function () {
                if (uri != undefined && uri != null && uri != '') {
                    window.setTimeout(function () {
                        $('#' + id + " iframe").attr("src", uri);
                    }, 100);
                }
            }, 200)

        }
        $('#' + id).window("expand");
    }

    var appCommon = {
        maskContent: maskContent,
        lockpage: lockpage,
        unLockpage: unLockpage,
        HeightLight: HeightLight,
        getQueryString: getQueryString,
        ajaxFunction: ajaxFunction,
        initDataGridPageing: initDataGridPageing,
        closeGrid: closeGrid,
        openGrid: openGrid,
        dataGridPageNumSet: dataGridPageNumSet,
        getPagingSearchData: getPagingSearchData,
        getHtmlFormJsons: getHtmlFormJsons,
        closeDialogWindows: closeDialogWindows,
        showDialogWindows: showDialogWindows,
        getPagingSearchDataWithAsync: getPagingSearchDataWithAsync,
        getPageData: getPageData
    };

    if ($.C_OBJ_MAP == null || $.C_OBJ_MAP == "" || $.C_OBJ_MAP == undefined) {
        $.C_OBJ_MAP = {};
    }
    $.C_APPOBJ_MAP = $.extend($.C_OBJ_MAP, {"appCommon": appCommon});
    if ($.C_getCommon == null || $.C_getCommon == "" || $.C_getCommon == undefined) {
        $.C_getCommon = function (id) {
            return $.C_APPOBJ_MAP[id];
        }
    }
})(jQuery);
