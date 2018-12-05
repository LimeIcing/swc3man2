/*
*
 */

$(document).ready(function () {
    $('.table .eBtn').on('click',function(event) {
        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href,function (courses,status) {
            $('.myModal #course_id').text(courses.id);
            $('.myModal #row_semester').text(courses.semester);
            $('.myModal #row_ects').text(courses.ects);
            $('.myModal #row_minstudent').text(courses.minNumberOfStudents);
            $('.myModal #row_expectedNumberOfStudents').text(courses.expectedNumberOfStudents);
            $('.myModal #row_maxNumberOfStudents').text(courses.maxNumberOfStudents);
            $('.myModal #row_name').text(courses.name);
            $('.myModal #row_studyperogramme').text(courses.studyperogramme);
            $('.myModal #row_dkname').text(courses.namedanish);
            $('.myModal #row_description').text(courses.description);
            $('.myModal #row_lang').text(courses.languange);
            $('.myModal #row_classCode').text(courses.classCode);
            $('.myModal #row_prerequisites').text(courses.prerequisites);
            $('.myModal #row_learningOutcome').text(courses.learningOutcome);
            $('.myModal #row_content').text(courses.content);
            $('.myModal #row_learningActivities').text(courses.learningActivities);
            $('.myModal #row_examForm').text(courses.examForm);
            $('.myModal #row_teachers').text(courses.teachers);
            $('.myModal #row_lastupdate').text(courses.lastUpdated);
            $('.myModal #row_mandatory').text(courses.mandatory);
        });

        $('.myModal #exampleModal').modal();
    })
});
