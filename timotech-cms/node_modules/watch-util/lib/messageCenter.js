/**
 * Created by Administrator on 2015/8/19.
 */

var events = require('events');
var redis = require('redis');
var util = require('util');

function MessageCenter(host, port, pwd) {
	events.EventEmitter.call(this);
	var _this = this;
	this.messageHandlers = {};
	var opts = undefined;
	if (pwd) {
		opts = {
			auth_pass: pwd
		};
	}
	this.redisClient = redis.createClient(port, host, opts);
	this.redisClient.on('error', function (err) {
		_this.emit('error', err);
	});

	this.redisClient.on('message', function (topic, message) {
		var topicHandlers = _this.messageHandlers[topic];
		if (!topicHandlers || topicHandlers.length == 0) {
			return;
		}
		topicHandlers.forEach(function (handler) {
			handler.handle(topic, message);
		});
	});
	this.pubRedisClient = redis.createClient(port, host, opts);
	this.pubRedisClient.on('error', function (err) {
		_this.emit('error', err);
	});
}
util.inherits(MessageCenter, events.EventEmitter);

MessageCenter.prototype.addMessageHandler = function (topic, handler) {
	var topicHandlers = this.messageHandlers[topic];
	if (!topicHandlers) {
		topicHandlers = [];
		this.messageHandlers[topic] = topicHandlers;
		this.redisClient.subscribe(topic);
	}
	topicHandlers.push(handler);
};

MessageCenter.prototype.publish = function (topic, message, callback) {
	if (!topic || !message) {
		return callback(new Error('topic or message can not empty'));
	}

	if (typeof message !== "string") {
		message = JSON.stringify(message);
	}
	this.pubRedisClient.publish(topic, message, callback);
};

MessageCenter.prototype.push = function (queue, message, callback) {
	if (!queue || !message) {
		return callback(new Error('queue or message can not empty'));
	}

	if (typeof message !== "string") {
		message = JSON.stringify(message);
	}
	this.pubRedisClient.rpush(queue, message, callback);
};

MessageCenter.prototype.pop = function (queue, callback) {
	this.pubRedisClient.lpop(queue, callback);
};

module.exports = exports = MessageCenter;