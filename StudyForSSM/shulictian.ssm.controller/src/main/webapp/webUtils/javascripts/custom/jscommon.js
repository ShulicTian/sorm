/*****************************************************
 * Author  : wadecha
 * Version : 1.0
 * Date    :  2015/6/24
 ****************************************************/
Date.prototype.format = function (format) {
    var o = {
        "Y+": this.getYear(), //year
        "M+": this.getMonth() + 1, //month
        "d+": this.getDate(), //day
        "h+": this.getHours(), //hour
        "m+": this.getMinutes(), //minute
        "s+": this.getSeconds() //second
    };

    if(/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }

    for(var k in o) {
        if(new RegExp("("+ k +")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
        }
    }
    return format;
};


function postJSON(url, data, callback, loadtext) {
    if (typeof data === 'function') {
        loadtext = callback;
        callback = data;
        data = null;
    }

    loadtext = loadtext || '数据处理中...';
    data = data || {};

    layer.msg(loadtext, {
        shade: [0, 'rgba(0,0,0,0)'],
        shadeClose: false
    });

    callback = callback || function () { };

    $.post(url, data, function (_res) {
        layer.closeAll();
        callback(_res);
    }, 'json').fail(function(_msg) {
        layer.closeAll();
        if(_msg.status === 403 && _msg.responseText === 'forbidden!'){
            layer.msg('您还没登录，请重新打开首页！', {
                offset: 0,
                shift: 6
            });
            return;
        }

        layer.msg('网络错误，请稍后重试！', {
            offset: 0,
            shift: 6
        });
    });
}



function postNotShadeJSON(url, data, callback) {
    if (typeof data === 'function') {
        callback = data;
        data = null;
    }

    data = data || {};

    callback = callback || function () { };

    $.post(url, data, function (_res) {
        callback(_res);
    }, 'json').fail(function(_msg) {
        layer.closeAll();
        if(_msg.status === 403 && _msg.responseText === 'forbidden!'){
            layer.msg('您还没登录，请重新打开首页！', {
                offset: 0,
                shift: 6
            });
            return;
        }

        layer.msg('网络错误，请稍后重试！', {
            offset: 0,
            shift: 6
        });
    });
}

function getJSON(url, data, callback, loadtext) {
    if (typeof data === 'function') {
        loadtext = callback;
        callback = data;
        data = null;
    }

    loadtext = loadtext || '加载中...';
    data = data || {};

    layer.msg(loadtext, {
        shade: [0, 'rgba(0,0,0,0)'],
        shadeClose: false
    });

    callback = callback || function () { };
    $.get(url, data, function (_res) {
        layer.closeAll();
        $('.init_hidden').show();
        callback(_res);
    }, 'json').fail(function(_msg) {
        layer.closeAll();
        if(_msg.status === 403 && _msg.responseText === 'forbidden!'){
            layer.msg('您还没登录，请重新打开首页！', {
                offset: 0,
                shift: 6
            });
            return;
        }

        layer.msg('网络错误，请稍后重试！', {
            offset: 0,
            shift: 6
        });
    });
}

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);  //获取url中"?"符后的字符串并正则匹配
    var context = "";
    if (r != null)
        context = r[2];
    reg = null;
    r = null;
    return context == null || context == "" || context == "undefined" ? "" : context;
}

function pageJump(pageUrl){
    window.location.href = pageUrl === 'myself' ? window.location.href : pageUrl;
}

function layerNoShadeAlert(content){
    layer.msg(content, {
        offset: 0,
        shift: 6
    });
}

function layerShadeCenterAlert(content){
    layer.msg(content, {
        shade: [0, 'rgba(0,0,0,0)'],
        shadeClose: false
    });
}

function layerPageFirstLoading() {
    $('#Loading').hide();
    layer.load(0, {shade: [1,'#fff']});
}

//
//function layerDataLoading(content){
//    layer.open({
//        style: 'border:none; min-width: 20px; background-color:rgba(0,0,0,.8); border-radius:3px; color:#fff; font-family:Microsoft YaHei, Arial, Helvetica; min-height: 30px; line-height: 30px; padding: 0px 15px;',
//        content: content,
//        shadeClose: false,
//        shade: 'background:none;'
//    });
//}
//
//function layerNoShadeDataLoading(content){
//    layer.open({
//        style: 'border:none; min-width: 20px; background-color:rgba(0,0,0,.8); border-radius:3px; color:#fff; font-family:Microsoft YaHei, Arial, Helvetica; min-height: 30px; line-height: 30px; padding: 0px 15px;',
//        content: content,
//        shadeClose: false,
//        shade: 'background:none;'
//    });
//}
//
//function layerConfirm(content, func){
//    layer.open({
//        style: 'border:none; background-color:#fff; border-radius:3px; font-family:Microsoft YaHei, Arial, Helvetica;',
//        mcontstyle: 'padding: 25px 20px;',
//        content: content,
//        shadeClose: false,
//        btn: ['取消', '确认'],
//        yes: function(){
//            layer.closeAll();
//        },
//        no: func
//    });
//}


function datagridLoader(param, success, error){
    var that = $(this);
    var gridType = that.context.id === 'treegd' ? 'treegrid' : 'datagrid';

    var opts = that[gridType]("options");
    if (!opts.url) {
        return false;
    }

    if(param && param.firstNotAjax){
        return false;
    }

    $.ajax({
        type : opts.method,
        url : opts.url,
        data : param,
        dataType : "json",
        success : function (data) {
            if(data.errcode === 1) {
                layerNoShadeAlert(data.errmsg);
            }
            success(data.retobj);
        },
        error : function () {
            error.apply(this, arguments);
        }
    });
}

function roleFormatter(value, row){
    return row.roleID ? (row.roleID == 1 ? '管理员' : '普通') : value;
}

function statusFormatter(value, row){
    return row.status == 1 ? '启用' : '禁用';
}

function genderFormatter(value, row){
    return row.gender == 0 ? '男' : '女';
}


$.extend($.fn.datagrid.methods, {
    fixRownumber : function (jq) {
        return jq.each(function () {
            var panel = $(this).datagrid("getPanel");
            //获取最后一行的number容器,并拷贝一份
            var clone = $(".datagrid-cell-rownumber", panel).last().clone();
            //由于在某些浏览器里面,是不支持获取隐藏元素的宽度,所以取巧一下
            clone.css({
                "position" : "absolute",
                left : -1000
            }).appendTo("body");
            var width = clone.width("auto").width();
            //默认宽度是25,所以只有大于25的时候才进行fix
            if (width > 25) {
                //多加5个像素,保持一点边距
                $(".datagrid-header-rownumber,.datagrid-cell-rownumber", panel).width(width + 5);
                //修改了宽度之后,需要对容器进行重新计算,所以调用resize
                $(this).datagrid("resize");
                //一些清理工作
                clone.remove();
                clone = null;
            } else {
                //还原成默认状态
                $(".datagrid-header-rownumber,.datagrid-cell-rownumber", panel).removeAttr("style");
            }
        });
    }
});