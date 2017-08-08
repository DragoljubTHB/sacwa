
$('#local-info').click(function (e) {
    $.getJSON("tests/result.json", function (data) {
        alert(JSON.stringify(data, null, 2));

    });
});

/*
$.each(data, function (index, value) {
            console.log(value);
        })
 */