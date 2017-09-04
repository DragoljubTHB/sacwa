'use strict';

var AWS = require('aws-sdk');
var s3 = AWS.S3();

function read(params) {
    s3.getObject(params, function (err, data) {
        if(err){
            console.log(err, err.stack);
            return (err);
        }else {
            return (data.Body.toString('utf8'));
        }
    });
}

module.exports = {
    read: read
};