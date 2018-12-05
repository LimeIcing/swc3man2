/*
*
 */

$(document).ready(function () {
    $('.table .eBtn').on('click',function(event) {
        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href,function (students,status) {
            $('.myModal #row_name').text(students.name);
            $('.myModal #row_email').text(students.email);
            $('.myModal #row_username').text(students.username);
            $('.myModal #row_password').text(students.password);
            $('.myModal #row_enabled').text(students.enabled);
        });

        $('.myModal #exampleModal').modal();
    })
});
