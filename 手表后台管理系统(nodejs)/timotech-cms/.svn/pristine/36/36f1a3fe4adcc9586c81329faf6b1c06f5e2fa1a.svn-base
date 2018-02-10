/*****************************************************
 * Author  : Administrator
 * Version : 1.0
 * Date    :  2014/12/7
 ****************************************************/

var mysql = require('mysql');
var async = require('async');

function SqlExecutor(mysqlConfig) {
	this.pool = mysql.createPool({
		host: mysqlConfig.host,
		port: mysqlConfig.port,
		user: mysqlConfig.user,
		password: mysqlConfig.password,
		database: mysqlConfig.database,
		connectionLimit: mysqlConfig.connectionLimit,
		multipleStatements: true
	});
}

module.exports = exports = SqlExecutor;

exports.prototype.execute = function (/*sql, params, callback*/) {
	var _this = this;
	var _args = arguments;
	_this.pool.getConnection(function (err, connection) {
		var args = Array.prototype.slice.call(_args, 0);
		var callback = args[args.length - 1];
		if (typeof(callback) !== "function") {
			callback = function () {

			};
			args.push(callback);
		}

		if (err) {
			return callback(err);
		}

		args[args.length - 1] = function (err2, results) {
			_this.pool.releaseConnection(connection);
			callback(err2, results);
		};
		connection.query.apply(connection, args);
	});
};

exports.prototype.executeByTran = function (sqlTasks, callback) {
	var _this = this;
	_this.pool.getConnection(function (err, connection) {
		if (err) {
			return callback(err);
		}

		connection.beginTransaction(function (err) {
			if (err) {
				_this.pool.releaseConnection(connection);
				return callback(err);
			}

			async.eachSeries(sqlTasks, function (sqlTask, callback2) {
				connection.query(sqlTask.sql, sqlTask.paras, callback2);
			}, function (err) {
				if (err) {
					connection.rollback(function (err2) {
						_this.pool.releaseConnection(connection);
						return callback(err);
					});
				} else {
					connection.commit(function (err) {
						_this.pool.releaseConnection(connection);
						callback(err);
					});
				}
			});
		});
	});
};

exports.prototype.getConnection = function (callback) {
	this.pool.getConnection(callback);
};

exports.prototype.releaseConnection = function (connection) {
	this.pool.releaseConnection(connection);
};
