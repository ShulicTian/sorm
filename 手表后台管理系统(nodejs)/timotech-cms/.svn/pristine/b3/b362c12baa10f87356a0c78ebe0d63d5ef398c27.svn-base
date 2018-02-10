/*****************************************************
 * Author  : wadecha
 * Version : 1.0
 * Date    :  2015/09/18
 ****************************************************/
var redis = require('redis');
var webConfig = require("../../config/web_config");

var opts = undefined;
if (webConfig.redis.pwd) {
	opts = {
		'auth_pass': webConfig.redis.pwd
	};
}
var redisClient = redis.createClient(webConfig.redis.port, webConfig.redis.host, opts);
redisClient.on('error', function (err) {
	console.log('redis error');
});

module.exports = exports = redisClient;