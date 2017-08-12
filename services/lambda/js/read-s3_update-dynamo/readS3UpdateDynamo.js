'use strict';

const AWS = require('aws-sdk');

var async = require('async');
var N3 = require('n3');
var s3 = new AWS.S3();
var dynamodb = new AWS.DynamoDB.DocumentClient({region: 'us-west-2'});


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

function lastWordOf(aTriple) {
    var trip = aTriple.split('#');
    if(trip.length > 1) {
        return ":"+trip[1]
    }else {
        return trip[0]
    }
}
function addItemToDynamoPutRequest(keyName, keyValue) {
    var item = {
        Item:{
        }
    };
    var putReq = {
        PutRequest:{

        }
    };

    item.Item[keyName] = keyValue;
    putReq.PutRequest = item;

    console.log(JSON.stringify(item));

    return putReq;

}

function createBucketParams(next) {
    var params = {
        Bucket: process.env.BUCKET,
        Key: 'raw/picasso-turtle.owl'
    };
    next(null, params);
}

function getObjectFromS3(params, next) {
    console.log("attempt to read object from S3");
    s3.getObject(params, function (err, data) {
        if(err){
            console.log(err, err.stack);
            next(err);
        }else {
            next(null, data);
        }
    });
}

function parseOntology(data, next) {
    console.log("attempt to parse ontology");

    // passed in request param
    // located at s3 under milasino account because of aws:role -> policy
    var file = 'raw/picasso-turtle.owl';
    var reqItems = { RequestItems : { }};
    var subjectItems = [];
    var predicateItems = [];
    var objectItems = [];


    var first = true;
    var parser = new N3.Parser();
    parser.parse(data, function (error, triple, prefixes) {
        if(!first && triple){
            subjectItems.push(addItemToDynamoPutRequest(lastWordOf(triple.subject), file));
            predicateItems.push(addItemToDynamoPutRequest(lastWordOf(triple.predicate), file));
            objectItems.push(addItemToDynamoPutRequest(lastWordOf(triple.object), file));

            reqItems.RequestItems['Subject'] = subjectItems;
            reqItems.RequestItems['Predicate'] = predicateItems;
            reqItems.RequestItems['Object'] = objectItems;
            console.log(JSON.stringify(reqItems));

        }
        first = false;
    });
    next(null, reqItems);
}

function writeToDynamoDB(params, next) {
    console.log("attempt to write to dynamoDB");

    dynamodb.batchWriteItem(params, function(err, data) {
        if (err) next(err);
        else next(null, data);
    });
}

exports.handler = function (event, context, callback) {
    // 1. read s3(bucket param) files(ontology folder)
    // 2. write to S, P, O tables k->":word" v->file url
    async.waterfall([createBucketParams
                    , getObjectFromS3
                    , parseOntology
                    , writeToDynamoDB],
        function (err, result) {
            if(err) callback(createErrorResponse(500, err));
            else callback(null, createSuccessResponse(200, result));
        });
};
