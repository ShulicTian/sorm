/**
 * Author:ShulicTian
 * Date:2017/12/30
 */

var mysqlDao = require('../common/mysql_pool');

exports.getAudios = function (name, callback) {
	var sql = "select resourceId,url,version,productId from t_resource " +
			"where resourceName=:name order by productId,resourceId";
	mysqlDao.executeList(sql, {"name": name}, callback);
};

exports.getDials = function (name, callback) {
	var sql = "select resourceId,url,version,previewUrl,tag,size,productId " +
			"from t_resource " +
			"where resourceName=:name order by productId,resourceId";
	mysqlDao.executeList(sql, {"name": name}, callback);
};

exports.getEmoticons = function (name, callback) {
	var sql = "select resourceId,url,version,tag,productId " +
			"from t_resource " +
			"where resourceName=:name order by productId,resourceId";
	mysqlDao.executeList(sql, {"name": name}, callback);
};

exports.getThemes = function (name, callback) {
	var sql = "select resourceId,url,version,previewUrl,tag,size,productId " +
			"from t_resource " +
			"where resourceName=:name order by productId,resourceId";
	mysqlDao.executeList(sql, {"name": name}, callback);
};

/**
 *  update
 */
exports.updateAudio = function (resourceId, url, version, productId, callback) {
	var sql = "update t_resource " +
			"set url=:url,version=:version " +
			"where productId=:productId and resourceName='audio' and resourceId=:resourceId";
	mysqlDao.executeUpdate(sql, {
		"resourceId": resourceId,
		"url": url,
		"version": version,
		"productId": productId
	}, callback);
};

exports.updateEmoticon = function (resourceId, url, version, tag, productId, callback) {
	var sql = "update t_resource " +
			"set url=:url,version=:version,tag=:tag " +
			"where productId=:productId and resourceName='emoticon' and resourceId=:resourceId";
	mysqlDao.executeUpdate(sql, {
		"resourceId": resourceId,
		"url": url,
		"version": version,
		"tag": tag,
		"productId": productId
	}, callback);
};

exports.updateDialAndTheme = function (resourceId, url, version, name, previewUrl, tag, size, productId, callback) {
	var sql = "update t_resource " +
			"set url=:url,version=:version,previewUrl=:previewUrl,tag=:tag,size=:size " +
			"where productId=:productId and resourceName=:name and resourceId=:resourceId";
	mysqlDao.executeUpdate(sql, {
		"resourceId": resourceId,
		"url": url,
		"version": version,
		"name": name,
		"previewUrl": previewUrl,
		"tag": tag,
		"size": size,
		"productId": productId
	}, callback);
};

/**
 * save
 */
exports.saveAudio = function (resourceId, url, version, createTime, productId, callback) {
	var sql = "insert into t_resource(resourceName,resourceId,url,version,createTime,productId) " +
			"values('audio',:resourceId,:url,:version,:createTime,:productId)";
	mysqlDao.executeUpdate(sql, {
		"resourceId": resourceId,
		"url": url,
		"version": version,
		"createTime": createTime,
		"productId": productId
	}, callback);
};

exports.saveEmoticon = function (resourceId, url, version, tag, createTime, productId, callback) {
	var sql = "insert into t_resource(resourceName,resourceId,url,version,tag,createTime,productId) " +
			"values('emoticon',:resourceId,:url,:version,:tag,:createTime,:productId)";
	mysqlDao.executeUpdate(sql, {
		"resourceId": resourceId,
		"url": url,
		"version": version,
		"tag": tag,
		"createTime": createTime,
		"productId": productId
	}, callback);
};

exports.saveDialAndTheme = function (resourceId, url, version, name, previewUrl, tag, size, createTime, productId, callback) {
	var sql = "insert into t_resource(resourceName,resourceId,url,version,previewUrl,tag,size,createTime,productId) " +
			"values(:name,:resourceId,:url,:version,:previewUrl,:tag,:size,:createTime,:productId)";
	mysqlDao.executeUpdate(sql, {
		"resourceId": resourceId,
		"url": url,
		"version": version,
		"name": name,
		"previewUrl": previewUrl,
		"tag": tag,
		"size": size,
		"createTime": createTime,
		"productId": productId
	}, callback);
};

/**
 * delete
 */
exports.deleteAudio = function (productId, resourceId, callback) {
	var sql = "delete from t_resource " +
			"where productId=:productId and resourceName='audio' and resourceId=:resourceId";
	mysqlDao.executeUpdate(sql, {"productId": productId, "resourceId": resourceId}, callback);
};

exports.deleteEmoticon = function (productId, resourceId, callback) {
	var sql = "delete from t_resource " +
			"where productId=:productId and resourceName='emoticon' and resourceId=:resourceId";
	mysqlDao.executeUpdate(sql, {"productId": productId, "resourceId": resourceId}, callback);
};

exports.deleteDialAndTheme = function (productId, resourceId, name, callback) {
	var sql = "delete from t_resource " +
			"where productId=:productId and resourceName=:name and resourceId=:resourceId";
	mysqlDao.executeUpdate(sql, {
		"productId": productId,
		"resourceId": resourceId,
		"name": name
	}, callback);
};