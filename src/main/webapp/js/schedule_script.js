$(document).ready(function () {
    if ($("#numberWeek").val() == '' ||
        $("#numberWeek").val() == 0) {
        $('#previousWeek').hide();
    } else {
        $('#previousWeek').show();
    }

    if ($("#nextSchedule").val() == "false") {
        $('#nextWeek').hide();
    } else {
        $('#nextWeek').show();
    }
    if ($("#showModalDeleteSchedule").val() == 'true') {
        $("#modalDeleteSchedule").modal('show');
    } else {
        $("#modalDeleteSchedule").modal('hide');
    }
    $('#modalDeleteSchedule').on('hidden.bs.modal', function () {
        $("#showModalDeleteSchedule").val('');
    });
});

$("#previousWeek").click(function () {
    if (Number($("#numberWeek").val()) > 0) {
        $("#numberWeek").val(Number($("#numberWeek").val()) - 1);
    }
});

$("#nextWeek").click(function () {
    if ($("#numberWeek").val() == '') {
        $("#numberWeek").val(1);
    } else {
        $("#numberWeek").val(Number($("#numberWeek").val()) + 1);
    }
});