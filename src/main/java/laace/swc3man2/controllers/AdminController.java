package laace.swc3man2.controllers;

import laace.swc3man2.models.CourseModel;
import laace.swc3man2.services.CourseService;
import laace.swc3man2.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    StudentService studentService;

    @Autowired
    CourseService courseService;

    @GetMapping("/studentCourses")
    public String manageStudentCourseRequest()
    {
        return "admin/studentCourses";
    }

    @GetMapping
    public String studentCoursePage(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("courses", courseService.listAll(page) );
        model.addAttribute("students", studentService.listAll(page) );
        model.addAttribute("currentPage",page);
        return "courses/index";
    }
}
