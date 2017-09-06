'use strict';


function createErrorResponse(code, message) {
    var response = {
        'statusCode': code,
        'headers' : {'Access-Control-Allow-Origin' : '*'},
        'body' : JSON.stringify({'code': code, 'messsage' : message}, null, 2)
    };

    return response;
}

function createSuccessResponse(result) {
    var response = {
        'statusCode': 200,
        'headers' : {'Access-Control-Allow-Origin' : '*'},
        'body' : result
    };

    return response;
}

module.exports = {
    createErrorResponse: createErrorResponse,
    createSuccessResponse: createSuccessResponse
};