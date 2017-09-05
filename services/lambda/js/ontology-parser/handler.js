'use strict';

var res = require('./lib/stdResponse');
var async = require('async');
var reader = require('./lib/getS3Object');
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
    var body = reader.read(bucket);
    next(null, body);
}
function parseOntology(data, next) {
    parser.parseBy(reqIndividual, data);
    next(null, data)
}

exports.handler = function (event, context, callback)  {
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