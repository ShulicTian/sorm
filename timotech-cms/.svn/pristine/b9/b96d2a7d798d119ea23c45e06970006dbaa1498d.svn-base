/*****************************************************
 * Author  : wadecha
 * Version : 1.0
 * Date    :  2015/09/18
 ****************************************************/

var mongoConfig = require("../../config/web_config").mongoConfig;
var mongo = require('mongoskin');

var mongoDB = mongo.db(mongoConfig.connstr, {
	safe: true
});

module.exports = exports = mongoDB;