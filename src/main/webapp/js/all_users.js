function searchClick() {
    if ($('#surnameSearch').val().length === 0 || $('#surnameSearch').val().length === '0') {
        $("#commandName").val('all_user_page');
    } else {
        $("#commandName").val('search_user');
    }
};

function editClick(id) {
    $("#commandName").val('change_user_role');
    $('#role' + id).prop('disabled', false);
    $('#status' + id).prop('disabled', false);
    $('#startDate' + id).prop('disabled', false);
    $('#endDate' + id).prop('disabled', false);
    $('#edit' + id).prop('hidden', true);
    $('#save' + id).prop('hidden', false);
}

function saveClick(id) {
    $('#idUser').val(id);
    $('#userRole').val($('#role' + id).val());
    $('#userStatus').val($('#status' + id).val());
    $('#startWorkingDate').val($('#startDate' + id).val());
    $('#endWorkingDate').val($('#endDate' + id).val());
    $('#surnameSearch2').val($('#surnameSearch').val());
    $('#role' + id).prop('disabled', true);
    $('#status' + id).prop('disabled', true);
    $('#startDate' + id).prop('disabled', true);
    $('#endDate' + id).prop('disabled', true);
    $('#edit' + id).prop('hidden', false);
    $('#save' + id).prop('hidden', true);
    $('#userRoleForm').submit();
}

function changeRole(id) {
    $("#startDate" + id).prop('hidden', $("#role" + id).val() === '3');
    $("#endDate" + id).prop('hidden', $("#role" + id).val() === '3');
}