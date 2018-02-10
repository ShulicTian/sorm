/*****************************************************
 * Author  : wadecha
 * Version : 1.0
 * Date    :  2015/9/18
 ****************************************************/

var utility = require('utility');
var i18n = require('../config/i18n_cn');
var auth = require('../middleware/auth');
var webConfig = require('../config/web_config');
var validator = require('../lib/common/validator_extend');

var RetJson = require('../proxy/retjson');
var adminDao = require('../lib/dao/admin_dao');

exports.login = function (req, res, next) {
	req.session.referer = req.headers.referer;
	res.locals._layoutFile = 'layout_login.html';
	res.render('login');
};


exports.signIn = function (req, res, next) {
	var userName = req.body.userName;
	var password = req.body.password;

	if (!(validator.isLength(userName, 2) && validator.isLength(password, 2))) {
		res.send(new RetJson(1, i18n.USER_LOGIN_INPUT_ERROR, null));
		return;
	}

	getUserByUserName(userName, password, function (err, _user) {
		if (err) {
			console.log(err);
			return res.send(new RetJson(1, err, null));
		}

		auth.genSession(_user.userID, res);
		res.send(new RetJson(0, i18n.SYS_SUCCESS_MESSAGE, null));
	});
};

function getUserByUserName(userName, password, callback) {
	adminDao.getUserByUserName(userName, function (err, _user) {
		if (err) {
			console.log(err);
			callback(i18n.SYS_ERROR_MESSAGE);
			return;
		}

		if (!_user) {
			callback(i18n.USER_LOGIN_USER_NOT_EXIST);
			return;
		}

		if (utility.md5(password) !== _user.password) {
			callback(i18n.USER_LOGIN_USER_PASSWORD_ERROR);
			return;
		}

		delete _user.password;
		callback(undefined, _user);
	});
}


exports.signout = function (req, res, next) {
	req.session.destroy();
	res.clearCookie(webConfig.auth_cookie_name, {path: '/'});
	res.redirect('/');
};