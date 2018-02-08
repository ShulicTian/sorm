/*****************************************************
 * Author  : wadecha
 * Version : 1.0
 * Date    :  2015/09/21
 ****************************************************/

var config = {
	// debug 为 true 时，用于本地调试
	debug: true,
	// 是否启用静态文件的合并压缩，详见视图中的Loader
	get mini_assets() {
		return !this.debug;
	},

	// 网站名
	site_name: 'timotech cms',
	// 网站标题
	site_title: '儿童手表后台',
	// 版本号
	version: '1.0.0',
	// 网站描述
	description: '',
	// 关键字
	keywords: 'timotech,cms',
	// 默认没有 favicon, 这里填写网址
	site_icon: '',
	// default is `name`
	site_logo: '',
	// 网站域名
	site_host: 'http://127.0.0.1:19030',
	// 静态文件存储域名
	site_static_host: 'http://127.0.0.1:19030/',
	// 程序运行的端口
	port: 19030,
	domain: '127.0.0.1',

// mysql 配置
	mysqlConfig: {
		host: '118.190.7.65',
		user: 'root',
		password: 'v5ma7b9o',
		database: 'db_watch_test',
		port: 3306
	},

	// mongodb 配置
	mongoConfig: {
		connstr: 'mongodb://titakid:Titakid_2017@118.190.7.65:27017/watch_test'
	},
	redis: {
		host: '118.190.7.65',
		port: 6379,
		pwd: 'Titakid_2017'
	},
	service: {
		host: '118.190.7.65',
		port: 10081
	},
	// session 务必修改
	session_secret: 'timotech_cms',
	// cookie
	auth_cookie_name: 'timotech_cms',

	initPageSize: 20,
	userInitPassword: '123456',


	// newrelic 是个用来监控网站性能的服务
	newrelic_key: 'timotech_cms',

	sys_def_param: {}

};

module.exports = config;
