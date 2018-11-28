/*
*
 */

$(document).ready(function () {
    $('.table .eBtn').on('click',function(event) {
        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href,function (courses,status) {
            $('.myModal #course_id').val(courses.id);
            $('.myModal #course_semester').val(courses.semester);
            $('.myModal #course_ECTS').text(courses.ects);
            $('.myModal #course_').val(courses.numberOfTeachers);
            $('.myModal #course_').val(courses.minNumberOfStudents);
            $('.myModal #course_').val(courses.expectedNumberOfStudents);
            $('.myModal #course_').val(courses.maxNumberOfStudents);
            $('.myModal #course_').val(courses.expected_number_of_students);
            $('.myModal #course_name').val(courses.name);
            $('.myModal #course_').val(courses.studyprogramme);
            $('.myModal #course_dkname').val(courses.namedanish);
            $('.myModal #course_').val(courses.description);
            $('.myModal #course_languange').val(courses.languange);
            $('.myModal #course_').val(courses.classCode);
            $('.myModal #course_').val(courses.prerequisites);
            $('.myModal #course_').val(courses.learningOutcome);
            $('.myModal #course_').val(courses.content);
            $('.myModal #course_').val(courses.learningActivities);
            $('.myModal #course_').val(courses.examForm);
            $('.myModal #course_').val(courses.teachers);
            $('.myModal #course_lastUp').val(courses.lastUpdated);
            $('.myModal #course_Mandatory').val(courses.mandatory);

        });

        $('.myModal #exampleModal').modal();
    })
});