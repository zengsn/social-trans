/**
 * zhiyuan.guo
 */

var PROJECT_NAME = "PublicResource";

// 终止回退键
function disablebackspace() {
	function banBackSpace(e) {
		var ev = e || window.event;// 获取event对象
		var obj = ev.target || ev.srcElement;// 获取事件源
		var t = obj.type || obj.getAttribute('type');// 获取事件源类型
		// 获取作为判断条件的事件类型
		var vReadOnly = obj.readOnly;
		var vDisabled = obj.disabled;
		// 处理undefined值情况
		vReadOnly = (vReadOnly == undefined) ? false : vReadOnly;
		vDisabled = (vDisabled == undefined) ? true : vDisabled;
		// 当敲Backspace键时，事件源类型为密码或单行、多行文本的，
		// 并且readOnly属性为true或disabled属性为true的，则退格键失效
		var flag1 = ev.keyCode == 8
				&& (t == "password" || t == "text" || t == "textarea")
				&& (vReadOnly == true || vDisabled == true);
		// 当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
		var flag2 = ev.keyCode == 8 && t != "password" && t != "text"
				&& t != "textarea";
		// 判断
		if (flag2 || flag1)
			return false;
	}

	// 禁止退格键 作用于Firefox、Opera
	document.onkeypress = banBackSpace;
	// 禁止退格键 作用于IE、Chrome
	document.onkeydown = banBackSpace;
}
disablebackspace();
/**
 * 
 * 表格方法 封装 params{ // 表头 head :[{name:"字段名称",tilte:"表头显示的值"}], //数据
 * data:[{},{},{}], //行格式化 format:{ 字段对应的key:格式化的方法 } }
 */
$.fn.init_table = function(opt) {
	var head = {
		"head" : [],
		"data" : [],
		"format" : {

		},
		rowOnClick : function(data, tables, tr) {

		}

	}
	var colModel = [];
	var colNames = [];
	for (var i = 0; i < opt.head.length; i++) {
		var model = {};
		var head = opt.head;
		var format = opt.format;
		model.name = head[i].name;
		model.index = head[i].name;
		if (typeof format[head[i].name] == "function") {
			model.formatter = format[head[i].name];
		}
		colModel.push(model);
		colNames.push(head[i].title);
	}

	var $this = $(this);
	var $pager = $('<div id="common_jqgrid_pager"></div>').insertAfter($this);
	var $jqgrid = $this.jqGrid({
		data : opt.data,
		datatype : "local",
		height : 393,
		colNames : colNames,
		colModel : colModel,
		viewrecords : true,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : "#common_jqgrid_pager",
		altRows : true,
		multiselect : false,
		multiboxonly : true,
		rownumbers : true,
		autowidth : true,
		loadComplete : function() {
			var table = this;
			setTimeout(function() {
				// styleCheckbox(table);

				// updateActionIcons(table);
				updatePagerIcons(table);
				// enableTooltips(table);
			}, 0);
		},
	});

}

function updatePagerIcons(table) {
	var replacement = {
		'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
		'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
		'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
		'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
	};
	$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon')
			.each(function() {
				var icon = $(this);
				var $class = $.trim(icon.attr('class').replace('ui-icon', ''));

				if ($class in replacement)
					icon.attr('class', 'ui-icon ' + replacement[$class]);
			})
}
// 弹窗，先用easyUI 的
function msgShow(title, msgString) {
	bootbox.alert(msgString);
}
function msgConfirm(title, msgString, callback) {
	bootbox.confirm(msgString, callback);
}

/*******************************************************************************
 * 表单序列化
 * 
 * @param id
 *            dom id
 * @return object
 */
