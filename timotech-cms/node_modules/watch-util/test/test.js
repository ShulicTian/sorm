/**
 * Created by Administrator on 2015/8/20.
 */
var util = require("../index.js");

/*console.log(util.geography.distance(113.82496, 22.73944, 113.82596, 22.73944));

 var buf = new Buffer(8);
 buf.writeInt64LE(20151230114700, 0);
 console.log(buf.readInt64LE(0));

 buf.writeInt64BE(20151230114700, 0);
 console.log(buf.readInt64BE(0));*/

console.log(util.geography.convertBaiduToGps(113.93502, 22.531307));
/*
 var idGen=new util.IdGenerator(1);
 for(i=0;i<10;i++){
 console.log(idGen.nextID());
 }*/
