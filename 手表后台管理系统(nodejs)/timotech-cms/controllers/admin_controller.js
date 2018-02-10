/*****************************************************
 * Author  : wadecha
 * Version : 1.0
 * Date    :  2015/9/18
 ****************************************************/

var _ = require('lodash');
var moment = require('moment');
var async = require('async');
var utility = require('utility');
var i18n = require('../config/i18n_cn');
var adminDao = require('../lib/dao/admin_dao');
var webConfig = require('../config/web_config');
var RetJson = require('../proxy/retjson');
var Organ = require('../proxy/organ');
var validator = require('../lib/common/validator_extend');

exports.index = function (req, res, next) {
	res.render('index');
};

exports.updatePswd = function (req, res, next) {
	res.render('admin/update_pswd');
};

exports.user = function (req, res, next) {
	res.render('admin/user');
};

exports.organ = function (req, res, next) {
	res.render('admin/organ');
};


exports.saveUpdatePswd = function (req, res, next) {
	var userID = req.session.user.userID;
	var oldPswd = req.body.oldPswd;
	var newPswd = req.body.newPswd;
	var confirmPswd = req.body.confirmPswd;

	if (!(validator.isLength(oldPswd, 2) && validator.isLength(newPswd, 2) && validator.isLength(confirmPswd, 2))) {
		res.send(new RetJson(1, i18n.USER_UPDATE_PASSWORD_INPUT_ERROR, null));
		return;
	}

	if (newPswd !== confirmPswd) {
		res.send(new RetJson(1, i18n.USER_UPDATE_NEW_CONFIRM_PASSWORD_DIFFERENT, null));
		return;
	}

	function _getUserID(callback) {
		callback(undefined, userID, oldPswd);
	}

	function _getUserID1(callback) {
		callback(undefined, userID, utility.md5(newPswd));
	}

	async.waterfall([
		_getUserID,
		getUserByUserID,
		_getUserID1,
		adminDao.updateUserPassword
	], function (err, updateResult) {
		if (err) {
			console.log(err);
			if (err.hasOwnProperty('errcode')) {
				res.send(new RetJson(1, err.errmsg, null));
			} else {
				res.send(new RetJson(1, i18n.SYS_ERROR_MESSAGE, null));
			}
			return;
		}

		res.send(new RetJson(0, i18n.SYS_SUCCESS_MESSAGE, null));
	});
};

function getUserByUserID(userID, oldPswd, callback) {

	adminDao.getUserUpdatePasswordByUserID(userID, function (err, _user) {
		if (err) {
			callback(err);
			return;
		}

		if (!_user || utility.md5(oldPswd) !== _user.password) {
			callback(new RetJson(i18n.CODE_UPDATE_PASSWORD_ERROR, i18n.USER_UPDATE_OLD_PASSWORD_ERROR, null));
			return;
		}

		callback(undefined);
	});
}

exports.getOrganList = function (req, res, next) {
	var currentOrganID = req.session.user.organID;

	if (!validator.isNotZeroInt(currentOrganID)) {
		res.send(new RetJson(i18n.SYS_ERROR_CODE, i18n.SYS_ERROR_MESSAGE, []));
	}

	function getCurrentOrganID(callback) {
		callback(undefined, currentOrganID);
	}

	function getCurrentOrganTree(currentOrgan, callback) {
		(new Organ()).getFullTree.call(currentOrgan, callback);
	}

	async.waterfall([
		getCurrentOrganID,
		adminDao.getUserCurrentOrgan,
		getCurrentOrganTree
	], function (err, treeData) {
		if (err) {
			console.log(err);
			res.send(new RetJson(i18n.SYS_ERROR_CODE, i18n.SYS_ERROR_MESSAGE, []));
			return;
		}

		res.send(new RetJson(i18n.SYS_SUCCESS_CODE, i18n.SYS_SUCCESS_MESSAGE, [treeData]));
	});
};

exports.saveOrgan = function (req, res, next) {
	var model = req.body.jsonStrData;

	if (!validator.isJSON(model)) {
		res.send(new RetJson(i18n.SYS_ERROR_CODE, i18n.SYS_ERROR_MESSAGE, null));
		return;
	}

	model = JSON.parse(model);

	if (model.isnewrecord) {
		adminDao.insertOrganInfo(model, function (err, insertResult) {
			if (err) {
				console.log(err);

				res.send(new RetJson(i18n.SYS_ERROR_CODE, i18n.SYS_ERROR_MESSAGE, null));
				return;
			}

			var insertSuccess = (insertResult && insertResult.length > 0 && insertResult[0] && insertResult[0].insertId > 0) ? insertResult[0].insertId : 0;

			model.organID = insertSuccess;
			res.send(insertSuccess === 0 ? new RetJson(i18n.SYS_ERROR_CODE, i18n.SYS_ERROR_MESSAGE, 0)
					: new RetJson(i18n.SYS_SUCCESS_CODE, i18n.SYS_SUCCESS_MESSAGE, insertSuccess));
		});
	} else {
		adminDao.updateOrganInfo(model, function (err, updateResults) {
			if (err) {
				console.log(err);
			}

			var updateResult = (updateResults && updateResults.length > 1) ? updateResults[0] : undefined;
			res.send((updateResult && updateResult.affectedRows > 0) ? new RetJson(i18n.SYS_SUCCESS_CODE, i18n.SYS_SUCCESS_MESSAGE, null)
					: new RetJson(i18n.SYS_ERROR_CODE, i18n.SYS_ERROR_MESSAGE, null));
		});
	}
};


