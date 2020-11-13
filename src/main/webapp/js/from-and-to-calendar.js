$.datepicker.regional['ru'] = {
    closeText: 'Закрыть',
    prevText: 'Предыдущий',
    nextText: 'Следующий',
    currentText: 'Сегодня',
    monthNames: ['Январь', 'Февраль', 'Март', 'Апрель', 'Май', 'Июнь', 'Июль', 'Август', 'Сентябрь', 'Октябрь', 'Ноябрь', 'Декабрь'],
    monthNamesShort: ['Янв', 'Фев', 'Мар', 'Апр', 'Май', 'Июн', 'Июл', 'Авг', 'Сен', 'Окт', 'Ноя', 'Дек'],
    dayNames: ['Воскресенье', 'Понедельник', 'Вторник', 'Среда', 'Четверг', 'Пятница', 'Суббота'],
    dayNamesShort: ['Вск', 'Пнд', 'Втр', 'Срд', 'Чтв', 'Птн', 'Суб'],
    dayNamesMin: ['Вс', 'Пн', 'Вт', 'Ср', 'Чт', 'Пт', 'Сб'],
    weekHeader: 'Нед',
    dateFormat: 'dd.mm.yy',
    firstDay: 1,
    isRTL: false,
    monthsTitle: 'Месяцы',
    yearSuffix: '',
    clear: 'Очистить'
};

$('#startDate').datepicker({
    startDate: new Date(1995, 1, 1),
    autoclose: true,
    changeMonth: true,
    changeYear: true,
    minDate: new Date(1960, 1, 1),
    maxDate: new Date()
});

$('#from-workout-date').datepicker({
    startDate: new Date(),
    autoclose: true,
    changeMonth: true,
    changeYear: true,
    minDate: new Date()
});

$('#birthday').datepicker({
    startDate: new Date(1995, 1, 1),
    autoclose: true,
    changeMonth: true,
    changeYear: true,
    minDate: new Date(1960, 1, 1),
    maxDate: new Date()
});


$('#openDate').datepicker({
    startDate: new Date(),
    autoclose: true,
    changeMonth: true,
    changeYear: true,
    minDate: new Date()
});



$(document).ready(function () {
    var locale = document.getElementById("localeCalendar").value;
    if (locale === 'ru_RU') {
        $.datepicker.setDefaults($.datepicker.regional['ru']);
    }
});

$('.choose-date').each(function(){
    $(this).datepicker({
        startDate: new Date(),
        autoclose: true,
        changeMonth: true,
        changeYear: true,
        minDate: new Date()
    });
});
