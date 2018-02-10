/*****************************************************
 * Author  : wadecha
 * Version : 1.0
 * Date    :  2015/09/19
 ****************************************************/
function RetJson() {
	this.errcode = arguments[0] || 0;
	this.errmsg = arguments[1] || '';
	this.retobj = arguments[2] || '';
}
RetJson.invalidParameter = function () {
    return new RetJson(1001, 'Required Argument Missing');
};
module.exports = exports = RetJson;