/*****************************************************
 * Author  : Administrator
 * Version : 1.0
 * Date    :  2014/6/15
 ****************************************************/
//nohup node some.js > /dev/null &

//var geography = require("./build/Release/geography.node");
//var IdGenerator = require("./lib/idGenerator");
var MessageCenter = require("./lib/messageCenter");
var Logger = require("./lib/logger");
//var PyramidBlock = require("./lib/pyramidBlock");
var SqlExecutor = require("./lib/sqlExecutor");
var EventBus = require("./lib/eventBus");
var ServiceClient = require("./lib/serviceClient");

//exports.geography = geography;
//exports.IdGenerator = IdGenerator;
exports.MessageCenter = MessageCenter;
exports.Logger = Logger;
//exports.PyramidBlock = PyramidBlock;
exports.SqlExecutor = SqlExecutor;
exports.EventBus = EventBus;
exports.ServiceClient = ServiceClient;

Date.minus = function (firstDate, secondDate) {
	var span = {};
	var spanTime = firstDate.getTime() - secondDate.getTime();
	span.totalDays = spanTime / (1.0 * 24 * 3600 * 1000);
	span.totalHours = spanTime / (1.0 * 3600 * 1000);
	span.totalMinutes = spanTime / (1.0 * 60 * 1000);
	span.totalSeconds = spanTime / (1.0 * 1000);
	span.totalMilliseconds = spanTime;
	return span;
};

Date.prototype.format = function (fmt) {
	fmt = fmt || 'yyyy-MM-dd hh:mm:ss';
	var o = {
		"M+": this.getMonth() + 1, //月份
		"d+": this.getDate(), //日
		"h+": this.getHours(), //小时
		"m+": this.getMinutes(), //分
		"s+": this.getSeconds(), //秒
		"q+": Math.floor((this.getMonth() + 3) / 3), //季度
		"S": this.getMilliseconds() //毫秒
	};
	if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o) {
		if (new RegExp("(" + k + ")").test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		}
	}
	return fmt;
};

Buffer.prototype.writeInt64LE = function (value, offset) {
	return geography.writeInt64LE(this, value, offset);
};

Buffer.prototype.readInt64LE = function (offset) {
	return geography.readInt64LE(this, offset);
};

Buffer.prototype.writeInt64BE = function (value, offset) {
	return geography.writeInt64BE(this, value, offset);
};

Buffer.prototype.readInt64BE = function (offset) {
	return geography.readInt64BE(this, offset);
};

Buffer.prototype.writeUInt24LE = function (value, offset) {
	return geography.writeUInt24LE(this, value, offset);
};

Buffer.prototype.readUInt24LE = function (offset) {
	return geography.readUInt24LE(this, offset);
};

Buffer.prototype.writeUInt24BE = function (value, offset) {
	return geography.writeUInt24BE(this, value, offset);
};

Buffer.prototype.readUInt24BE = function (offset) {
	return geography.readUInt24BE(this, offset);
};