'use strict';

var AWS = require('aws-sdk');
var async = require('async');

var s3 = new AWS.S3();


function createErrorResponse(code, message) {
    var response = {
        'statusCode': code,
        'headers' : {'Access-Control-Allow-Origin' : '*'},
        'body' : JSON.stringify({'code': code, 'messsage' : message})
    };

    return response;
}

function createSuccessResponse(result) {
    var response = {
        'statusCode': 200,
        'headers' : {'Access-Control-Allow-Origin' : '*'},
        'body' : JSON.stringify(result)
    };

    return response;
}

function createBucketParams(next) {
    var params = {
        Bucket: process.env.BUCKET,
        EncodingType: 'url'
    };
    next(null, params);
}

function getFileListFromS3(params, next) {
    s3.listObjects(params, function (err, data) {
        if(err){
            next(err);
        }else {
            next(null, data)
        }
    });
}

function createList(data, next) {
    var urls = [];
    for(var i = 0; i < data.Contents.length; i++){
        var file = data.Contents[i];
        urls.push(file);
    }
    var result = {
        baseUrl: process.env.BASE_URL,
        bucket: process.env.BUCKET,
        urls: urls
    };
    next(null, result);
}

exports.handler = function (event, context, callback) {
    async.waterfall([createBucketParams, getFileListFromS3, createList],
        function (err, result) {
            if(err){
                callback(createErrorResponse(500, err));
            }else {

                if(result.urls.length > 0){
                    console.log(result);
                    callback(null, createSuccessResponse(result));
                }else {
                    callback(createErrorResponse(404, "No files were found"));
                }
            }
        });
};