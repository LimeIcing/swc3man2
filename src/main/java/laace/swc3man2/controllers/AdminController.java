package laace.swc3man2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/studentCourses")
    public String manageStudentCourseRequest()
    {
        return "admin/studentCourses";
    }
}
