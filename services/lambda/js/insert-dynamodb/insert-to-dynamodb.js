'use strict';

const AWS = require('aws-sdk');
const dynamo = new AWS.DynamoDB.DocumentClient({region: 'us-west-2'});


exports.handler = function (event, context, callback) {

    var params = {
        Item: {
            date: Date.now(),
            message: "message: hello!"
        },
        TableName: 'testtable'
    };

    dynamo.put(params, function (err, data) {
        if(err){
            callback(err, null);
        } else {
            callback(null, data);
        }
    });
};

