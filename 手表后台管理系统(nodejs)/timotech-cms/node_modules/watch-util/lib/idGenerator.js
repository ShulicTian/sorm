/**
 * Created by Administrator on 2015/7/5.
 */

//代码参考自twitter的分布式自增ID算法snowflake实现分析及其Java
var util = require("util");
var geography = require("../build/Release/geography.node");

var TWEPOCH = 1484201323141;
var WORKERIDBITS = 7;
var MAXWORKERID = -1 ^ (-1 << WORKERIDBITS);
var SEQUENCEBITS = 5;

var WORKERIDSHIFT = SEQUENCEBITS;
var TIMESTAMPLEFTSHIFT = SEQUENCEBITS + WORKERIDBITS;
var SEQUENCEMASK = -1 ^ (-1 << SEQUENCEBITS);

function IdGenerator(workerID) {
	if (workerID > MAXWORKERID || workerID < 0) {
		throw new Error(util.format("worker ID can't be greater than %d or less than 0", MAXWORKERID));
	}
	this.workerID = workerID;
	this.lastTimestamp = -1;
	this.sequence = 0;
}

IdGenerator.prototype.nextID = function () {

	var timestamp = new Date().getTime();
	if (this.lastTimestamp == timestamp) {
		//当前毫秒内，加1
		this.sequence = (this.sequence + 1) & SEQUENCEMASK;
		if (this.sequence == 0) {
			//当前毫秒内计数满了，则等待下一毫秒
			timestamp = tillNextMillis(this.lastTimestamp);
		}
	} else {
		this.sequence = 0;
	}
	if (timestamp < this.lastTimestamp) {
		throw new Error(util.format("Clock moved backwards. Refusing to generate id for %d milliseconds",
				this.lastTimestamp - timestamp));
	}

	this.lastTimestamp = timestamp;
	return geography.leftShift(timestamp - TWEPOCH, TIMESTAMPLEFTSHIFT) +
			(this.workerID << WORKERIDSHIFT) + this.sequence;

};

//等待下一毫秒的到来
function tillNextMillis(lastTimestamp) {
	var timestamp = new Date().getTime();
	while (timestamp <= lastTimestamp) {
		timestamp = new Date().getTime();
	}
	return timestamp;
}

module.exports = exports = IdGenerator;