exports.getDisplayOrganList = function (req, res, next) {
	var currentOrganID = req.session.user.organID;

	if (!validator.isNotZeroInt(currentOrganID)) {
		res.send(new RetJson(i18n.SYS_ERROR_CODE, i18n.SYS_ERROR_MESSAGE, []));
	}

	function getCurrentOrganID(callback) {
		callback(undefined, currentOrganID);
	}

	function getCurrentOrganTree(currentOrgan, callback) {
		(new Organ()).getDisplayTree.call(currentOrgan, callback);
	}

	async.waterfall([
		getCurrentOrganID,
		adminDao.getUserCurrentOrgan,
		getCurrentOrganTree
	], function (err, treeData, childIDs) {
		if (err) {
			console.log(err);
			res.send(new RetJson(i18n.SYS_ERROR_CODE, i18n.SYS_ERROR_MESSAGE, []));
			return;
		}

		req.session.user.childIDs = childIDs;
		res.send(new RetJson(i18n.SYS_SUCCESS_CODE, i18n.SYS_SUCCESS_MESSAGE, [treeData]));
	});
};

exports.getUserList = function (req, res, next) {
	var organID = req.body.organID;
	if (!validator.isNotZeroInt(organID)) {
		res.send(new RetJson(i18n.SYS_ERROR_CODE, i18n.SYS_ERROR_MESSAGE, []));
	}

	function getCurrentOrganID(callback) {
		callback(undefined, organID);
	}

	function tranformUserData(userData, callback) {
		userData = _.map(userData, function (user) {
			user.createTime = moment(user.createTime).format('YYYY-MM-DD HH:mm:ss');
			return user;
		});
		callback(undefined, userData);
	}

	async.waterfall([
		getCurrentOrganID,
		adminDao.getUserList,
		tranformUserData
	], function (err, userData) {
		if (err) {
			console.log(err);
			res.send(new RetJson(i18n.SYS_ERROR_CODE, i18n.SYS_ERROR_MESSAGE, []));
			return;
		}

		res.send(new RetJson(i18n.SYS_SUCCESS_CODE, i18n.SYS_SUCCESS_MESSAGE, userData));
	});
};


exports.saveUser = function (req, res, next) {
	var model = req.body.jsonStrData;
	var userName = req.session.user.userName;

	if (!validator.isJSON(model)) {
		res.send(new RetJson(i18n.SYS_ERROR_CODE, i18n.SYS_ERROR_MESSAGE, null));
		return;
	}

	model = JSON.parse(model);

	function getUserInfoByUserID(callback) {
		adminDao.getUpdateUserByUserName(model.userName, function (err, userResult) {
			if (err) {
				callback(err);
				return;
			}

			if (userResult) {
				callback(new RetJson(i18n.SYS_ERROR_CODE, i18n.ADMIN_USER_EXIST_ERROR, null));
				return;
			}

			model.password = utility.md5(webConfig.userInitPassword);
			model.createUser = userName;
			callback(undefined, model);
		});
	}

	if (model.isNewRecord) {
		async.waterfall([
			getUserInfoByUserID,
			adminDao.insertUserInfo
		], function (err, insertResult) {
			if (err) {
				console.log(err);
				if (!err.hasOwnProperty('errcode')) {
					res.send(new RetJson(i18n.SYS_ERROR_CODE, i18n.SYS_ERROR_MESSAGE, null));
					return;
				}

				res.send(err);
				return;
			}

			var insertSuccess = insertResult ? insertResult.insertId : 0;

			model.userID = insertSuccess;
			res.send(insertSuccess === 0 ? new RetJson(i18n.SYS_ERROR_CODE, i18n.SYS_ERROR_MESSAGE, 0)
					: new RetJson(i18n.SYS_SUCCESS_CODE, i18n.SYS_SUCCESS_MESSAGE, insertSuccess));
		});
	} else {
		adminDao.updateUserInfo(model, function (err, updateResult) {
			if (err) {
				console.log(err);
				if (err.code == 'ER_DUP_ENTRY') {
					res.send(new RetJson(i18n.SYS_ERROR_CODE, i18n.ADMIN_USER_EXIST_ERROR, null));
				} else {
					res.send(new RetJson(i18n.SYS_ERROR_CODE, i18n.SYS_ERROR_MESSAGE, null));
				}
				return;
			}

			res.send(new RetJson(i18n.SYS_SUCCESS_CODE, i18n.ADMIN_USER_UPDATE_SUCCESS, null));
		});
	}
};

exports.resetPassword = function (req, res, next) {
	var userName = req.body.userName;

	if (!userName) {
		res.send(new RetJson(i18n.SYS_ERROR_CODE, i18n.SYS_ERROR_MESSAGE, null));
		return;
	}

	adminDao.resetPassword(userName, utility.md5(webConfig.userInitPassword), function (err, resetResult) {
		if (err) {
			console.log(err);
			res.send(new RetJson(i18n.SYS_ERROR_CODE, i18n.SYS_ERROR_MESSAGE, null));
			return;
		}

		var resetSuccess = resetResult ? resetResult.changedRows : 0;
		res.send(resetSuccess === 0 ? new RetJson(i18n.SYS_ERROR_CODE, i18n.ADMIN_USER_RESET_PASSWORD_ERROR, null)
				: new RetJson(i18n.SYS_SUCCESS_CODE, i18n.ADMIN_USER_RESET_PASSWORD_SUCCESS, null));
	});
};