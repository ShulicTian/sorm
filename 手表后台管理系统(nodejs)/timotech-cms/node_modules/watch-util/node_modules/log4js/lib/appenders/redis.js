/*****************************************************
 * Author  : Administrator
 * Version : 1.0
 * Date    :  2015/3/21
 ****************************************************/
"use strict";
var layouts = require('../layouts');
var redis = require('redis');
var util = require('util');
function redisAppender(host, port, pwd, channel, layout) {
	layout = layout || layouts.messagePassThroughLayout;
	var opts = undefined;
	if (pwd) {
		opts = {
			auth_pass: pwd
		};
	}
	var redisClient = redis.createClient(port, host, opts);
	redisClient.on('error', function (err) {
		if (err) {
			console.error(
					"log4js.redisAppender - %s:%p Error: %s", host, port, util.inspect(err)
			);
		}
	});
	return function (loggingEvent) {
		var message = layout(loggingEvent);
		redisClient.publish(channel, message, function (err) {
			if (err) {
				console.error(
						"log4js.redisAppender - %s:%p Error: %s", host, port, util.inspect(err)
				);
			}
		});
	};
}

function configure(config) {
	var layout, host, port, pwd, channel;
	if (config.layout) {
		layout = layouts.layout(config.layout.type, config.layout);
	}
	host = config.host ? config.host : '127.0.0.1';
	port = config.port ? config.port : 6379;
	pwd = config.pwd ? config.pwd : null;
	channel = config.channel ? config.channel : 'log';

	return redisAppender(host, port, pwd, channel, layout);
}

exports.appender = redisAppender;
exports.configure = configure;
