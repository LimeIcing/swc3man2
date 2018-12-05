package laace.swc3man2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//intellij virker ikke ordentligt
//kan ikke name den AdminController
@Controller
@RequestMapping("/admin")
public class Admin {

    @GetMapping("/studentCourses")
    public String manageStudentCourseRequest()
    {
        return "studentCourses";
    }
}
