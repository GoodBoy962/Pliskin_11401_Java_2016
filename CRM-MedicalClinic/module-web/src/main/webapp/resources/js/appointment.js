$(function () {
    var arr = ['w', 'm', '2m'];
    var period = $("#period");
    if ($.inArray(period.find("option:selected").attr("id"), arr) > -1) {
        period.change(function () {
            getDates();
        }).change();
    }
});

function getDates() {
    var w_day = $("#weekDay").val();
    var time = $("#time").val();
    var doctorFio = $("#doctorFio").val();
    var period = $("#period").find("option:selected").attr("id");
    $.ajax({
        url: "dates",
        type: "GET",
        data: {
            w_day: w_day,
            time: time,
            doctorFio: doctorFio,
            period: period
        },
        dataType: "html",
        success: function (data) {
            $("#dates").html(data);
        }
    })
}

function doSendAble() {
    if ($("#dates").find("input[type=checkbox]:checked").length == 1) {
        $("#appointment-create").removeAttr('disabled');
    } else {
        $("#appointment-create").prop('disabled', true);
    }
}

$("#dates").change(function() {
    doSendAble()
});
