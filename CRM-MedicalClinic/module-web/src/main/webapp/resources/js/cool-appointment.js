function getMedClinicsAndOffices() {
    var city = $("#city").val();
    $.ajax({
        url: "offices",
        type: "GET",
        data: {
            city: city
        },
        dataType: "html",
        success: function (data) {
            $("#medicalClinics").show();
            $("#offices").html(data)
        }
    })
}

function getDoctorSpecialities() {
    var city = $("#city").val();
    var address = $("#offices").find("option:selected").val()
    $.ajax({
        url: "specializations",
        type: "GET",
        data: {
            city: city,
            address: address
        },
        dataType: "html",
        success: function (data) {
            $("#specializations").show();
            $("#specs").html(data);
            $("#js-period").show();
        }
    })
}

$(function () {
    var offices = $("#offices");
    offices.change(function () {
        getDoctorSpecialities();
    }).change();
});

$(function () {
    var specializations = $("#specializations");
    specializations.change(function () {
        getDoctorsAndPossibleDates(specializations.find("option:selected").val());
    })
});

function getDoctorsAndPossibleDates(specialization) {
    var city = $("#city").val();
    var address = $("#offices").find("option:selected").val();
    var period = $("#js-period").find("option:selected").attr("id");
    $.ajax({
        url: "dates_doctors",
        type: "GET",
        data: {
            city: city,
            address: address,
            specialization: specialization,
            period: period
        },
        dataType: "html",
        success: function (data) {
            $("#doctors").html(data);
            $("#js-doctorDates").show();
        }
    })
}