function g_showLoading(){
    var idx = layer.msg('processing...', {icon:16, shade:[0.5, '#f5f5f5'], scrollbar:false, offset:'0px', time:10000});
    return idx;
}
//salt
var g_password_salt = "ftd_seckill_salt";
//retrieve url parameter
function g_getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r != null)
        return unescape(r[2]);
    return null;
}
//
Date.prototype.format=function (format) {
    var args = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "H+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
    };
    if(/(y+)/.test(format))
        format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4-RegExp.$1.length));
    for(var i in args){
        var n = args[i];
        if(new RegExp("("+i+")").test(format))
            format = format.replace(RegExp.$1, RegExp.$1.length=1?n:("00"+n).substr((""+n).length));
    }
    return format;
};
