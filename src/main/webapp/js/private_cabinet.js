$(document).ready(function () {
        $("#phone").mask("+375 99 999-99-99");

        if ($("#editInfo").val() == "true") {
            enableFields();
        } else {
            disableFields();
        }

        switch ($("#incorrectPasswordMessage").val()) {
            case 'true':
                $("#modalChangePassword").modal('show');
                $("#incorrectPassword").prop('hidden', false);
                $("#modalCorrectPasswordChange").modal('hide');
                break;
            case 'false':
                $("#modalChangePassword").modal('hide');
                $("#incorrectPassword").prop('hidden', true);
                $("#modalCorrectPasswordChange").modal('show');
                $("#incorrectPasswordMessage").val('');
                break;
            default:
                $("#incorrectPassword").prop('hidden', true);
                $("#modalCorrectPasswordChange").modal('hide');
        }

        $('#modalChangePassword').on('hidden.bs.modal', function () {
            clearPassword();
            $("#incorrectPassword").prop('hidden', true);
        });
        if ($('#language').val() == 'ru_RU') {
            $('#photo').lang('ru');
        } else {
            $('#photo').lang('en');
        }
    }
);

$("#changePrivateInfo").click(function () {
    enableFields();
});

$("#cancelPhoto").click(function () {
    $("#photo").val('');
    $('#modalChangePhoto').modal('hide');
});

$("#addPhoto").click(function () {
    $('#modalChangePhoto').modal('show');
});

$('#photo:file').change(function (e) {
    $("#labelPhoto").text(e.target.files[0].name);
});

$("#cancel").click(function () {
    $('input').each(function () {
        $(this).val($(this).attr('previousValue'));
    });
    $('textarea#staffDescription').val($("textarea#previousDescription").val());
    disableFields();
});

$("#cancelPassword").click(function () {
    clearPassword();
    $('#modalChangePassword').modal('hide');
});

function enableFields() {
    $("#from-login").prop('disabled', false);
    $("#from-surname").prop('disabled', false);
    $("#from-name").prop('disabled', false);
    $("#from-email").prop('disabled', false);
    $("#phone").prop('disabled', false);
    $("#birthday").prop('disabled', false);
    $("#workExperience").prop('disabled', false);
    $("#staffDescription").prop('disabled', false);
    $("#saveChange").prop('hidden', false);
    $("#cancel").prop('hidden', false);
};

function disableFields() {
    $("#from-login").prop('disabled', true);
    $("#from-surname").prop('disabled', true);
    $("#from-name").prop('disabled', true);
    $("#from-email").prop('disabled', true);
    $("#phone").prop('disabled', true);
    $("#birthday").prop('disabled', true);
    $("#workExperience").prop('disabled', true);
    $("#staffDescription").prop('disabled', true);
    $("#saveChange").prop('hidden', true);
    $("#cancel").prop('hidden', true);
};

function clearPassword() {
    $("#oldPassword").val('');
    $("#newPassword").val('');
    $("#repeatedNewPassword").val('');
    $("#incorrectPassword").val('');
};