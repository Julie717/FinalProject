$(document).ready(function () {
    if ($("#showModalDeleteSchedule").val() == 'true') {
        $("#modalDeleteSchedule").modal('show');
    } else {
        $("#modalDeleteSchedule").modal('hide');
    }
});

$("#backToClientMemberships").click(function () {
    $("#openDate").prop('required', false)
});

$("#from-workout-name").change(function () {
    $("#idWorkout").val($("#from-workout-name").val());
});

$("#from-hall").change(function () {
    $("#idHall").val($("#from-hall").val());
});

$("#from-instructor-name").change(function () {
    $("#idInstructor").val($("#from-instructor-name").val());
});
$("#saveChanges").click(function () {
    $("#startDate").val($("#from-workout-date").val());
    $("#startTime").val($("#from-workout-time").val());
    $("#duration").val($("#from-workout-duration").val());
    $("#capacity").val($("#from-workout-capacity").val());
    $("#idWorkout").val($("#from-workout-name").val());
    $("#idHall").val($("#from-hall").val());
    $("#idInstructor").val($("#from-instructor-name").val());
});

$("#saleMembershipButton").click(function () {
    $("#idMembership").val($("input[name=idMembershipRadio]:checked").val());
});