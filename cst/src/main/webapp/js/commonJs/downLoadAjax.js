/**
 * 下载文件
 * @param data 附件id
 * @param fn 下载文件相应函数
 * @param sync
 * @constructor
 */
function DownLoadFunction(projectName,data , fn, sync) {
    if (sync == undefined || sync == null) {
        sync = true;

    }
    var urlDownLoad="/"+projectName+"/downAndUpLoadFileClientAction/downLoad.do";
    //var urlPost="/downAndUpLoadFileClientAction/downLoad.do";
    $.ajax({
        url: urlDownLoad,
        type: "POST",
        cache: false,
        data:data,

        success: function(filename) {

           if ($.isFunction(fn)) {
                try {
                    fn(filename);
                } catch (e) {
                    alert("失败");
                }
            }
            var url = urlDownLoad + (((urlDownLoad.indexOf("?") > 0) ? "&" : "?") + $.param(data));
            $(document.body).append("<iframe height='0' width='0' frameborder='0'  src=" + url + "></iframe>")
        },error: function (data, status, e)//服务器响应失败处理函数
        {

            alert("下载失败");


        }

    });
}

/**
 * 上传文件
 * @param str 文件域id
 * @param fn 上传文件响应函数
 * @param sync
 * @returns {boolean}
 */
function FileUploadFunction(projectName,str, fn, sync){
    if (sync == undefined || sync == null) {
        sync = true;

    }

    var urlUpLoad = "/"+ projectName + "/FileAction/upLoadFile.do";
    $.ajaxFileUpload
    (
        {
            //url: "/UpAndDownFileClient/downAndUpLoadFileClientAction/upLoadFile.do", //用于文件上传的服务器端请求地址
            url: urlUpLoad, //用于文件上传的服务器端请求地址
            secureuri: false, //是否需要安全协议，一般设置为false*/
            fileElementId: str, //文件上传域的ID
            dataType: 'text', //返回值类型 一般设置为json
            success: function(data,status,e) {

                if ($.isFunction(fn)) {
                    try {
                        var result = eval( "(" + data + ")" );
                        fn(result);
                    } catch (e) {
                        alert("成功");
                    }
                }
            },
            error: function (data, status, e)//服务器响应失败处理函数
            {
                alert("上传失败"+data);
            }
        }
    )
    return true;
}