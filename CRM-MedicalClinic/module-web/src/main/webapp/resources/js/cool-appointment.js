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
            $("#specs").html(data)
        }
    })
}

$(function () {
    var offices = $("#offices");
    offices.change(function () {
        getDoctorSpecialities();
    }).change();
});
