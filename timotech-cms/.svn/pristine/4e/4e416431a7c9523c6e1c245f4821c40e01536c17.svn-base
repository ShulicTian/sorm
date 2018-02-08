/**
 * Created by Administrator on 14-8-30.
 */
var geography = require("../build/Release/geography.node");

//china rect (72, 3, 136, 67)
module.exports = exports = function (rect) {
	this.rect = rect || {minX: -180, minY: -180, maxX: 180, maxY: 180};
};

exports.encryptBlockID = function (x, y, level) {
	return geography.leftShift(geography.leftShift(x, 28) + y, 8) + level;
};

exports.decodeBlockID = function (blockID) {
	var result = {};
	result.level = geography.and(blockID, 0xFF);
	blockID = geography.rightShift(blockID, 8);
	result.y = geography.and(blockID, 0xFFFFFFF);
	blockID = geography.rightShift(blockID, 28);
	result.x = blockID;
	return result;
};

exports.getParentID = function (blockID) {
	var result = exports.decodeBlockID(blockID);
	var x = (result.x >> 1);
	var y = (result.y >> 1);
	var level = result.level - 1;
	return exports.encryptBlockID(x, y, level);
};

exports.getLevel = function (blockID) {
	return geography.and(blockID, 0xFF);
};

exports.getChildBlock = function (blockID) {
	var result = exports.decodeBlockID(blockID);
	var x = result.x * 2;
	var y = result.y * 2;
	var level = result.level + 1;
	var childBlocks = {};
	childBlocks.leftTop = exports.encryptBlockID(x, y + 1, level);
	childBlocks.rightTop = exports.encryptBlockID(x + 1, y + 1, level);
	childBlocks.leftBottom = exports.encryptBlockID(x, y, level);
	childBlocks.rightBottom = exports.encryptBlockID(x + 1, y, level);
	return childBlocks;
};

exports.prototype.getBlockSize = function (level) {
	var result = {};
	result.sizeX = (this.rect.maxX - this.rect.minX) / (1 << level);
	result.sizeY = (this.rect.maxY - this.rect.minY) / (1 << level);
	return result;
};

exports.prototype.getInBlockID = function (lon, lat, level) {
	if (x < this.rect.minX || y < this.rect.minY || x > this.rect.maxX || y > this.rect.maxY) {
		return 0;
	}
	var size = this.getBlockSize(level);
	var x = Math.floor((lon - this.rect.minX) / size.sizeX);
	var y = Math.floor((lat - this.rect.minY) / size.sizeY);
	return exports.encryptBlockID(x, y, level);
};

exports.prototype.getBlockRect = function (blockID) {
	var result = exports.decodeBlockID(blockID);
	var size = this.getBlockSize(result.level);
	var rect = {};
	rect.minX = result.x * size.sizeX + this.rect.minX;
	rect.minY = result.y * size.sizeY + this.rect.minY;
	rect.maxX = rect.minX + size.sizeX;
	rect.maxY = rect.minY + size.sizeY;
	return rect;
};

exports.prototype.getLeftBottomVector = function (blockID) {
	var rect = this.getBlockRect(blockID);
	return {lon: rect.minX, lat: rect.minY};
};

exports.prototype.getCrossedBlock = function (rect, level) {
	var size = this.getBlockSize(level);
	var ltBlockID = this.getInBlockID(rect.minX, rect.maxY, level);
	var rbBlockID = this.getInBlockID(rect.maxX, rect.minY, level);
	var ltRect = this.getBlockRect(ltBlockID);
	var rbRect = this.getBlockRect(rbBlockID);
	var minX = ltRect.minX;
	var maxX = rbRect.maxX > this.rect.maxX ? this.rect.maxX : rbRect.maxX;
	var minY = rbRect.minY;
	var maxY = ltRect.maxY > this.rect.maxY ? this.rect.maxY : ltRect.maxY;
	var blockIDs = [];
	for (var x = minX; x < maxX; x += size.sizeX) {
		for (var y = minY; y < maxY; y += size.sizeY) {
			var blockID = this.getInBlockID(x, y, level);
			blockIDs.push(blockID);
		}
	}
	return blockIDs;
};