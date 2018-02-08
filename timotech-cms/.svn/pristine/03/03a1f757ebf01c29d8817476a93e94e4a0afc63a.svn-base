var mysqlDao = require('../common/mysql_pool');

exports.getUserByUserName = function (userName, callback) {
	var sql = "select id as userID, username as userName, password, organID" +
			" from t_sys_user " +
			" where status = 1 and username=:userName ";
	mysqlDao.executeObject(sql, {userName: userName}, callback);
};

exports.getUserByUserID = function (userID, callback) {
	var sql = "select id as userID, username as userName, password, organID, roleID " +
			" from t_sys_user " +
			" where status = 1 and id=:userID ";
	mysqlDao.executeObject(sql, {userID: userID}, callback);
};

exports.getUserUpdatePasswordByUserID = function (userID, callback) {
	var sql = "select id as userID, username as userName, password " +
			" from t_sys_user " +
			" where id=:userID ";
	mysqlDao.executeObject(sql, {userID: userID}, callback);
};

exports.updateUserPassword = function (userID, newPassword, callback) {
	var sql = "update t_sys_user set password=:newPassword where id=:userID ";
	mysqlDao.executeUpdate(sql, {userID: userID, newPassword: newPassword}, callback);
};

exports.getUserCurrentOrgan = function (organID, callback) {
	var sql = "select id, organName as text, id as organID, organName, parentID, path, status from t_organ where id=:organID";
	mysqlDao.executeObject(sql, {organID: organID}, callback);
};

exports.getAllOrganList = function (callback) {
	var sql = "select id, organName as text, id as organID, organName, parentID, path, status from t_organ";
	mysqlDao.executeList(sql, {}, callback);
};

exports.insertOrganInfo = function (organ, callback) {
	var sqlTasks = [];

	var sql_insert = "insert into t_organ(organname, path, parentid, status) values(:organName, :path, :_parentId, :status)";
	var sql_update = "update t_organ set path = concat(path, id, ',') where id = last_insert_id()";

	sqlTasks.push({
		"sql": sql_insert,
		"paras": organ
	});

	sqlTasks.push({
		"sql": sql_update
	});

	mysqlDao.executeByTran(sqlTasks, callback);
};

exports.updateOrganInfo = function (organ, callback) {
	var sqlTasks = [];

	var sql_update = "update t_organ c join t_organ p on (c.parentid = p.id and p.status = 1 ) or p.parentid = 0  " +
			"set c.organname = :organName, c.status = :status " +
			"where c.id = :organID";
	sqlTasks.push({
		"sql": sql_update,
		"paras": organ
	});

	if (organ.status == 0) {
		var sql_disable = "update t_organ set status = 0 where path like concat(:path, '%')";
		sqlTasks.push({
			"sql": sql_disable,
			"paras": {path: organ.path}
		});
	}

	mysqlDao.executeByTran(sqlTasks, callback);
};

exports.getUserList = function (organID, callback) {
	var sql = "select u.id as userID, u.userName, u.email, u.phone, u.realName, u.status, u.roleID, u.createTime, u.organID, o.organName " +
			"from t_sys_user u " +
			"left join t_organ o on u.organid = o.id " +
			"where u.organid = :organID ";
	mysqlDao.executeList(sql, {organID: organID}, callback);
};

exports.getUpdateUserByUserName = function (userName, callback) {
	var sql = "select id as userID, username as userName" +
			" from t_sys_user " +
			" where username=:userName ";
	mysqlDao.executeObject(sql, {userName: userName}, callback);
};


exports.insertUserInfo = function (user, callback) {
	var sql = "insert into t_sys_user(userName, password, email, phone, realName, organID, roleID, status, createTime, createUser) " +
			"values(:userName, :password, :email, :phone, :realName, :organID, :roleID, :status, :createTime, :createUser)";

	mysqlDao.executeUpdate(sql, user, callback);
};

exports.updateUserInfo = function (user, callback) {
	var sql = "update t_sys_user set userName=:userName,email=:email,phone=:phone,realname=:realName,roleid=:roleID,status=:status where id=:userID";
	mysqlDao.executeUpdate(sql, user, callback);
};

exports.resetPassword = function (userName, password, callback) {
	var sql = "update t_sys_user set password=:password where username=:userName";
	mysqlDao.executeUpdate(sql, {userName: userName, password: password}, callback);
};