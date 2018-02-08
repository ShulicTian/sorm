/*****************************************************
 * Author  : wadecha
 * Version : 1.0
 * Date    :  2015/09/18
 ****************************************************/


var validator = require('validator');

validator.extend('isShortDate', function (str) {
	return /^((((1[6-9]|[2-9]\d)\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})-0?2-(0?[1-9]|1\d|2[0-9]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$/.test(str);
});

validator.extend('isDateTime', function (str) {
	return /^((((1[6-9]|[2-9]\d)\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\d):[0-5]?\d:[0-5]?\d$/.test(str);
});

validator.extend('isNotZeroInt', function (str) {
	return /^[0-9]*[1-9][0-9]*$/.test(str);
});

validator.extend('isChineseName', function (str) {
	return /^[\u4e00-\u9fa5_a-zA-Z0-9]+$/.test(str);
});

validator.extend('isIMEI', function (str) {
	return /^\d{15,}$/.test(str);
});

validator.extend('isInt11', function (str) {
	return /^\d{11}$/.test(str);
});

validator.extend("isNull",function(str){
	if(str==null || str==""){
		return false;
	}
	return true;
});

validator.extend('isInt', function (str) {
    return /^\d{1,}$/.test(str);
});

module.exports = exports = validator;

