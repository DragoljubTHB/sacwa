'use strict';

var AWS = require('aws-sdk');
var s3 = new AWS.S3();
var res = require('./lib/stdResponse');
var async = require('async');
var parser = require('./lib/parser');

var reqIndividual;

function createBucketParams(next) {
    var bucket = {
        Bucket: process.env.BUCKET,
        Key: process.env.SOURCE_KEY
    };
    next(null, bucket);
}

function getS3ObjectBody(bucket, next) {
    console.log("attempt to read object from S3");
    s3.getObject(bucket, function (err, data) {
        if (err) {
            console.log(err, err.stack);
            next(err);
        } else {
            next(null, data.Body.toString('utf8'));
        }
    });
}

function parseOntology(data, next) {
    parser.parseBy(reqIndividual, data);
    parser.searchBy(reqIndividual, data);
    next(null, data)
}

exports.handler = function (event, context, callback) {
    reqIndividual = event.individual;
    async.waterfall([createBucketParams
            , getS3ObjectBody
            , parseOntology
        ],
        function (err, result) {
            if (err) {
                callback(res.createErrorResponse(500, err));
            } else {
                callback(null, res.createSuccessResponse(200, result));
            }
        }
    );
};