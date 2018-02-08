/*****************************************************
 * Author  : Administrator
 * Version : 1.0
 * Date    :  2014/12/20
 ****************************************************/

var log4js = require('log4js');

function Logger(logCofig) {
	var appenders = logCofig.appenders.filter(function (appender) {
		if (appender.category === logCofig.logger.appender) {
			return true;
		}
		return false;
	});

	log4js.configure({
		appenders: appenders
	});

	var logger = log4js.getLogger(logCofig.logger.appender);
	logger.setLevel(logCofig.logger.level);
	return logger;
}

module.exports = exports = Logger;