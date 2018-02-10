/*****************************************************
 * Author  : wadecha
 * Version : 1.0
 * Date    :  2015/09/26
 ****************************************************/
var _ = require('lodash');
var async = require('async');
var adminDao = require('../lib/dao/admin_dao');

function Organ(){
    this.organID = arguments[0] || 0;
    this.organName = arguments[1] || '';
    this.parentID = arguments[2] || 0;
    this.status = arguments[3] || 1;


    this.children = [];
}

module.exports = exports = Organ;

exports.prototype.getFullTree = function(callback) {
    var self = this;

    function getAllOrganList(callback){
        adminDao.getAllOrganList(function(err, result){
            callback(err, result, self, false);
        });
    }

    async.waterfall([
        getAllOrganList,
        buildTreeData
    ], callback);
};

exports.prototype.getDisplayTree = function(callback) {
    var self = this;

    function getAllOrganList(callback){
        adminDao.getAllOrganList(function(err, result){
            callback(err, result, self, true);
        });
    }

    async.waterfall([
        getAllOrganList,
        buildTreeData
    ], callback);
};

function buildTreeData(organs, self, display, callback) {
    var childList = _.chain(organs)
        .filter(function (organ) {
            return _.startsWith(organ.path, self.path) && organ.organID !== self.organID && (display ? organ.status === 1 : true);
        }).map(function (organ) {
            return organ;
        }).value();

    var childIDs = _.pluck(childList, 'organID');
    childIDs.unshift(self.organID);

    var groupList = _.groupBy(childList, function (organ) {
        return organ.parentID;
    });

    _.mapKeys(groupList, function (value, key) {
        var parent = self;
        if (key != self.organID) {
            parent = _.find(childList, function (child) {
                return child.organID == key;
            });
        }
        parent.children = value;
    });

    callback(undefined, self, childIDs);
}


//exports.prototype.getDisplayChildIDs = function(callback) {
//    var self = this;
//
//    function getAllOrganList(callback){
//        adminDao.getAllOrganList(function(err, result){
//            callback(err, result, self, false);
//        });
//    }
//
//    async.waterfall([
//        getAllOrganList,
//        buildChildIDs
//    ], callback);
//};
//
//function buildChildIDs(organs, self, display, callback) {
//    var childIDs = _.chain(organs)
//        .filter(function (organ) {
//            return _.startsWith(organ.path, self.path) && organ.organID !== self.organID && (display ? true : organ.status === 1);
//        }).map(function (organ) {
//            return organ;
//        }).pluck('organID').value();
//
//    childIDs.unshift(self.organID);
//    callback(undefined, childIDs);
//}