function formToJsonstr(fromId) {
	var params = {};

	$('#' + fromId + ' :input').each(
			function(index, obj) {
				var type = obj.type;
				var tag = obj.tagName.toLowerCase();
				var $obj = $(obj);
				var value = $obj.val();
				var key = $obj.attr('name');

				if ((type == 'text' || type == 'password' || type == 'hidden'
						|| tag == 'textarea' || tag == 'select')
						&& key != undefined && key != '') {

					var sType = $obj.attr('data-sType');
					if (sType == "date") {
						if (value != "" && value != "0001-01-01 00:00:00")
							params[key] = $obj.val();
					} else {
						params[key] = $obj.val();
					}

				}
				if (type == 'radio') {
					if ($obj.is(':checked') == true) {
						params[key] = $obj.val();

					}
				}
				if (type == 'checkbox') {
					if ($obj.is(':checked') == true) {

						if (key != null && key != 'undefined') {
							alert($obj.val());
							params[key] = $obj.val();
						}
					}

				}

			});
	return params;
}
var maskContent = {
	css : {
		border : 'none',
		padding : '15px',
		backgroundColor : '#000',
		'-webkit-border-radius' : '10px',
		'-moz-border-radius' : '10px',
		opacity : .5,
		color : '#fff',
		font : '14px'
	},
	baseZ : 9999,
	message : '<img src="/' + PROJECT_NAME
			+ '/img/wait.gif" border="0" />  正在处理中，请稍候...'
};

function ajaxFunction(url, params, fn, sync) {
	// $.blockUI(maskContent);

	if (sync == undefined || sync == null) {
		sync = true;

	}
	$.ajax({
		async : sync,
		type : "post",
		url : url,
		dataType : "json",
		data : params,
		success : function(data) {
			if ($.isFunction(fn)) {
				try {
					fn(data);
				} catch (e) {
					alert(e.stack);
				}
			}
			// 关闭遮罩
			// $.unblockUI();
		},
		error : function(r) {
			alert("error");
			// $.unblockUI();
		}
	});

}

// 初始化字典项
function initSelect(data, dict_key_key, dict_val_key, isAppendempty) {
	var ops = "";
	for (var i = 0; i < data.length; i++) {
		ops += "<option value='" + data[i][dict_key_key] + "'>"
				+ data[i][dict_val_key] + "</option>";
	}
	if (isAppendempty) {
		return " <option value=''></option>" + ops;
	}
	return ops;
}
function initDict(type, $dom, isAppendempty) {
	var params = {};
	params.type = type;
	var url = "/" + PROJECT_NAME + "/dict/list.do";
	ajaxFunction(url, params, function(data) {
		if (data.success) {
			var restr = initSelect($.parseJSON(data.data), "dict_key",
					"dict_value", isAppendempty);
			$dom.append(restr);
		} else {
			alert("字典项加载失败");
		}
	}, false);

}
var fomateDate = function(formatStr, date) {
	var str = formatStr;
	str = str.replace(/yyyy|YYYY/, date.getFullYear());
	str = str.replace(/yy|YY/,
			(date.getYear() % 100) > 9 ? (date.getYear() % 100).toString()
					: '0' + (date.getYear() % 100));
	str = str.replace(/MM/, (date.getMonth() + 1) > 9 ? (date.getMonth() + 1)
			: '0' + (date.getMonth() + 1));
	str = str.replace(/dd|DD/, date.getDate() > 9 ? date.getDate().toString()
			: '0' + date.getDate());
	str = str.replace(/hh|HH/, date.getHours() > 9 ? date.getHours().toString()
			: '0' + date.getHours());
	str = str.replace(/mm/, date.getMinutes() > 9 ? date.getMinutes()
			.toString() : '0' + date.getMinutes());
	str = str.replace(/ss|SS/, date.getSeconds() > 9 ? date.getSeconds()
			.toString() : '0' + date.getSeconds());
	return str;
}
/**
 * 打印表格
 * 
 * @param content
 */
