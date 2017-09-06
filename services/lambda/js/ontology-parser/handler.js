'use strict';

var AWS = require('aws-sdk');
var s3 = new AWS.S3();
var parser = require('./lib/parser');
var res = require('./lib/stdResponse');
var async = require('async');

const baseUrl = 'https://s3-us-west-2.amazonaws.com';
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
    var iri = baseUrl + '/' + process.env.BUCKET + '/' + process.env.SOURCE_KEY + '#' + reqIndividual;
    console.log(iri);
    if(typeof reqIndividual !== undefined || reqIndividual === '') {
        parser.parse(reqIndividual, data, next);
    } else {
        console.log("get All ----------- ");
        parser.getAllSPO(data, next);
    }
    //parser.searchBy(reqIndividual, data);
}

exports.handler = function (event, context, callback) {
    reqIndividual = event.individual; // check possible exception
    async.waterfall([createBucketParams
            , getS3ObjectBody
            , parseOntology
        ],
        function (err, result) {
            if (err) {
                callback(res.createErrorResponse(500, err));
            } else {
                if (Object.keys(result).length === 0 && result.constructor === Object) {
                    callback(null, res.createErrorResponse(404, "there was no result on the search"));
                } else {
                    callback(null, res.createSuccessResponse(result));
                }
            }
        }
    );
};