'use strict';
var N3 = require('n3');

function searchBy(individual, data) {
    var store = N3.Store();
    var triple = store.getTriples(individual, null, null, null)[0];
    console.log(triple.subject + " : " + triple.property + " : " + triple.object)
}

function parseBy(individual, data) {
    var parser = new N3.Parser();
    console.log("about to parse ");
    parser.parse(data, function (error, triple, prefixes) {
        if(triple){
            if(triple.subject === individual.toString()){
                console.log(triple.subject + " : " + triple.property + " : " + triple.object)
            }
        }
    });
    console.log(found);
    return JSON.stringify(found);
};
module.exports = {
    parseBy: parseBy,
    searchBy: searchBy
};
