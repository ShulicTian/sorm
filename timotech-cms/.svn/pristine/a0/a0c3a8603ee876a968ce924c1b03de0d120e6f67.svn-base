/**
 * Created by Administrator on 2016/9/24.
 */
var ServiceClient = require('../lib/serviceClient');
var client = new ServiceClient('127.0.0.1', 8080);
client.invokeJson('/baby/test', {
			"name": "keman",
			"phone": "13618487309",
			"tests": ["111", "222", "333"]
		},
		function (err, result) {
			console.log(err);
			console.log(result);
		});