'use strict';

var async = require('async');
var reader = require('./lib/getS3Object');
var parser = require('./lib/parser');

function createBucketParams(key) {
    return {
        Bucket: process.env.BUCKET,
        Key: key,
        EncodingType: 'url'
    };
}

exports.handler = function (event, context, callback) {
    async.waterfall([reader.read(createBucketParams(event.env.BUCKET_KEY))
                    , parser.parseBy()
    ])

};