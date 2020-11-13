$(document).ready(function () {
    if ($("#showModalAddSchedule").val() == 'true') {
        $("#modalAddSchedule").modal('show');
    } else {
        $("#modalAddSchedule").modal('hide');
    }

    $('#modalAddSchedule').on('hidden.bs.modal', function () {
        $("#showModalAddSchedule").val('');
    });
});