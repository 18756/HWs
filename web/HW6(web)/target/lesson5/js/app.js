window.notify = function(message) {
    $.notify(message, {position: "bottom right"})
};



window.ajax = function(data, successFunction, url="", type="POST", dataType="json") {
    $.ajax({
        type: type,
        url: url,
        dataType: dataType,
        data: data,
        success: function (response) {
            if (response["redirect"]) {
                location.href = response["redirect"];
            }
            successFunction(response);
        }
    });
};

