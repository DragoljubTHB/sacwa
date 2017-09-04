'use strict';

var async = require('async');
var reader = require('./lib/getS3Object');
var parser = require('./lib/parser');

function createBucketParams(params, next) {
    var bucket = {
        Bucket: process.env.BUCKET,
        Key: params('sourceKey'),
        EncodingType: 'url'
    };
    next(null, bucket, params);
}
function getS3ObjectBody(bucket, params, next) {
    var body = reader.read(bucket);
    next(null, body, params);
}
function parseOntology(data, params, next) {
    parser.parseBy(params('individual'), data);
    next(null, data)
}

exports.handler = function (event, context, callback) {
    console.log(event.params('sourceKey'));
    console.log(event.params('individual'));

    async.waterfall([createBucketParams(event.params)
            , getS3Object
            , parseOntology
        ],
        function (err, result) {
            if (err) {
                callback(err);
            } else {
                callback(null, result);
            }
        }
    );
};