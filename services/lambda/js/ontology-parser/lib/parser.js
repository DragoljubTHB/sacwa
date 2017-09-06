'use strict';
var N3 = require('n3');
var async = require('async');

function searchBy(individual, data) {
    var store = N3.Store();
    var triple = store.getTriples(individual, null, null, null)[0];
    if (triple) {
        console.log(triple.subject + " : " + triple.property + " : " + triple.object)
    } else {
        console.log("searchBy found notin'")
    }
}

function hasSubstring(string, substring) {
    if (string !== undefined) {
        return string.indexOf(substring) !== -1;
    } else {
        return false;
    }
}

function parseBy(individual, data, next) {
    var parser = N3.Parser();
    var store = N3.Store();
    parser.parse(data, function (error, triple, prefixes) {
        if (triple) {
            if (hasSubstring(triple.subject, individual) ||
                hasSubstring(triple.property, individual) ||
                hasSubstring(triple.object, individual)) {
                store.addTriple(triple);
            }
        } else {
            next(null, store.getTriples());
        }
    });
};

function parse(individual, data, callback) {
    async.waterfall([async.apply(parseBy, individual, data)],
        function (err, result) {
            callback(null, result)
        });
}

module.exports = {
    parse: parse,
    searchBy: searchBy
};
