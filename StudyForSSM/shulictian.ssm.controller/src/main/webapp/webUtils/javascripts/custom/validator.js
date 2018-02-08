
function isMobilePhone(str){
    var re = /^(\+?0?86\-?)?1[345789]\d{9}$/;
    if(re.test(str)){
        return true;
    }
    return false;
}

function isLength(str, min, max) {
    var surrogatePairs = str.match(/[\uD800-\uDBFF][\uDC00-\uDFFF]/g) || [];
    var len = str.length - surrogatePairs.length;
    return len >= min && (typeof max === 'undefined' || len <= max);
}
