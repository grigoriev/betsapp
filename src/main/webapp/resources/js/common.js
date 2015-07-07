function syncPost(actionUrl, requestParam, callback) {
    $.ajax({
        contentType: 'application/json',
        type: 'post',
        url: actionUrl,
        data: JSON.stringify(requestParam),
        dataType: 'json',
        success: callback,
        async: false
    });
}

function syncGet(actionUrl, callback, requestParam) {
    $.ajax({
        contentType: 'application/json',
        type: 'get',
        url: actionUrl,
        data: JSON.stringify(requestParam),
        dataType: 'json',
        success: callback,
        async: false
    });
}

function asyncGet(actionUrl, callback, requestParam) {
    $.ajax({
        contentType: 'application/json',
        type: 'get',
        url: actionUrl,
        data: JSON.stringify(requestParam),
        dataType: 'json',
        success: callback,
        async: true
    });
}

$(function () {
    $(document).ajaxError(function myErrorHandler(event, jqXHR, ajaxOptions, thrownError) {
        if (jqXHR.status !== 0) {
            var errorMessage = jqXHR.status + ' ' + jqXHR.statusText;
            alert(errorMessage);
        }
    });
});
