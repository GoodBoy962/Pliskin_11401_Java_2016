$(function () {
    var period = $("#period");
    if (period.find("option:selected").attr("id") in ['w', 'm', 'wm']) {
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