function printTableById(domId) {
	var content = encodeURI($("#" + domId).html())
	var url = "/" + PROJECT_NAME + "/print/printTable.do"
	var $form = $(
			'<form action='
					+ url
					+ ' target= "_blank" method="post" style="display:none;"><input type="text" name="content" value='
					+ content + '></form>').appendTo($("body"))
	$form.submit();
	$form.remove();
}
function printTable(content) {
	var content = encodeURI(content)
	var url = "/" + PROJECT_NAME + "/print/printTable.do"
	var $form = $(
			'<form action='
					+ url
					+ ' target= "_blank" method="post" style="display:none;"><input type="text" name="content" value='
					+ content + '></form>').appendTo($("body"))
	$form.submit();
	$form.remove();
}
function printForm(content) {
	var content = encodeURI(content)
	var url = "/" + PROJECT_NAME + "/print/printForm.do"
	var $form = $(
			'<form action='
					+ url
					+ ' target= "_blank" method="post" style="display:none;"><input type="text" name="content" value='
					+ content + '></form>').appendTo($("body"))
	$form.submit();
	$form.remove();
}

function delCookie(name) {
	setCookie(name, "", -1)
}
function setCookie(cname, cvalue, exdays) {
	var d = new Date();
	d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
	var expires = "expires=" + d.toUTCString();
	document.cookie = cname + "=" + cvalue + "; " + expires;
}
function getCookieValue(cookieName) {
	var cookieValue = document.cookie;
	var cookieStartAt = cookieValue.indexOf("" + cookieName + "=");
	if (cookieStartAt == -1) {
		cookieStartAt = cookieValue.indexOf(cookieName + "=");
	}
	if (cookieStartAt == -1) {
		cookieValue = null;
	} else {
		cookieStartAt = cookieValue.indexOf("=", cookieStartAt) + 1;
		cookieEndAt = cookieValue.indexOf(";", cookieStartAt);
		if (cookieEndAt == -1) {
			cookieEndAt = cookieValue.length;
		}
		cookieValue = unescape(cookieValue
				.substring(cookieStartAt, cookieEndAt));// 解码latin-1
	}
	return cookieValue;
}

