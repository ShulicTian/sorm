/*****************************************************
 * Author  : wadecha
 * Version : 1.0
 * Date    :  2015/6/11
 ****************************************************/

var _ = require('lodash');
var adminDao = require('../lib/dao/admin_dao');
var config = require('../config/web_config');

/**
 * 需要登录
 */
exports.userRequired = function (req, res, next) {
	if (!(req.session && req.session.user)) {
		return res.redirect('/login');
	}

	res.locals.current_user = req.session.user;
	next();
};

exports.adminRequired = function (req, res, next) {
	if (!req.session.user) {
		return res.redirect('/login');
	}

	if (!req.session.user.isAdmin) {
		return res.status(403).send('only admin access!');
	}
	next();
};

function genSession(userID, res) {
	var authToken = userID + '$$'; // 以后可能会存储更多信息，用 $$$$ 来分隔
	res.cookie(config.auth_cookie_name, authToken,
			{path: '/', maxAge: 1000 * 60 * 60 * 24 * 30, signed: true, httpOnly: true}); //cookie 有效期30天
}

exports.genSession = genSession;


// 验证用户是否登录
exports.authUser = function (req, res, next) {

	if (req.session && req.session.user) {
		res.locals.current_user = req.session.user;
		next();
		return;
	}

	var authToken = req.signedCookies[config.auth_cookie_name];
	if (!authToken) {
		next();
		return;
	}

	var userID = authToken.split('$$')[0];
	adminDao.getUserByUserID(userID, function (err, _user) {
		if (err) {
			console.log(err);
			next();
			return;
		}

		if (!_user) {
			next();
			return;
		}

		if (_user.status === 0) {
			next();
			return;
		}

		_user.isAdmin = _user.roleID === 1;
		delete _user.password;
		delete _user.roleID;

		res.locals.current_user = req.session.user = _user;
		next();
	});
};