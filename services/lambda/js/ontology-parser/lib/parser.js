'use strict';
var N3 = require('n3');


function parseBy(individual, data) {
    var parser = new N3.Parser();
    var found;
    parser.parse(data, function (error, triple, prefixes) {
        if(triple){
            found = N3.Util.getTriples(individual, null, null, null);
        }
    });
    console.log(found);
    return JSON.stringify(found);
};
module.exports = {
    parseBy: parseBy
};
