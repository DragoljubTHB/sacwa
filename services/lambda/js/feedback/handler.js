'use strict';
var util = require('util');

exports.handler = function (event, context, callback) {
    var opts = util.inspect(event, {depth: 5});
    console.log(opts);
    console.log("got event.name: "+event.name);

    callback(null, event.name);
};
