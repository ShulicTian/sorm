/**
 * Created by weiz on 2016/9/9.
 */

var request = require('superagent');

function ServiceClient(host, port, https) {
	this.host = host;
	this.port = port;
	this.https = https || false;
}

ServiceClient.prototype.invoke = function (path, param, callback) {
	var url = getUrl(this.host, this.https, this.port, path);
	request.post(url).set('Accept', 'application/json').set('Content-Type', 'application/x-www-form-urlencoded').
			send(param).end(handle(path, callback));
};

ServiceClient.prototype.invokeJson = function (path, param, callback) {
	var url = getUrl(this.host, this.https, this.port, path);
	request.post(url).set('Accept', 'application/json').set('Content-Type', 'application/json').
			send(param).end(handle(path, callback));
};

function handle(path, callback) {
	return function (err, res) {
		if (!err && res.ok) {
			var type = res.headers['content-type'] || 'text/plain';
			type = type.split('/');
			type = type[0];
			if (type == 'text') {
				callback(null, res.text);
			} else {
				callback(null, res.body);
			}
		} else {
			var error = null;
			if (res && res.body && res.body.code) {
				error = new Error(res.body.message);
				error.status = res.body.code;
			} else {
				error = new Error(err.message);
				error.status = err.status || 1000;
				error.status = err.status > 1000 ? err.status : 1000;
			}
			error.path = path;
			callback(error);
		}
	}
}


function getUrl(host, https, port, path) {
	var url = ( https === true ? 'https' : 'http') + '://' + host + ":" + port + '/' + path;
	return url;
}

module.exports = exports = ServiceClient;