var getQueryString = function(name) {
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
/*******************************************************************************
 * 显示车辆信息dialog
 */
function showCarListDialog(callbackFn) {
	var $carDialog = $("#common_car-modal");
	if ($carDialog[0] == undefined) {
		$carDialog = $(
				'<div tabindex="-1" class="modal fade in" id="common_car-modal" aria-hidden="false" >'
						+ '<div class="modal-dialog modal-lg">'
						+ '<div class="modal-content">'
						+ '<div class="modal-header">'
						+ '<button class="close" aria-hidden="true" type="button" data-dismiss="modal">×</button>'
						+ '<h3 class="smaller lighter blue no-margin">车辆信息</h3>'
						+ '</div>'
						+ '<div class="modal-body" id="common_carTable_body">'
						+ '<div id="jqGridCarheader">'
						+ '<button class="btn btn-white btn-info btn-bold" id="common_car_btn"><i class="ace-icon fa fa-floppy-o bigger-120 blue"></i>确定</button>'
						+ ' </div>'
						+ '<table id="common_carList_table"></table>'
						+ '<div id="common_carList_table_pager"></div>'
						+ '</div>' + '</div></div></div>').appendTo("body");
		var grid_config = {
			datatype : "local",
			width : "100%",
			height : "375px",
			colModel : [ {
				"name" : "plate_number",
				"label" : "车牌号"
			}, {
				"name" : "brand_model",
				"label" : "品牌型号",
				"width" : 250
			}, {
				"name" : "models",
				"label" : "车型",
				"width" : 250
			}, {
				"name" : "disposal_method",
				"label" : "车辆状态"
			}, {
				"name" : "id",
				"label" : "id",
				"hidden" : true
			} ],
			viewrecords : true,
			rowNum : 10,
			rowList : [ 10, 20, 30 ],
			pager : "#common_carList_table_pager",
			altRows : true,
			multiselect : false,
			multiboxonly : true,
			rownumbers : true,
			autowidth : true,
			loadComplete : function() {
				var table = this;
				setTimeout(function() {
					// styleCheckbox(table);
					// updateActionIcons(table);
					updatePagerIcons(table);
					// enableTooltips(table);
				}, 0);
			},
			ondblClickRow : function(rowid, iRow, iCol, e) {

				// alert(JSON.stringify($('#common_driverList_table').getRowData(rowid)))
			}
		}
		$('#common_carList_table').jqGrid(grid_config);

	}

	var loadCarData = function() {
		var params = {};
		var url = "/" + PROJECT_NAME + "/vehicle/list.do";
		ajaxFunction(url, params, function(result) {
			if (result.success) {
				// jqgrid.clearGridData();
				var data = result.data;
				$("#common_carList_table").jqGrid('setGridParam', {
					"data" : data
				}).trigger("reloadGrid");
			}
		});
	}
    var clickFn=function() {

		var rowId = $('#common_carList_table').jqGrid(
				'getGridParam', 'selrow');
		if (rowId == null) {
			alert("请选则一条数据");
			$("#common_car_btn").one("click",clickFn);
			return;
		} else {
			if (typeof callbackFn == "function") {
				callbackFn($('#common_carList_table')
						.getRowData(rowId))
				$("#common_car-modal").modal("hide");
			} else
				alert('回调参数应该一个方法');
		}
	}
	$("#common_car_btn").one("click",clickFn);
	loadCarData();
	$("#common_car-modal").modal("show");

}

/*******************************************************************************
 * 显示驾驶员信息dialog
 */
function showDriverListDialog(callbackFn) {
	$.jgrid.defaults.responsive = true;
	var $driverDialog = $("#common_driverTable_body");
	if ($driverDialog[0] == undefined) {
		$(
				'<div tabindex="-1" class="modal fade in" id="common_driver-modal" aria-hidden="false" >'
						+ '<div class="modal-dialog modal-lg">'
						+ '<div class="modal-content">'
						+ '<div class="modal-header">'
						+ '<button class="close" aria-hidden="true" type="button" data-dismiss="modal">×</button>'
						+ '<h3 class="smaller lighter blue no-margin">驾驶员信息</h3>'
						+ '</div>'
						+ '<div class="modal-body" id="common_driverTable_body">'
						+ '<div id="jqGridheader">'
						+ '<button class="btn btn-white btn-info btn-bold" id="common_driver_btn"><i class="ace-icon fa fa-floppy-o bigger-120 blue"></i>确定</button>'
						+ ' </div>'
						+ '<table id="common_driverList_table"></table>'
						+ '<div id="common_driverList_table_pager"></div>'
						+ '</div>' + '</div></div></div>').appendTo("body");
		;

		// $driverDialog.append('<div id="jqGridheader"></div>')
		// .append('<table id="common_driverList_table" fit=true></table>')
		// .append('<div id="common_driverList_table_pager"></div>');
		var grid_config = {
			datatype : "local",
			width : "100%",
			height : "375px",
			colModel : [ {
				"name" : "name",
				"label" : "姓名"
			}, {
				"name" : "driving_type",
				"label" : "准驾车型",
				"width" : 250
			}, {
				"name" : "telephone",
				"label" : "联系方式",
				"width" : 250
			}, {
				"name" : "personnal_status",
				"label" : "司机状态"
			}, {
				"name" : "driver_id",
				"label" : "driver_id",
				"hidden" : true
			} ],
			viewrecords : true,
			rowNum : 10,
			rowList : [ 10, 20, 30 ],
			pager : "#common_driverList_table_pager",
			altRows : true,
			multiselect : false,
			multiboxonly : true,
			rownumbers : true,
			autowidth : true,
			loadComplete : function() {
				var table = this;
				setTimeout(function() {
					// styleCheckbox(table);
					// updateActionIcons(table);
					updatePagerIcons(table);
					// enableTooltips(table);
				}, 0);
			},
			ondblClickRow : function(rowid, iRow, iCol, e) {

				// alert(JSON.stringify($('#common_driverList_table').getRowData(rowid)))
			}
		}
		$('#common_driverList_table').jqGrid(grid_config);

		/*
		 * $('#common_driverList_table').jqGrid( "navGrid",
		 * "#common_driverList_table_pager", { edit : false, add : false, del :
		 * false, search : true, searchicon : 'ace-icon fa fa-search orange',
		 * refresh : true, refreshicon : 'ace-icon fa fa-refresh green', }, {}, //
		 * edit options {}, // add options {}, // delete options { // search
		 * form recreateForm : true, afterShowSearch : function(e) { var form =
		 * $(e[0]); form.closest('.ui-jqdialog').find('.ui-jqdialog-title')
		 * .wrap('<div class="widget-header" />') style_search_form(form); },
		 * afterRedraw : function() { style_search_filters($(this)); },
		 * multipleSearch : true
		 * 
		 * }// search options - define multiple search );
		 */

		/*
		 * $('#common_driverList_table').navGrid('#t_common_driverList_table', {
		 * edit : true, add : true, del : true, search : true, refresh : true,
		 * view : false, position : "left", cloneToTop : true });
		 */

	}

	var loadDriverData = function() {
		var params = {};
		var url = "/" + PROJECT_NAME + "/driver/listAll.do";
		ajaxFunction(url, params, function(result) {
			if (result.success) {
				var data = result.data;
				// jqgrid.clearGridData();
				$("#common_driverList_table").jqGrid('setGridParam', {
					"data" : data
				}).trigger("reloadGrid");

				// jqgrid.addJSONData(data);
				// tabledata.rows = data;
				// tabledata.length = data.length;
				// $('#common_driverList_table').jqGrid(grid_config);
			}
		});
	}
	
	var clickFn=function() {

		var rowId = $('#common_driverList_table').jqGrid(
				'getGridParam', 'selrow');
		if (rowId == null) {
			alert("请选则一条数据");
			$("#common_driver_btn").one("click",clickFn);
			return;
		} else {
			if (typeof callbackFn == "function") {
				callbackFn($('#common_driverList_table').getRowData(
						rowId))
				$("#common_driver-modal").modal("hide");
			} else
				alert('回调参数应该一个方法');
		}
	}
	$("#common_driver_btn").one("click",clickFn);
	loadDriverData();
	$("#common_driver-modal").modal("show");
	// $driverDialog.dialog("open");
}

function style_search_filters(form) {
	form.find('.delete-rule').val('X');
	form.find('.add-rule').addClass('btn btn-xs btn-primary');
	form.find('.add-group').addClass('btn btn-xs btn-success');
	form.find('.delete-group').addClass('btn btn-xs btn-danger');
}
function style_search_form(form) {
	var dialog = form.closest('.ui-jqdialog');
	var buttons = dialog.find('.EditTable')
	buttons.find('.EditButton a[id*="_reset"]').addClass('btn btn-sm btn-info')
			.find('.ui-icon').attr('class', 'ace-icon fa fa-retweet');
	buttons.find('.EditButton a[id*="_query"]').addClass(
			'btn btn-sm btn-inverse').find('.ui-icon').attr('class',
			'ace-icon fa fa-comment-o');
	buttons.find('.EditButton a[id*="_search"]').addClass(
			'btn btn-sm btn-purple').find('.ui-icon').attr('class',
			'ace-icon fa fa-search');
}

/**
 * 民族
 */
var national = [ "汉族", "壮族", "满族", "回族", "苗族", "维吾尔族", "土家族", "彝族", "蒙古族",
		"藏族", "布依族", "侗族", "瑶族", "朝鲜族", "白族", "哈尼族", "哈萨克族", "黎族", "傣族", "畲族",
		"傈僳族", "仡佬族", "东乡族", "高山族", "拉祜族", "水族", "佤族", "纳西族", "羌族", "土族",
		"仫佬族", "锡伯族", "柯尔克孜族", "达斡尔族", "景颇族", "毛南族", "撒拉族", "布朗族", "塔吉克族",
		"阿昌族", "普米族", "鄂温克族", "怒族", "京族", "基诺族", "德昂族", "保安族", "俄罗斯族", "裕固族",
		"乌孜别克族", "门巴族", "鄂伦春族", "独龙族", "塔塔尔族", "赫哲族", "珞巴族" ];
function initNational() {

}
