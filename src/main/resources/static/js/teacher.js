/*
*
 */

$(document).ready(function () {
    $('.table .eBtn').on('click',function(event) {
        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href,function (teachers,status) {
            $('.myModal #row_fname').text(teachers.name);
            $('.myModal #row_lname').text(teachers.name);
            $('.myModal #row_email').text(teachers.email);
        });

        $('.myModal #exampleModal').modal();
    })
});
