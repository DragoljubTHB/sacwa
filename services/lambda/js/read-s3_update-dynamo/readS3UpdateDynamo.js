'use strict';

const AWS = require('aws-sdk');

exports.handler = function (event, context, callback) {
    // 1. read s3(bucket param) files(ontology folder)
    // 2. write to S, P, O tables k->":word" v->file url
};
